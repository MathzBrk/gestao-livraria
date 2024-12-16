package br.com.livraria.gestao_livraria.domain.livro;

import br.com.livraria.gestao_livraria.domain.autor.DadosCadastroAutor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record DadosCadastroLivro(

        @NotBlank
        String titulo,

        @NotNull
        Categoria categoria,

        @NotNull
        Long idAutor,


        @NotNull
        @Positive
        Integer anoPublicacao,

        @NotNull
        @Positive
        Double preco,

        @NotNull
        @PositiveOrZero
        Integer quantidadeEstoque

) {
}
