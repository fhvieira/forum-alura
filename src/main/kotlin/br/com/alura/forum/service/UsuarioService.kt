package br.com.alura.forum.service

import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(private var usuarios: List<Usuario>) {

    init {
        val usuario1 = Usuario(
                id = 1,
                nome = "john doe",
                email = "johndoe@email.com")

        val usuario2 = Usuario(
                id = 2,
                nome = "jane doe",
                email = "janedoe@email.com")

         usuarios = listOf(usuario1, usuario2)
    }

    fun buscarPorId(idAutor: Long): Usuario {
        return usuarios.stream().filter { u ->
            u.id == idAutor
        }.findFirst().get()
    }

}
