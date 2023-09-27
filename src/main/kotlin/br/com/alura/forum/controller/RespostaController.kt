package br.com.alura.forum.controller

import br.com.alura.forum.dto.AlteracaoRespostasForm
import br.com.alura.forum.dto.NovaRespostaForm
import br.com.alura.forum.dto.RespostaView
import br.com.alura.forum.service.RespostaService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class RespostaController(private val service: RespostaService) {

    @GetMapping("/topicos/{idTopico}/respostas")
    fun listar(@PathVariable idTopico: Long): List<RespostaView> {
        return service.listar(idTopico)
    }

    @GetMapping("/respostas/{id}")
    fun buscarPorId(@PathVariable id: Long): RespostaView {
        return service.buscarPorId(id)
    }

    @PostMapping("/respostas")
    fun cadastrar(@RequestBody form: NovaRespostaForm): RespostaView {
        return service.cadastrar(form)
    }

    @PutMapping("/resposta")
    fun alterar(@RequestBody form: AlteracaoRespostasForm): RespostaView {
        TODO("not yet implemented")
    }
}