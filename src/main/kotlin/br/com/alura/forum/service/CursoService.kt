package br.com.alura.forum.service

import br.com.alura.forum.model.Curso
import org.springframework.stereotype.Service

@Service
class CursoService(private var cursos: List<Curso>) {

    init {
        cursos = listOf(Curso(
                id = 1,
                nome = "curso de kotlin",
                categoria = "Programação"
        ))
    }

    fun findPorId(idCurso: Long): Curso {
        return cursos.stream().filter { c ->
            c.id == idCurso
        }.findFirst().get()
    }

}
