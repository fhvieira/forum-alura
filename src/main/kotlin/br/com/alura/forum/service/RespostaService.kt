package br.com.alura.forum.service

import br.com.alura.forum.dto.AlteracaoRespostasForm
import br.com.alura.forum.dto.NovaRespostaForm
import br.com.alura.forum.dto.RespostaView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.RespostaFormMapper
import br.com.alura.forum.mapper.RespostaViewMapper
import br.com.alura.forum.model.Resposta
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class RespostaService(
    private var respostas: List<Resposta>,
    usuarioService: UsuarioService,
    topicoService: TopicoService,
    private var respostaViewMapper: RespostaViewMapper,
    private var respostaFormMapper: RespostaFormMapper
) {
    fun listar(idTopico: Long): List<RespostaView> {
        return respostas.stream()
                .filter { r -> r.topico.id == idTopico }
                .map { r -> respostaViewMapper.map(r) }
                .collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): Resposta {
        return respostas.stream()
                .filter { r -> r.id == id }
                .findFirst().orElseThrow { NotFoundException("Resposta não encontrada") }
    }

    fun toRespostaView(id: Long): RespostaView {
        return respostaViewMapper.map(buscarPorId(id))
    }

    fun cadastrar(form: NovaRespostaForm): RespostaView {
        val resposta = respostaFormMapper.map(form)

        resposta.id = respostas.size.plus(1L)

        respostas = respostas.plus(resposta)

        return respostaViewMapper.map(resposta)
    }

    fun alterar(form: AlteracaoRespostasForm): RespostaView {
        val resposta = buscarPorId(form.id)

        val respostasAlterada = Resposta(
            id = form.id,
            mensagem = form.mensagem,
            autor = resposta.autor,
            topico = resposta.topico,
            solucao = form.solucao
        )

        respostas = respostas.minus(resposta).plus(respostasAlterada)

        return respostaViewMapper.map(respostasAlterada)
    }

    fun deletar(id: Long) {
        respostas = respostas.minus(buscarPorId(id))
    }

}
