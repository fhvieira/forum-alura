package br.com.alura.forum.controller

import br.com.alura.forum.dto.RespostaView
import br.com.alura.forum.service.RespostaService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topicos/{id}/respostas")
class RespostaController(val service: RespostaService) {

    @GetMapping
    fun listar(): List<RespostaView> {
        return service.listar()
    }
}