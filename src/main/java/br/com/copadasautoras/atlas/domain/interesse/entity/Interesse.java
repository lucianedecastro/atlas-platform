package br.com.copadasautoras.atlas.domain.interesse.entity;

import br.com.copadasautoras.atlas.core.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "interesse")
public class Interesse extends BaseEntity {

    @Column(nullable = false, unique = true, length = 150)
    private String nome;

    @Column(length = 100)
    private String categoria;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    private Boolean ativo = true;

}
