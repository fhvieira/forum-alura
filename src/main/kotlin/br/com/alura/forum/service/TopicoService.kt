package br.com.alura.forum.service

import br.com.alura.forum.dto.AlteracaoTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
        private var topicos: List<Topico>,
        usuarioService: UsuarioService,
        cursoService: CursoService,
        private var topicoViewMapper: TopicoViewMapper
) {

    init {
        val topico1 = Topico(
                id = 1,
                titulo = "primeiro topico",
                mensagem = "primeiro topico de teste",
                curso = cursoService.buscarPorId(idCurso = 1),
                autor = usuarioService.buscarPorId(1)
        )

        val topico2 = Topico(
                id = 2,
                titulo = "segundo topico",
                mensagem = "segundo topico de teste",
                curso = cursoService.buscarPorId(idCurso = 1),
                autor = usuarioService.buscarPorId(1)
        )

        val topico3 = Topico(
                id = 3,
                titulo = "terceiro topico",
                mensagem = "terceiro topico de teste",
                curso = cursoService.buscarPorId(idCurso = 1),
                autor = usuarioService.buscarPorId(1)
        )

        topicos = listOf(topico1, topico2, topico3)
    }

    fun listar(): List<TopicoView> {
        return topicos.stream().map { t ->
            topicoViewMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): Topico {
        return topicos.stream()
            .filter { t -> t.id == id }
            .findFirst().orElseThrow{ NotFoundException("Topico não encontrado") }
    }

    fun toTopicoView(id: Long): TopicoView {
        return topicoViewMapper.map(buscarPorId(id))
    }

    fun cadastrar(topico: Topico): TopicoView {
        topico.id = topicos.size.plus(1L)
        topicos = topicos.plus(topico)
        return topicoViewMapper.map(topico)
    }

    fun alterar(form: AlteracaoTopicoForm): TopicoView {
        val topico = buscarPorId(form.id)

        val topicoAlterado = Topico(
                form.id,
                form.titulo,
                form.mensagem,
                topico.curso,
                topico.autor,
                topico.status,
                topico.respostas,
                topico.dataCriacao)

        topicos = topicos.minus(topico).plus(topicoAlterado)

        return topicoViewMapper.map(topico)
    }

    fun excluir(id: Long) {
        topicos = topicos.minus(buscarPorId(id))
    }

}