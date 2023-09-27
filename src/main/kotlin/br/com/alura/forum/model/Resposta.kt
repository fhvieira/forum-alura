package br.com.alura.forum.model

import java.time.LocalDateTime

data class Resposta (
        var id: Long? = null,
        val mensagem: String,
        val autor: Usuario,
        val topico: Topico,
        val solucao: Boolean,
        val dataCriacao: LocalDateTime = LocalDateTime.now()
)