package br.com.livraria.gestao_livraria.controller;

import br.com.livraria.gestao_livraria.domain.cliente.Cliente;
import br.com.livraria.gestao_livraria.domain.cliente.DadosAtualizacaoCliente;
import br.com.livraria.gestao_livraria.domain.cliente.DadosCadastroCliente;
import br.com.livraria.gestao_livraria.domain.cliente.DadosListagemCliente;
import br.com.livraria.gestao_livraria.domain.livro.DadosListagemLivro;
import br.com.livraria.gestao_livraria.service.ClienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("clientes")
@SecurityRequirement(name = "bearer-key")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Page<DadosListagemCliente>> listarClientes(@PageableDefault(sort = {"nome"}) Pageable pageable) {
        var page = clienteService.findClientes(pageable).map(DadosListagemCliente::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemCliente> cadastrarCliente( @RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder ) {
        var cliente = new Cliente(dados);
        clienteService.cadastrarCliente(cliente);

        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemCliente(cliente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosListagemCliente> atualizarCliente(@RequestBody @Valid DadosAtualizacaoCliente dados){
        var cliente = clienteService.pegarReferenciaPorId(dados.id());
        clienteService.atualizarCliente(dados, cliente);

        return ResponseEntity.ok(new DadosListagemCliente(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosListagemCliente> excluirCliente(@PathVariable Long id){
        var cliente = clienteService.pegarReferenciaPorId(id);
        clienteService.excluir(cliente);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemCliente> buscarClientePorId(@PathVariable Long id){
        var cliente = clienteService.pegarReferenciaPorId(id);
        return ResponseEntity.ok(new DadosListagemCliente(cliente));
    }

//    @PutMapping("/{id}/livros")
//    @Transactional
//    public ResponseEntity<String> adicionarLivrosAoCliente(
//            @PathVariable Long id, @RequestBody List<Long> livrosIds) {
//        clienteService.adicionarLivrosAoCliente(id, livrosIds);
//        return ResponseEntity.ok("Livros adicionados ao cliente com sucesso!");
//    }
//
//    @GetMapping("/{id}/livros")
//    public ResponseEntity<Page<DadosListagemLivro>> listarLivrosDoCliente(
//            @PathVariable Long id, @PageableDefault(size = 5, sort = {"id"}) Pageable pageable) {
//        var page = clienteService.listarLivrosDoCliente(id, pageable);
//        return ResponseEntity.ok(page);
//    }


}
