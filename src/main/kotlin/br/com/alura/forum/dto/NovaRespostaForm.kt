package br.com.alura.forum.dto

import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario

data class NovaRespostaForm (
        val mensagem: String,
        val idAutor: Usuario,
        val idTopico: Topico
)
