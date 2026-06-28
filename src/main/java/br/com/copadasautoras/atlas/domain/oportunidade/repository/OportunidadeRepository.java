package br.com.copadasautoras.atlas.domain.oportunidade.repository;

import br.com.copadasautoras.atlas.domain.oportunidade.entity.Oportunidade;
import br.com.copadasautoras.atlas.domain.oportunidade.enums.StatusOportunidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OportunidadeRepository extends JpaRepository<Oportunidade, UUID> {

    List<Oportunidade> findByRelacionamentoId(UUID relacionamentoId);

    List<Oportunidade> findByStatus(StatusOportunidade status);

}
