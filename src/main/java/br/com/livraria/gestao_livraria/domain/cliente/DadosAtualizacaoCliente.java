package br.com.livraria.gestao_livraria.domain.cliente;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCliente(
        @NotNull
        Long id,

        String nome,
        String cpf,
        String telefone,
        String email
) {
}
