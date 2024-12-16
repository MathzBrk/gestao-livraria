package br.com.livraria.gestao_livraria.service;

import br.com.livraria.gestao_livraria.domain.ValidacaoException;
import br.com.livraria.gestao_livraria.domain.cliente.ClienteRepository;
import br.com.livraria.gestao_livraria.domain.compra.Compra;
import br.com.livraria.gestao_livraria.domain.compra.CompraRepository;
import br.com.livraria.gestao_livraria.domain.compra.DadosCadastroCompra;
import br.com.livraria.gestao_livraria.domain.compra.DadosListagemCompra;
import br.com.livraria.gestao_livraria.domain.compra.validacoes.IValidarCompra;
import br.com.livraria.gestao_livraria.domain.livro.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private List<IValidarCompra> validadores;

    public DadosListagemCompra realizarCompra( DadosCadastroCompra dados ) {
        if(!clienteRepository.existsById(dados.idCliente())){
            throw new ValidacaoException("ID do cliente informado não existe!");
        }

        if(!livroRepository.existsById(dados.idLivro())){
            throw new ValidacaoException("ID do livro informado não existe!");
        }

        var cliente = clienteRepository.getReferenceById(dados.idCliente());
        var livro = livroRepository.getReferenceById(dados.idLivro());

        validadores.forEach(v -> v.validar(dados));

        livro.setQuantidadeEstoque(livro.getQuantidadeEstoque() - dados.quantidade());
        livroRepository.save(livro);

        var compra = new Compra(null, cliente, livro, dados.dataCompra(), dados.quantidade(), livro.getPreco() * dados.quantidade());
        compraRepository.save(compra);

        return new DadosListagemCompra(compra);
    }

    public Page<Compra> devolverCompras(Pageable pageable) {
        try {
            return compraRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar compras: " + e.getMessage(), e);
        }
    }
}
