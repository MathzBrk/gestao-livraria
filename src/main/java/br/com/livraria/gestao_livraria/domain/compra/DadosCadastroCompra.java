package br.com.livraria.gestao_livraria.domain.compra;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record DadosCadastroCompra(
        @NotBlank
        Long idCliente,

        @NotBlank
        Long idLivro,

        @NotBlank
        LocalDate dataCompra,

        @NotBlank
        @Positive
        Integer quantidade
) {
}
