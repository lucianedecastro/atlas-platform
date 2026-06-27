package br.com.copadasautoras.atlas.repository;

import br.com.copadasautoras.atlas.entity.Organizacao;
import br.com.copadasautoras.atlas.enums.StatusRelacionamento;
import br.com.copadasautoras.atlas.enums.TipoOrganizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrganizacaoRepository extends JpaRepository<Organizacao, UUID> {

    List<Organizacao> findByTipo(TipoOrganizacao tipo);

    List<Organizacao> findByStatusRelacionamento(StatusRelacionamento statusRelacionamento);

    List<Organizacao> findByNomeContainingIgnoreCase(String nome);

    boolean existsByCnpj(String cnpj);

}