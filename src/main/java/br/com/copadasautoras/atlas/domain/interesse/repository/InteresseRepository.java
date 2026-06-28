package br.com.copadasautoras.atlas.domain.interesse.repository;

import br.com.copadasautoras.atlas.domain.interesse.entity.Interesse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InteresseRepository extends JpaRepository<Interesse, UUID> {

    boolean existsByNomeIgnoreCase(String nome);

    List<Interesse> findByAtivo(Boolean ativo);

    List<Interesse> findByCategoria(String categoria);

}
