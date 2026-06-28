package br.com.copadasautoras.atlas.domain.interesse.repository;

import br.com.copadasautoras.atlas.domain.interesse.entity.OrganizacaoInteresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrganizacaoInteresseRepository extends JpaRepository<OrganizacaoInteresse, UUID> {

    List<OrganizacaoInteresse> findByOrganizacaoId(UUID organizacaoId);

    List<OrganizacaoInteresse> findByInteresseId(UUID interesseId);

    boolean existsByOrganizacaoIdAndInteresseId(UUID organizacaoId, UUID interesseId);

    boolean existsByInteresseId(UUID interesseId);

}
