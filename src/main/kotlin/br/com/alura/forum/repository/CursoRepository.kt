package br.com.alura.forum.repository

import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository: JpaRepository<Curso, Long> {
}