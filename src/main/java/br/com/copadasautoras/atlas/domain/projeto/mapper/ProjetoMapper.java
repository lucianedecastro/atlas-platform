package br.com.copadasautoras.atlas.domain.projeto.mapper;

import br.com.copadasautoras.atlas.domain.projeto.dto.request.AtualizarProjetoRequest;
import br.com.copadasautoras.atlas.domain.projeto.dto.request.CriarProjetoRequest;
import br.com.copadasautoras.atlas.domain.projeto.dto.response.ProjetoResponse;
import br.com.copadasautoras.atlas.domain.projeto.entity.Projeto;
import br.com.copadasautoras.atlas.domain.projeto.enums.StatusProjeto;

import java.math.BigDecimal;

public final class ProjetoMapper {

    private ProjetoMapper() {
    }

    public static Projeto toEntity(CriarProjetoRequest request) {

        Projeto projeto = new Projeto();
        projeto.setNome(request.nome());
        projeto.setDescricao(request.descricao());
        projeto.setCategoria(request.categoria());
        projeto.setStatus(StatusProjeto.IDEACAO);
        projeto.setProponente(request.proponente());
        projeto.setLeiIncentivo(request.leiIncentivo() != null ? request.leiIncentivo() : false);
        projeto.setOrcamentoPrevisto(request.orcamentoPrevisto());
        projeto.setValorCaptado(BigDecimal.ZERO);
        projeto.setDataInicio(request.dataInicio());
        projeto.setDataFim(request.dataFim());
        projeto.setCidade(request.cidade());
        projeto.setEstado(request.estado());
        projeto.setLatitude(request.latitude());
        projeto.setLongitude(request.longitude());

        return projeto;
    }

    public static void atualizar(Projeto projeto, AtualizarProjetoRequest request) {

        projeto.setStatus(request.status());
        projeto.setDescricao(request.descricao());
        projeto.setCategoria(request.categoria());
        projeto.setProponente(request.proponente());

        if (request.leiIncentivo() != null) {
            projeto.setLeiIncentivo(request.leiIncentivo());
        }

        projeto.setOrcamentoPrevisto(request.orcamentoPrevisto());

        if (request.valorCaptado() != null) {
            projeto.setValorCaptado(request.valorCaptado());
        }

        projeto.setDataInicio(request.dataInicio());
        projeto.setDataFim(request.dataFim());
        projeto.setCidade(request.cidade());
        projeto.setEstado(request.estado());
        projeto.setLatitude(request.latitude());
        projeto.setLongitude(request.longitude());
    }

    public static ProjetoResponse toResponse(Projeto projeto) {

        return new ProjetoResponse(
                projeto.getId(),
                projeto.getNome(),
                projeto.getDescricao(),
                projeto.getCategoria(),
                projeto.getStatus(),
                projeto.getProponente(),
                projeto.getLeiIncentivo(),
                projeto.getOrcamentoPrevisto(),
                projeto.getValorCaptado(),
                projeto.getDataInicio(),
                projeto.getDataFim(),
                projeto.getCidade(),
                projeto.getEstado(),
                projeto.getLatitude(),
                projeto.getLongitude(),
                projeto.getCreatedAt(),
                projeto.getUpdatedAt()
        );
    }

}
