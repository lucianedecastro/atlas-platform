package br.com.copadasautoras.atlas.domain.contato.service;

import br.com.copadasautoras.atlas.core.exception.RecursoNaoEncontradoException;
import br.com.copadasautoras.atlas.domain.contato.dto.request.AtualizarContatoRequest;
import br.com.copadasautoras.atlas.domain.contato.dto.request.CriarContatoRequest;
import br.com.copadasautoras.atlas.domain.contato.dto.response.ContatoResponse;
import br.com.copadasautoras.atlas.domain.contato.entity.Contato;
import br.com.copadasautoras.atlas.domain.contato.mapper.ContatoMapper;
import br.com.copadasautoras.atlas.domain.contato.repository.ContatoRepository;
import br.com.copadasautoras.atlas.domain.relacionamento.entity.Relacionamento;
import br.com.copadasautoras.atlas.domain.relacionamento.repository.RelacionamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ContatoService {

    private final ContatoRepository repository;
    private final RelacionamentoRepository relacionamentoRepository;

    public ContatoService(ContatoRepository repository, RelacionamentoRepository relacionamentoRepository) {
        this.repository = repository;
        this.relacionamentoRepository = relacionamentoRepository;
    }

    @Transactional
    public ContatoResponse criar(CriarContatoRequest request) {

        Relacionamento relacionamento = relacionamentoRepository.findById(request.relacionamentoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Relacionamento não encontrado."));

        Contato contato = ContatoMapper.toEntity(request, relacionamento);

        return ContatoMapper.toResponse(repository.saveAndFlush(contato));
    }

    public ContatoResponse buscarPorId(UUID id) {
        return ContatoMapper.toResponse(buscarEntidadePorId(id));
    }

    public List<ContatoResponse> listarPorRelacionamento(UUID relacionamentoId) {
        return repository.findByRelacionamentoId(relacionamentoId).stream()
                .map(ContatoMapper::toResponse)
                .toList();
    }

    public List<ContatoResponse> listarAtivosPorRelacionamento(UUID relacionamentoId) {
        return repository.findByRelacionamentoIdAndAtivo(relacionamentoId, true).stream()
                .map(ContatoMapper::toResponse)
                .toList();
    }

    @Transactional
    public ContatoResponse atualizar(UUID id, AtualizarContatoRequest request) {
        Contato contato = buscarEntidadePorId(id);
        ContatoMapper.atualizar(contato, request);
        return ContatoMapper.toResponse(repository.saveAndFlush(contato));
    }

    @Transactional
    public void excluir(UUID id) {
        repository.delete(buscarEntidadePorId(id));
    }

    private Contato buscarEntidadePorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Contato não encontrado."));
    }

}
