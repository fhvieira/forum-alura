package br.com.alura.forum.mapper

import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.CursoService
import br.com.alura.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(private val usuarioService: UsuarioService,
                       private val cursoService: CursoService): Mapper<NovoTopicoForm, Topico> {

    override fun map(form: NovoTopicoForm): Topico {
        return Topico(
            titulo = form.titulo,
            mensagem = form.mensagem,
            autor = usuarioService.findPorId(form.idAutor),
            curso = cursoService.findPorId(form.idCurso)
        )
    }

}
