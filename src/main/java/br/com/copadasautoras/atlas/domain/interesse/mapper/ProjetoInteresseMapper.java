package br.com.copadasautoras.atlas.domain.interesse.mapper;

import br.com.copadasautoras.atlas.domain.interesse.dto.request.AtualizarProjetoInteresseRequest;
import br.com.copadasautoras.atlas.domain.interesse.dto.request.CriarProjetoInteresseRequest;
import br.com.copadasautoras.atlas.domain.interesse.dto.response.ProjetoInteresseResponse;
import br.com.copadasautoras.atlas.domain.interesse.entity.Interesse;
import br.com.copadasautoras.atlas.domain.interesse.entity.ProjetoInteresse;
import br.com.copadasautoras.atlas.domain.projeto.entity.Projeto;

public final class ProjetoInteresseMapper {

    private ProjetoInteresseMapper() {
    }

    public static ProjetoInteresse toEntity(CriarProjetoInteresseRequest request,
                                            Projeto projeto,
                                            Interesse interesse) {

        ProjetoInteresse projetoInteresse = new ProjetoInteresse();
        projetoInteresse.setProjeto(projeto);
        projetoInteresse.setInteresse(interesse);
        projetoInteresse.setPeso(request.peso());
        projetoInteresse.setCriticidade(request.criticidade() != null ? request.criticidade() : (short) 3);
        projetoInteresse.setObservacoes(request.observacoes());

        return projetoInteresse;
    }

    public static void atualizar(ProjetoInteresse projetoInteresse, AtualizarProjetoInteresseRequest request) {

        projetoInteresse.setPeso(request.peso());

        if (request.criticidade() != null) {
            projetoInteresse.setCriticidade(request.criticidade());
        }

        projetoInteresse.setObservacoes(request.observacoes());
    }

    public static ProjetoInteresseResponse toResponse(ProjetoInteresse projetoInteresse) {

        Projeto projeto = projetoInteresse.getProjeto();
        Interesse interesse = projetoInteresse.getInteresse();

        return new ProjetoInteresseResponse(
                projetoInteresse.getId(),
                projeto.getId(),
                projeto.getNome(),
                interesse.getId(),
                interesse.getNome(),
                projetoInteresse.getPeso(),
                projetoInteresse.getCriticidade(),
                projetoInteresse.getObservacoes(),
                projetoInteresse.getCreatedAt(),
                projetoInteresse.getUpdatedAt()
        );
    }

}