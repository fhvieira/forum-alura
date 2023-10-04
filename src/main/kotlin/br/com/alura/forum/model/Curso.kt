package br.com.alura.forum.model

import java.time.LocalDateTime
import java.util.*
import javax.annotation.processing.Generated
import javax.persistence.*

@Entity
data class Curso (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val nome: String,
    val categoria: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now()
)
