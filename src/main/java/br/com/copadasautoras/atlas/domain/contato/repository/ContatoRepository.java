package br.com.copadasautoras.atlas.domain.contato.repository;

import br.com.copadasautoras.atlas.domain.contato.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, UUID> {

    List<Contato> findByRelacionamentoId(UUID relacionamentoId);

    List<Contato> findByRelacionamentoIdAndAtivo(UUID relacionamentoId, Boolean ativo);

}
