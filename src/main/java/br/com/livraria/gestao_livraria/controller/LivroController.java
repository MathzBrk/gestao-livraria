package br.com.livraria.gestao_livraria.controller;

import br.com.livraria.gestao_livraria.domain.autor.AutorRepository;
import br.com.livraria.gestao_livraria.domain.livro.DadosAtualizacaoLivro;
import br.com.livraria.gestao_livraria.domain.livro.DadosCadastroLivro;
import br.com.livraria.gestao_livraria.domain.livro.DadosListagemLivro;
import br.com.livraria.gestao_livraria.domain.livro.Livro;
import br.com.livraria.gestao_livraria.service.AutorService;
import br.com.livraria.gestao_livraria.service.LivroService;
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


@RestController
@RequestMapping("livros")
public class LivroController {

    @Autowired
    private LivroService livroService;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private AutorService autorService;

    @GetMapping
    public ResponseEntity<Page<DadosListagemLivro>> listarLivros( @PageableDefault(size = 5, sort = {"titulo"}) Pageable pageable) {
        var page = livroService.findDisponveis(pageable).map(DadosListagemLivro::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemLivro> cadastrarLivro( @RequestBody @Valid DadosCadastroLivro dados, UriComponentsBuilder uriBuilder){
        try {
            var autor = autorService.pegarReferenciaPorId(dados.idAutor());
            var livro = new Livro(dados, autor);
            livroService.cadastrarLivro(livro);

            var uri = uriBuilder.path("/livros/{id}").buildAndExpand(livro.getId()).toUri();

            return ResponseEntity.created(uri).body(new DadosListagemLivro(livro));
        } catch (EntityNotFoundException e) {
            System.out.println("Entidade não encontrada");
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosListagemLivro> atualizarLivro(@RequestBody @Valid DadosAtualizacaoLivro dados){
        var livro = livroService.pegarReferenciaPorId(dados.id());
        livroService.atualizarInformacoesLivro(dados, livro);

        return ResponseEntity.ok(new DadosListagemLivro(livro));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosListagemLivro> excluirLivro(@PathVariable Long id){
        var livro = livroService.pegarReferenciaPorId(id);
        livroService.excluir(livro);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemLivro> listarLivro(@PathVariable Long id){
        var livro = livroService.pegarReferenciaPorId(id);
        return ResponseEntity.ok(new DadosListagemLivro(livro));
    }

}
