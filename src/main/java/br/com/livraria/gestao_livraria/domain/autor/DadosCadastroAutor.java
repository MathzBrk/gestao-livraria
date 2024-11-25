package br.com.livraria.gestao_livraria.domain.autor;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroAutor(
        @NotBlank
        String nome,

        @NotBlank
        String nacionalidade
) {
}
