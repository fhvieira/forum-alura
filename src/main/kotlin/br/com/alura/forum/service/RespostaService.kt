package br.com.alura.forum.service

import br.com.alura.forum.dto.NovaRespostaForm
import br.com.alura.forum.dto.RespostaView
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

    init {
        val resposta1 = Resposta(
                id = 1,
                mensagem = "primeira mensagem de resposta",
                autor = usuarioService.buscarPorId(1),
                topico = topicoService.buscarPorId(id = 1),
                solucao = true
        )

        val resposta2 = Resposta(
                id = 2,
                mensagem = "segunda mensagem de resposta",
                autor = usuarioService.buscarPorId(2),
                topico = topicoService.buscarPorId(id = 1),
                solucao = true
        )

        val resposta3 = Resposta(
                id = 3,
                mensagem = "terceira mensagem de resposta",
                autor = usuarioService.buscarPorId(2),
                topico = topicoService.buscarPorId(id = 2),
                solucao = true
        )

        respostas = listOf(resposta1, resposta2, resposta3)
    }

    fun listar(idTopico: Long): List<RespostaView> {
        return respostas.stream()
                .filter { r -> r.topico.id == idTopico }
                .map { r -> respostaViewMapper.map(r) }
                .collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): RespostaView {
        return respostaViewMapper.map(respostas.stream()
                .filter { r -> r.id == id }
                .findFirst().get())
    }

    fun cadastrar(form: NovaRespostaForm): RespostaView {
        val resposta = respostaFormMapper.map(form)

        resposta.id = respostas.size.plus(1L)

        respostas = respostas.plus(resposta)

        return respostaViewMapper.map(resposta)
    }

}
