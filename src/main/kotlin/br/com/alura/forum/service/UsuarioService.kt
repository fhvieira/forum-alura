package br.com.alura.forum.service

import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(private var usuarios: List<Usuario>) {

    init {
         usuarios = listOf(Usuario(
                id = 1,
                nome = "john doe",
                email = "johndoe@email.com"
        ))
    }

    fun findPorId(idAutor: Long): Usuario {
        return usuarios.stream().filter { u ->
            u.id == idAutor
        }.findFirst().get()
    }

}
