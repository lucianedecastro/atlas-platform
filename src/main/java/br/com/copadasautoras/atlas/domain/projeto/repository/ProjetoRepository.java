package br.com.copadasautoras.atlas.domain.projeto.repository;

import br.com.copadasautoras.atlas.domain.projeto.entity.Projeto;
import br.com.copadasautoras.atlas.domain.projeto.enums.StatusProjeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, UUID> {

    List<Projeto> findByStatus(StatusProjeto status);

    List<Projeto> findByNomeContainingIgnoreCase(String nome);

    List<Projeto> findByEstado(String estado);

    List<Projeto> findByEstadoAndStatus(String estado, StatusProjeto status);

}
