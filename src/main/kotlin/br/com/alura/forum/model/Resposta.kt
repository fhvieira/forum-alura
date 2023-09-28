package br.com.alura.forum.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Resposta (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        val mensagem: String,

        @ManyToOne
        val autor: Usuario,

        @ManyToOne
        val topico: Topico,
        val solucao: Boolean,
        val dataCriacao: LocalDateTime = LocalDateTime.now()
)