package br.com.copadasautoras.atlas.domain.interacao.repository;

import br.com.copadasautoras.atlas.domain.interacao.entity.Interacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InteracaoRepository extends JpaRepository<Interacao, UUID> {

    List<Interacao> findByRelacionamentoIdOrderByDataInteracaoDesc(UUID relacionamentoId);

}