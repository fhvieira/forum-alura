package br.com.alura.forum.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
data class Topico (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        val titulo: String,
        val mensagem: String,
        @Enumerated
        val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
        val dataCriacao: LocalDateTime = LocalDateTime.now(),
        @ManyToOne
        val curso: Curso,
        @ManyToOne
        val autor: Usuario,
        @OneToMany
        val respostas: List<Resposta> = ArrayList()
)