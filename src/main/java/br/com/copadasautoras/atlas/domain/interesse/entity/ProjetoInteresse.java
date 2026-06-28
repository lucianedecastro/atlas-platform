package br.com.copadasautoras.atlas.domain.interesse.entity;

import br.com.copadasautoras.atlas.core.entity.BaseEntity;
import br.com.copadasautoras.atlas.domain.projeto.entity.Projeto;
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
@Table(name = "projeto_interesse")
public class ProjetoInteresse extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interesse_id", nullable = false)
    private Interesse interesse;

    @Column(nullable = false)
    private Short peso;

    @Column(nullable = false)
    private Short criticidade = 3;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

}
