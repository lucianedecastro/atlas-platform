package br.com.copadasautoras.atlas.domain.inteligencia.service;

import br.com.copadasautoras.atlas.domain.inteligencia.dto.response.LacunaGeograficaResponse;
import br.com.copadasautoras.atlas.domain.inteligencia.dto.response.ScoreAfinidadeResponse;
import br.com.copadasautoras.atlas.domain.organizacao.entity.Organizacao;
import br.com.copadasautoras.atlas.domain.organizacao.enums.TipoOrganizacao;
import br.com.copadasautoras.atlas.domain.organizacao.repository.OrganizacaoRepository;
import br.com.copadasautoras.atlas.domain.projeto.entity.Projeto;
import br.com.copadasautoras.atlas.domain.projeto.enums.StatusProjeto;
import br.com.copadasautoras.atlas.domain.projeto.repository.ProjetoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class InteligenciaService {

    private final JdbcTemplate jdbcTemplate;
    private final ProjetoRepository projetoRepository;
    private final OrganizacaoRepository organizacaoRepository;

    public InteligenciaService(JdbcTemplate jdbcTemplate,
                               ProjetoRepository projetoRepository,
                               OrganizacaoRepository organizacaoRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.projetoRepository = projetoRepository;
        this.organizacaoRepository = organizacaoRepository;
    }

    public List<ScoreAfinidadeResponse> listarConexoesSugeridas(UUID projetoId, UUID organizacaoId) {

        StringBuilder sql = new StringBuilder(
                "SELECT projeto_id, projeto, organizacao_id, organizacao, interesses_em_comum, score_bruto " +
                        "FROM vw_score_afinidade WHERE 1 = 1"
        );

        List<Object> params = new ArrayList<>();

        if (projetoId != null) {
            sql.append(" AND projeto_id = ?");
            params.add(projetoId);
        }

        if (organizacaoId != null) {
            sql.append(" AND organizacao_id = ?");
            params.add(organizacaoId);
        }

        sql.append(" ORDER BY score_bruto DESC");

        return jdbcTemplate.query(sql.toString(), params.toArray(), (rs, rowNum) ->
                new ScoreAfinidadeResponse(
                        rs.getObject("projeto_id", UUID.class),
                        rs.getString("projeto"),
                        rs.getObject("organizacao_id", UUID.class),
                        rs.getString("organizacao"),
                        rs.getLong("interesses_em_comum"),
                        rs.getLong("score_bruto")
                )
        );
    }

    public List<LacunaGeograficaResponse> listarLacunasGeograficas() {

        Map<String, Long> projetosPorEstado = projetoRepository.findByStatus(StatusProjeto.EM_CAPTACAO).stream()
                .filter(p -> p.getEstado() != null)
                .collect(Collectors.groupingBy(Projeto::getEstado, Collectors.counting()));

        List<Organizacao> patrocinadores = new ArrayList<>();
        patrocinadores.addAll(organizacaoRepository.findByTipo(TipoOrganizacao.PATROCINADOR));
        patrocinadores.addAll(organizacaoRepository.findByTipo(TipoOrganizacao.PATROCINADOR_POTENCIAL));

        Map<String, Long> organizacoesPorEstado = patrocinadores.stream()
                .filter(o -> o.getEstado() != null)
                .collect(Collectors.groupingBy(Organizacao::getEstado, Collectors.counting()));

        Set<String> estados = new TreeSet<>();
        estados.addAll(projetosPorEstado.keySet());
        estados.addAll(organizacoesPorEstado.keySet());

        return estados.stream()
                .map(estado -> {
                    long projetos = projetosPorEstado.getOrDefault(estado, 0L);
                    long organizacoes = organizacoesPorEstado.getOrDefault(estado, 0L);
                    return new LacunaGeograficaResponse(estado, projetos, organizacoes, projetos - organizacoes);
                })
                .sorted(Comparator.comparingLong(LacunaGeograficaResponse::lacuna).reversed())
                .toList();
    }

}