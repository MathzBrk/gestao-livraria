package br.com.livraria.gestao_livraria.service;

import br.com.livraria.gestao_livraria.domain.autor.Autor;
import br.com.livraria.gestao_livraria.domain.autor.AutorRepository;
import br.com.livraria.gestao_livraria.domain.autor.DadosAtualizacaoAutor;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroService livroService;

    @Transactional(readOnly = true)
    public Page<Autor> findAutores(Pageable pageable) {
        try {
            return autorRepository.findAllByAtivoTrue(pageable);
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao buscar autores", e);
        }
    }

    public void cadastrarAutor( Autor autor ) {
        try{
            autorRepository.save(autor);
        }
        catch (Exception e) {
            System.out.println("Erro ao cadastrar autor");
        }
    }

    public Autor pegarReferenciaPorId(Long id) {
        try {
            return autorRepository.getReferenceById(id);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Autor não encontrado com o ID: " + id, e);
        }
    }

    public void atualizarAutor( DadosAtualizacaoAutor dadosAtualizacaoAutor, Autor autor ) {
        try{
            if(dadosAtualizacaoAutor.nome() != null){
                autor.setNome(dadosAtualizacaoAutor.nome());
            }
            if(dadosAtualizacaoAutor.nacionalidade() != null){
                autor.setNacionalidade(dadosAtualizacaoAutor.nacionalidade());
            }
        } catch (Exception e){
            System.out.println("Erro ao atualizar autor");
        }

    }

    public void excluir( Autor autor ) {
        try{
            autor.setAtivo(false);
        }
        catch (Exception e){
            System.out.println("Erro ao excluir autor");
        }
    }

}
