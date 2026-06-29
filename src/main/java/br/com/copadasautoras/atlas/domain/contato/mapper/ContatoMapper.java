package br.com.copadasautoras.atlas.domain.contato.mapper;

import br.com.copadasautoras.atlas.domain.contato.dto.request.AtualizarContatoRequest;
import br.com.copadasautoras.atlas.domain.contato.dto.request.CriarContatoRequest;
import br.com.copadasautoras.atlas.domain.contato.dto.response.ContatoResponse;
import br.com.copadasautoras.atlas.domain.contato.entity.Contato;
import br.com.copadasautoras.atlas.domain.relacionamento.entity.Relacionamento;

public final class ContatoMapper {

    private ContatoMapper() {
    }

    public static Contato toEntity(CriarContatoRequest request, Relacionamento relacionamento) {

        Contato contato = new Contato();
        contato.setRelacionamento(relacionamento);
        contato.setNome(request.nome());
        contato.setCargo(request.cargo());
        contato.setEmail(request.email());
        contato.setTelefone(request.telefone());
        contato.setLinkedin(request.linkedin());
        contato.setPrincipal(request.principal() != null ? request.principal() : false);
        contato.setObservacoes(request.observacoes());

        return contato;
    }

    public static void atualizar(Contato contato, AtualizarContatoRequest request) {

        contato.setNome(request.nome());
        contato.setCargo(request.cargo());
        contato.setEmail(request.email());
        contato.setTelefone(request.telefone());
        contato.setLinkedin(request.linkedin());

        if (request.principal() != null) {
            contato.setPrincipal(request.principal());
        }

        contato.setObservacoes(request.observacoes());
        contato.setAtivo(request.ativo());
    }

    public static ContatoResponse toResponse(Contato contato) {

        Relacionamento relacionamento = contato.getRelacionamento();

        return new ContatoResponse(
                contato.getId(),
                relacionamento.getId(),
                relacionamento.getProjeto().getNome(),
                relacionamento.getOrganizacao().getNome(),
                contato.getNome(),
                contato.getCargo(),
                contato.getEmail(),
                contato.getTelefone(),
                contato.getLinkedin(),
                contato.getPrincipal(),
                contato.getObservacoes(),
                contato.getAtivo(),
                contato.getCreatedAt(),
                contato.getUpdatedAt()
        );
    }

}
