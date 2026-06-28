package br.com.copadasautoras.atlas.domain.relacionamento.repository;

import br.com.copadasautoras.atlas.domain.relacionamento.entity.Relacionamento;
import br.com.copadasautoras.atlas.domain.relacionamento.enums.StatusRelacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RelacionamentoRepository extends JpaRepository<Relacionamento, UUID> {

    List<Relacionamento> findByOrganizacaoId(UUID organizacaoId);

    List<Relacionamento> findByProjetoId(UUID projetoId);

    List<Relacionamento> findByStatus(StatusRelacionamento status);

    List<Relacionamento> findByOrganizacaoIdAndProjetoId(UUID organizacaoId, UUID projetoId);

}
