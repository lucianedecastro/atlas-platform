package br.com.copadasautoras.atlas.domain.documento.mapper;

import br.com.copadasautoras.atlas.domain.documento.dto.request.AtualizarDocumentoRequest;
import br.com.copadasautoras.atlas.domain.documento.dto.request.CriarDocumentoRequest;
import br.com.copadasautoras.atlas.domain.documento.dto.response.DocumentoResponse;
import br.com.copadasautoras.atlas.domain.documento.entity.Documento;
import br.com.copadasautoras.atlas.domain.documento.enums.ContextoDocumento;
import br.com.copadasautoras.atlas.domain.organizacao.entity.Organizacao;
import br.com.copadasautoras.atlas.domain.projeto.entity.Projeto;
import br.com.copadasautoras.atlas.domain.relacionamento.entity.Relacionamento;

public final class DocumentoMapper {

    private DocumentoMapper() {
    }

    public static Documento toEntity(CriarDocumentoRequest request,
                                     Relacionamento relacionamento,
                                     Projeto projeto,
                                     Organizacao organizacao) {

        Documento documento = new Documento();
        documento.setTitulo(request.titulo());
        documento.setTipo(request.tipo());
        documento.setDescricao(request.descricao());
        documento.setNomeArquivo(request.nomeArquivo());
        documento.setCaminhoArquivo(request.caminhoArquivo());
        documento.setUrlArquivo(request.urlArquivo());
        documento.setVersao(1);
        documento.setDataDocumento(request.dataDocumento());
        documento.setRelacionamento(relacionamento);
        documento.setProjeto(projeto);
        documento.setOrganizacao(organizacao);

        return documento;
    }

    public static void atualizar(Documento documento, AtualizarDocumentoRequest request) {

        if (request.titulo() != null) {
            documento.setTitulo(request.titulo());
        }

        if (request.tipo() != null) {
            documento.setTipo(request.tipo());
        }

        if (request.descricao() != null) {
            documento.setDescricao(request.descricao());
        }

        if (request.nomeArquivo() != null) {
            documento.setNomeArquivo(request.nomeArquivo());
        }

        if (request.caminhoArquivo() != null) {
            documento.setCaminhoArquivo(request.caminhoArquivo());
        }

        if (request.urlArquivo() != null) {
            documento.setUrlArquivo(request.urlArquivo());
        }

        if (request.versao() != null) {
            documento.setVersao(request.versao());
        }

        if (request.dataDocumento() != null) {
            documento.setDataDocumento(request.dataDocumento());
        }
    }

    public static DocumentoResponse toResponse(Documento documento) {

        Relacionamento relacionamento = documento.getRelacionamento();
        Projeto projeto = documento.getProjeto();
        Organizacao organizacao = documento.getOrganizacao();

        return new DocumentoResponse(
                documento.getId(),
                documento.getTitulo(),
                documento.getTipo(),
                documento.getDescricao(),
                documento.getNomeArquivo(),
                documento.getCaminhoArquivo(),
                documento.getUrlArquivo(),
                documento.getVersao(),
                documento.getDataDocumento(),
                calcularContexto(documento),
                relacionamento != null ? relacionamento.getId() : null,
                projeto != null ? projeto.getId() : null,
                projeto != null ? projeto.getNome() : null,
                organizacao != null ? organizacao.getId() : null,
                organizacao != null ? organizacao.getNome() : null,
                documento.getCreatedAt(),
                documento.getUpdatedAt()
        );
    }

    private static ContextoDocumento calcularContexto(Documento documento) {

        if (documento.getRelacionamento() != null) {
            return ContextoDocumento.NEGOCIACAO;
        }

        if (documento.getProjeto() != null && documento.getOrganizacao() != null) {
            return ContextoDocumento.PROPOSTA_DIRECIONADA;
        }

        return ContextoDocumento.INSTITUCIONAL;
    }

}
