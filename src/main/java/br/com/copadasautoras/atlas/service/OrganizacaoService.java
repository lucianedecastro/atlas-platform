package br.com.copadasautoras.atlas.service;

import br.com.copadasautoras.atlas.entity.Organizacao;
import br.com.copadasautoras.atlas.repository.OrganizacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrganizacaoService {

    private final OrganizacaoRepository repository;

    public OrganizacaoService(OrganizacaoRepository repository) {
        this.repository = repository;
    }

    public List<Organizacao> listarTodas() {
        return repository.findAll();
    }

    public Organizacao buscarPorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organização não encontrada."));
    }

    public Organizacao salvar(Organizacao organizacao) {

        if (organizacao.getCnpj() != null &&
                repository.existsByCnpj(organizacao.getCnpj())) {

            throw new RuntimeException("Já existe uma organização cadastrada com este CNPJ.");
        }

        return repository.save(organizacao);
    }

    public Organizacao atualizar(UUID id, Organizacao organizacaoAtualizada) {

        Organizacao organizacao = buscarPorId(id);

        organizacao.setNome(organizacaoAtualizada.getNome());
        organizacao.setNomeFantasia(organizacaoAtualizada.getNomeFantasia());
        organizacao.setCnpj(organizacaoAtualizada.getCnpj());
        organizacao.setTipo(organizacaoAtualizada.getTipo());
        organizacao.setStatusRelacionamento(organizacaoAtualizada.getStatusRelacionamento());
        organizacao.setSegmento(organizacaoAtualizada.getSegmento());
        organizacao.setSite(organizacaoAtualizada.getSite());
        organizacao.setDescricao(organizacaoAtualizada.getDescricao());
        organizacao.setUtilizaLeiIncentivo(organizacaoAtualizada.getUtilizaLeiIncentivo());
        organizacao.setPossuiInstituto(organizacaoAtualizada.getPossuiInstituto());
        organizacao.setObservacoes(organizacaoAtualizada.getObservacoes());

        return repository.save(organizacao);
    }

    public void excluir(UUID id) {

        Organizacao organizacao = buscarPorId(id);

        repository.delete(organizacao);
    }

}
