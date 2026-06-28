package br.com.copadasautoras.atlas.domain.oportunidade.repository;

import br.com.copadasautoras.atlas.domain.oportunidade.entity.OportunidadeCandidata;
import br.com.copadasautoras.atlas.domain.oportunidade.enums.StatusCandidata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OportunidadeCandidataRepository extends JpaRepository<OportunidadeCandidata, UUID> {

    List<OportunidadeCandidata> findByStatus(StatusCandidata status);

    List<OportunidadeCandidata> findByProjetoId(UUID projetoId);

}
