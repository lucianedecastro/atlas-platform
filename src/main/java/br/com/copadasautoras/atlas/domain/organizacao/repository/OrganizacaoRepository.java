package br.com.copadasautoras.atlas.domain.organizacao.repository;

import br.com.copadasautoras.atlas.domain.organizacao.enums.TipoOrganizacao;
import br.com.copadasautoras.atlas.domain.organizacao.entity.Organizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrganizacaoRepository extends JpaRepository<Organizacao, UUID> {

    List<Organizacao> findByTipo(TipoOrganizacao tipo);

    List<Organizacao> findByNomeContainingIgnoreCase(String nome);

    List<Organizacao> findByEstado(String estado);

    List<Organizacao> findByEstadoAndTipo(String estado, TipoOrganizacao tipo);

    boolean existsByCnpj(String cnpj);

}