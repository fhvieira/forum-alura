package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NovoTopicoForm (
        @field:NotEmpty(message = "não pode ser em branco")
        @field:Size(min = 3, max = 100, message = "deve ter entre 3 e 100 caracteres")
        val titulo: String,

        @field:NotEmpty(message = "não pode ser em branco")
        val mensagem: String,

        @field:NotNull(message = "deve ser informado")
        val idAutor: Long,

        @field:NotNull(message = "deve ser informado")
        val idCurso: Long
)
