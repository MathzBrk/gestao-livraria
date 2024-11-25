package br.com.livraria.gestao_livraria.domain.autor;

public record DadosListagemAutor(Long id, String nome, String nacionalidade) {
    public DadosListagemAutor(Autor autor){
        this(autor.getId(), autor.getNome(), autor.getNacionalidade());
    }
}
