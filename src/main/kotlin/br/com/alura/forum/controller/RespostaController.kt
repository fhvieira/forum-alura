package br.com.alura.forum.controller

import br.com.alura.forum.dto.AlteracaoRespostasForm
import br.com.alura.forum.dto.NovaRespostaForm
import br.com.alura.forum.dto.RespostaView
import br.com.alura.forum.service.RespostaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping
class RespostaController(private val service: RespostaService) {

    @GetMapping("/topicos/{idTopico}/respostas")
    fun listar(@PathVariable idTopico: Long): List<RespostaView> {
        return service.listar(idTopico)
    }

    @GetMapping("/respostas/{id}")
    fun buscarPorId(@PathVariable id: Long): RespostaView {
        return service.toRespostaView(id)
    }

    @PostMapping("/respostas")
    fun cadastrar(
        @RequestBody @Valid form: NovaRespostaForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<RespostaView> {
        val resposta = service.cadastrar(form)
        val uri = uriBuilder.path("/respotas/${resposta.id}").build().toUri()
        return ResponseEntity.created(uri).body(resposta)
    }

    @PutMapping("/resposta")
    fun alterar(@RequestBody form: AlteracaoRespostasForm): RespostaView {
        return service.alterar(form)
    }

    @DeleteMapping("/respostas/{id}")
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }
}