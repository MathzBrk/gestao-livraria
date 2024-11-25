package br.com.livraria.gestao_livraria.domain.livro;

import br.com.livraria.gestao_livraria.domain.autor.Autor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record DadosAtualizacaoLivro(
        @NotNull
        Long id,
        String titulo,
        Categoria categoria,
        Autor autor,
        Integer anoPublicacao,

        @Positive
        Double preco,

        @PositiveOrZero
        Integer quantidadeEstoque
){

}
