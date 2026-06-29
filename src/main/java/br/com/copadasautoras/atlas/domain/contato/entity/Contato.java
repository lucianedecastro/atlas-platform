package br.com.copadasautoras.atlas.domain.contato.entity;

import br.com.copadasautoras.atlas.core.entity.BaseEntity;
import br.com.copadasautoras.atlas.domain.relacionamento.entity.Relacionamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contato")
public class Contato extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "relacionamento_id", nullable = false)
    private Relacionamento relacionamento;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(length = 150)
    private String cargo;

    @Column(length = 200)
    private String email;

    @Column(length = 30)
    private String telefone;

    @Column(length = 255)
    private String linkedin;

    @Column(nullable = false)
    private Boolean principal = false;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @Column(nullable = false)
    private Boolean ativo = true;

}
