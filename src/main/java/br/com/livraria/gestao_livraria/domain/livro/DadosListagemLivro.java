package br.com.livraria.gestao_livraria.domain.livro;

public record DadosListagemLivro(Long id, String titulo, String nomeAutor, Categoria categoria, Integer anoPublicacao ) {
    public DadosListagemLivro (Livro livro){
        this(livro.getId(), livro.getTitulo(), livro.getAutor().getNome(), livro.getCategoria(), livro.getAnoPublicacao());
    }
}
