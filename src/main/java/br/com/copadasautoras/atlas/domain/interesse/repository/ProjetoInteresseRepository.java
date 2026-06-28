package br.com.copadasautoras.atlas.domain.interesse.repository;

import br.com.copadasautoras.atlas.domain.interesse.entity.ProjetoInteresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjetoInteresseRepository extends JpaRepository<ProjetoInteresse, UUID> {

    List<ProjetoInteresse> findByProjetoId(UUID projetoId);

    List<ProjetoInteresse> findByInteresseId(UUID interesseId);

    boolean existsByProjetoIdAndInteresseId(UUID projetoId, UUID interesseId);

    boolean existsByInteresseId(UUID interesseId);

}