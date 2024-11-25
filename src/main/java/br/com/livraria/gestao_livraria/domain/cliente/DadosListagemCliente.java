package br.com.livraria.gestao_livraria.domain.cliente;

import br.com.livraria.gestao_livraria.domain.livro.DadosListagemLivro;

public record DadosListagemCliente(Long id, String nome, String cpf, String email, String telefone) {
    public DadosListagemCliente(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getTelefone());
    }
}
