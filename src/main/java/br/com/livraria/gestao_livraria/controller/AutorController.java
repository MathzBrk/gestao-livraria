package br.com.livraria.gestao_livraria.controller;

import br.com.livraria.gestao_livraria.domain.autor.Autor;
import br.com.livraria.gestao_livraria.domain.autor.DadosAtualizacaoAutor;
import br.com.livraria.gestao_livraria.domain.autor.DadosCadastroAutor;
import br.com.livraria.gestao_livraria.domain.autor.DadosListagemAutor;
import br.com.livraria.gestao_livraria.domain.livro.DadosListagemLivro;
import br.com.livraria.gestao_livraria.service.AutorService;
import jakarta.persistence.EntityNotFoundException;
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
@RequestMapping("autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public ResponseEntity<Page<DadosListagemAutor>> listarAutores( @PageableDefault(size = 5, sort = {"id"}) Pageable pageable ) {
        var page = autorService.findAutores(pageable).map(DadosListagemAutor::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemAutor> cadastrarAutor( @RequestBody @Valid DadosCadastroAutor dados, UriComponentsBuilder uriBuilder ){
        var autor = new Autor(dados);
        autorService.cadastrarAutor(autor);

        var uri = uriBuilder.path("/autores/{id}").buildAndExpand(autor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemAutor(autor));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosListagemAutor> atualizarAutor(@RequestBody @Valid DadosAtualizacaoAutor dadosAtualizacaoAutor){
        var autor = autorService.pegarReferenciaPorId(dadosAtualizacaoAutor.id());
        autorService.atualizarAutor(dadosAtualizacaoAutor, autor);

        return ResponseEntity.ok(new DadosListagemAutor(autor));
    }

    @PutMapping("/{id}/livros")
    @Transactional
    public ResponseEntity<String> atribuirLivros(@PathVariable Long id, @RequestBody List<Long> livrosIds) {

        if (livrosIds == null || livrosIds.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            autorService.atribuirLivrosAoAutor(id, livrosIds);
            return ResponseEntity.ok("Livros adicionados ao autor com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosListagemAutor> excluirAutor(@PathVariable Long id){
        var autor = autorService.pegarReferenciaPorId(id);
        autorService.excluir(autor);

        return  ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemAutor> listarAutor(@PathVariable Long id){
        var autor = autorService.pegarReferenciaPorId(id);
        return ResponseEntity.ok(new DadosListagemAutor(autor));
    }

    @GetMapping("/{id}/livros")
    public ResponseEntity<List<DadosListagemLivro>> listarLivrosDoAutor(@PathVariable Long id){
        try{
            var autor = autorService.pegarReferenciaPorId(id);
            var livros = autor.getLivros().stream()
                    .map(DadosListagemLivro::new)
                    .toList();

            return ResponseEntity.ok(livros);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
