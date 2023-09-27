package br.com.alura.forum.dto

data class AlteracaoRespostasForm (
        val id: Long,
        val mensagem: String,
        val solucao: Boolean
)
