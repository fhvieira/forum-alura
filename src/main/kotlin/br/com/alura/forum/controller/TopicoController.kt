package br.com.alura.forum.controller

import br.com.alura.forum.dto.AlteracaoTopicoForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.service.TopicoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService,
                       private val topicoFormMapper: TopicoFormMapper) {

    @GetMapping
    fun listar(): List<TopicoView> {
        return service.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoView {
        return service.toTopicoView(id)
    }

    @PostMapping
    fun cadastrar(
            @RequestBody @Valid form: NovoTopicoForm,
            uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoView> {
        val topico = topicoFormMapper.map(form);
        val topicoCadastrado = service.cadastrar(topico)
        val uri = uriBuilder.path("/topicos/${topicoCadastrado.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoCadastrado)
    }

    @PutMapping
    fun alterar(@RequestBody @Valid form: AlteracaoTopicoForm): ResponseEntity<TopicoView> {
        val topico = service.alterar(form)
        return ResponseEntity.ok().body(topico)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun excluir(@PathVariable id: Long) {
        service.excluir(id)
    }

}