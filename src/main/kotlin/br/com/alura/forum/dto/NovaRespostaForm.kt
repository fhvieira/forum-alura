package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NovaRespostaForm (
        @field:NotEmpty
        @field:Size(min = 10, max = 200, message = "deve estar entre 10 e 200 caracteres")
        val mensagem: String,

        @field:NotNull(message = "deve ser informado")
        val idAutor: Long,

        @field:NotNull(message = "deve ser informado")
        val idTopico: Long,

        val solucao: Boolean
)
