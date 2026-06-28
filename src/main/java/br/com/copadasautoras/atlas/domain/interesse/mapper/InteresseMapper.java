package br.com.copadasautoras.atlas.domain.interesse.mapper;

import br.com.copadasautoras.atlas.domain.interesse.dto.request.AtualizarInteresseRequest;
import br.com.copadasautoras.atlas.domain.interesse.dto.request.CriarInteresseRequest;
import br.com.copadasautoras.atlas.domain.interesse.dto.response.InteresseResponse;
import br.com.copadasautoras.atlas.domain.interesse.entity.Interesse;

public final class InteresseMapper {

    private InteresseMapper() {
    }

    public static Interesse toEntity(CriarInteresseRequest request) {

        Interesse interesse = new Interesse();
        interesse.setNome(request.nome());
        interesse.setCategoria(request.categoria());
        interesse.setDescricao(request.descricao());
        interesse.setAtivo(true);

        return interesse;
    }

    public static void atualizar(Interesse interesse, AtualizarInteresseRequest request) {

        interesse.setNome(request.nome());
        interesse.setCategoria(request.categoria());
        interesse.setDescricao(request.descricao());
        interesse.setAtivo(request.ativo());
    }

    public static InteresseResponse toResponse(Interesse interesse) {

        return new InteresseResponse(
                interesse.getId(),
                interesse.getNome(),
                interesse.getCategoria(),
                interesse.getDescricao(),
                interesse.getAtivo(),
                interesse.getCreatedAt(),
                interesse.getUpdatedAt()
        );
    }

}
