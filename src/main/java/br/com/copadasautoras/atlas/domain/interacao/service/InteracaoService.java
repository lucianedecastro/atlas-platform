package br.com.copadasautoras.atlas.domain.interacao.service;

import br.com.copadasautoras.atlas.core.exception.RecursoNaoEncontradoException;
import br.com.copadasautoras.atlas.domain.interacao.dto.request.AtualizarInteracaoRequest;
import br.com.copadasautoras.atlas.domain.interacao.dto.request.CriarInteracaoRequest;
import br.com.copadasautoras.atlas.domain.interacao.dto.response.InteracaoResponse;
import br.com.copadasautoras.atlas.domain.interacao.entity.Interacao;
import br.com.copadasautoras.atlas.domain.interacao.mapper.InteracaoMapper;
import br.com.copadasautoras.atlas.domain.interacao.repository.InteracaoRepository;
import br.com.copadasautoras.atlas.domain.relacionamento.entity.Relacionamento;
import br.com.copadasautoras.atlas.domain.relacionamento.repository.RelacionamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class InteracaoService {

    private final InteracaoRepository repository;
    private final RelacionamentoRepository relacionamentoRepository;

    public InteracaoService(InteracaoRepository repository, RelacionamentoRepository relacionamentoRepository) {
        this.repository = repository;
        this.relacionamentoRepository = relacionamentoRepository;
    }

    @Transactional
    public InteracaoResponse criar(CriarInteracaoRequest request) {

        Relacionamento relacionamento = relacionamentoRepository.findById(request.relacionamentoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Relacionamento não encontrado."));

        Interacao interacao = InteracaoMapper.toEntity(request, relacionamento);

        return InteracaoMapper.toResponse(repository.saveAndFlush(interacao));
    }

    public InteracaoResponse buscarPorId(UUID id) {
        return InteracaoMapper.toResponse(buscarEntidadePorId(id));
    }

    public List<InteracaoResponse> listarPorRelacionamento(UUID relacionamentoId) {
        return repository.findByRelacionamentoIdOrderByDataInteracaoDesc(relacionamentoId).stream()
                .map(InteracaoMapper::toResponse)
                .toList();
    }

    @Transactional
    public InteracaoResponse atualizar(UUID id, AtualizarInteracaoRequest request) {
        Interacao interacao = buscarEntidadePorId(id);
        InteracaoMapper.atualizar(interacao, request);
        return InteracaoMapper.toResponse(repository.saveAndFlush(interacao));
    }

    @Transactional
    public void excluir(UUID id) {
        repository.delete(buscarEntidadePorId(id));
    }

    private Interacao buscarEntidadePorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Interação não encontrada."));
    }

}
