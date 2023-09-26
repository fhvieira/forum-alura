package br.com.alura.forum.model

import java.time.LocalDateTime

data class Curso (
    val id: Long? = null,
    val nome: String,
    val categoria: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now()
)