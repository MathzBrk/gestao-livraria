package br.com.livraria.gestao_livraria.domain.cliente;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCliente(
        @NotBlank
        String nome,

        @NotBlank
        String cpf,

        @NotBlank
        String email,

        @NotBlank
        String telefone
) {
}
