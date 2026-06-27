package br.com.copadasautoras.atlas.controller;

import br.com.copadasautoras.atlas.entity.Organizacao;
import br.com.copadasautoras.atlas.service.OrganizacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/organizacoes")
public class OrganizacaoController {

    private final OrganizacaoService service;

    public OrganizacaoController(OrganizacaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Organizacao> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public Organizacao buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Organizacao salvar(@RequestBody Organizacao organizacao) {
        return service.salvar(organizacao);
    }

    @PutMapping("/{id}")
    public Organizacao atualizar(@PathVariable UUID id,
                                 @RequestBody Organizacao organizacao) {
        return service.atualizar(id, organizacao);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id) {
        service.excluir(id);
    }

}