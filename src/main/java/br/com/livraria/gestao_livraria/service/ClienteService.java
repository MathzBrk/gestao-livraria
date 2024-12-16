package br.com.livraria.gestao_livraria.service;

import br.com.livraria.gestao_livraria.domain.cliente.Cliente;
import br.com.livraria.gestao_livraria.domain.cliente.ClienteRepository;
import br.com.livraria.gestao_livraria.domain.cliente.DadosAtualizacaoCliente;
import br.com.livraria.gestao_livraria.domain.livro.DadosListagemLivro;
import br.com.livraria.gestao_livraria.domain.livro.Livro;
import br.com.livraria.gestao_livraria.domain.livro.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private LivroRepository livroRepository;


    public Page<Cliente> findClientes( Pageable pageable ) {
        try{
            return clienteRepository.findByAtivoTrue(pageable);
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao buscar os clientes", e);
        }
    }

    public void cadastrarCliente( Cliente cliente ) {
        try{
            clienteRepository.save(cliente);
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao cadastrar o cliente", e);
        }
    }

    public Cliente pegarReferenciaPorId( Long id ) {
        try {
            return clienteRepository.getReferenceById(id);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
    }

    public void atualizarCliente( DadosAtualizacaoCliente dados, Cliente cliente ) {
        try{
            if(dados.nome() != null){
                cliente.setNome(dados.nome());
            }
            if(dados.email() != null){
                cliente.setEmail(dados.email());
            }
            if(dados.telefone() != null){
                cliente.setTelefone(dados.telefone());
            }
            if(dados.cpf() != null){
                cliente.setCpf(dados.cpf());
            }
        } catch (Exception e){
            System.out.println("Erro ao atualizar o cliente");
        }
    }

    public void excluir( Cliente cliente ) {
        try{
            cliente.setAtivo(false);
        } catch (Exception e){
            System.out.println("Erro ao excluir o cliente");
        }
    }


}
