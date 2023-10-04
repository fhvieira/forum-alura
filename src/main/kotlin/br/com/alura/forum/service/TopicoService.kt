package br.com.alura.forum.service

import br.com.alura.forum.dto.AlteracaoTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
class TopicoService(
    private val repository: TopicoRepository,
    usuarioService: UsuarioService,
    cursoService: CursoService,
    private var topicoViewMapper: TopicoViewMapper
) {
    fun listar(): List<TopicoView> {
        return repository.findAll().stream()
            .map { t -> topicoViewMapper.map(t) }
            .collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): Topico {
        return repository.findById(id).stream()
            .filter { t -> t.id == id }
            .findFirst().orElseThrow{ NotFoundException("Topico não encontrado") }
    }

    fun toTopicoView(id: Long): TopicoView {
        return topicoViewMapper.map(buscarPorId(id))
    }

    @Transactional
    fun cadastrar(topico: Topico): TopicoView {
        return topicoViewMapper.map(repository.save(topico))
    }

    @Transactional
    fun alterar(form: AlteracaoTopicoForm): TopicoView {
        val topico = buscarPorId(form.id)

        val topicoAlterado = topico.copy(titulo = form.titulo, mensagem = form.mensagem)

        repository.save(topicoAlterado)

        return topicoViewMapper.map(topico)
    }

    @Transactional
    fun excluir(id: Long) {
        repository.deleteById(id)
    }

}