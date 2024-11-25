package br.com.livraria.gestao_livraria.service;

import br.com.livraria.gestao_livraria.domain.livro.DadosAtualizacaoLivro;
import br.com.livraria.gestao_livraria.domain.livro.Livro;
import br.com.livraria.gestao_livraria.domain.livro.LivroRepository;
import br.com.livraria.gestao_livraria.infra.exception.LivroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Page<Livro> findDisponveis(Pageable pageable){
        try {
            return livroRepository.findDisponiveis(pageable);
        }
        catch(Exception e){
            System.out.println("Erro ao buscar livros disponiveis");
        }
        return null;
    }

    public void cadastrarLivro(Livro livro){
        try{
            livroRepository.save(livro);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar livro" + e.getMessage());
        }
    }


    public Livro pegarReferenciaPorId(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro com ID " + id + " não encontrado"));
    }


    public void atualizarInformacoesLivro( DadosAtualizacaoLivro dados , Livro livro) {
        try {
            if (dados.titulo() != null) {
                livro.setTitulo(dados.titulo());
            }
            if (dados.categoria() != null) {
                livro.setCategoria(dados.categoria());
            }
            if (dados.autor() != null) {
                livro.setAutor(dados.autor());
            }
            if (dados.anoPublicacao() != null) {
                livro.setAnoPublicacao(dados.anoPublicacao());
            }
            if (dados.preco() != null) {
                livro.setPreco(dados.preco());
            }
            if (dados.quantidadeEstoque() != null) {
                livro.setQuantidadeEstoque(dados.quantidadeEstoque());
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar livro" + e.getMessage());
        }
    }

    public void excluir( Livro livro ) {
        try{
            livro.setDisponivel(false);
        }
        catch (Exception e) {
            System.out.println("Erro ao excluir livro" + e.getMessage());
        }
    }

    public List<Livro> procurarLivrosPorIDs( List<Long> livrosIds ) {
        return livroRepository.findAllById(livrosIds);
    }
}
