package br.com.copadasautoras.atlas.domain.documento.repository;

import br.com.copadasautoras.atlas.domain.documento.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, UUID> {

    List<Documento> findByRelacionamentoId(UUID relacionamentoId);

    List<Documento> findByProjetoId(UUID projetoId);

    List<Documento> findByOrganizacaoId(UUID organizacaoId);

    List<Documento> findByProjetoIdAndOrganizacaoId(UUID projetoId, UUID organizacaoId);

    List<Documento> findByTipo(String tipo);

}
