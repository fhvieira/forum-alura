package br.com.alura.forum.mapper

import br.com.alura.forum.dto.NovaRespostaForm
import br.com.alura.forum.model.Resposta
import br.com.alura.forum.service.TopicoService
import br.com.alura.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class RespostaFormMapper(
    private val usuarioService: UsuarioService,
    private val topicoService: TopicoService
): Mapper<NovaRespostaForm, Resposta> {

    override fun map(form: NovaRespostaForm): Resposta {
        return Resposta(
            mensagem = form.mensagem,
            autor = usuarioService.buscarPorId(form.idAutor),
            topico = topicoService.buscarPorId(form.idTopico),
            solucao = form.solucao
        )
    }

}
