package br.com.copadasautoras.atlas.domain.documento.entity;

import br.com.copadasautoras.atlas.core.entity.BaseEntity;
import br.com.copadasautoras.atlas.domain.organizacao.entity.Organizacao;
import br.com.copadasautoras.atlas.domain.projeto.entity.Projeto;
import br.com.copadasautoras.atlas.domain.relacionamento.entity.Relacionamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "documento")
public class Documento extends BaseEntity {

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "nome_arquivo", nullable = false, length = 255)
    private String nomeArquivo;

    @Column(name = "caminho_arquivo", length = 500)
    private String caminhoArquivo;

    @Column(name = "url_arquivo", length = 500)
    private String urlArquivo;

    @Column(nullable = false)
    private Integer versao = 1;

    @Column(name = "data_documento")
    private LocalDate dataDocumento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "relacionamento_id")
    private Relacionamento relacionamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizacao_id")
    private Organizacao organizacao;

}
