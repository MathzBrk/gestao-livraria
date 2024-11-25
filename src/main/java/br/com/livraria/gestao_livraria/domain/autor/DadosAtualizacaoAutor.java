package br.com.livraria.gestao_livraria.domain.autor;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoAutor(
        @NotNull
        Long id,

        String nome,
        String nacionalidade

) {
}
