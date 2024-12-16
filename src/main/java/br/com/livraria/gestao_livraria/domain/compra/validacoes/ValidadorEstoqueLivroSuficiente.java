package br.com.livraria.gestao_livraria.domain.compra.validacoes;

import br.com.livraria.gestao_livraria.domain.ValidacaoException;
import br.com.livraria.gestao_livraria.domain.compra.DadosCadastroCompra;
import br.com.livraria.gestao_livraria.domain.livro.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorEstoqueLivroSuficiente implements IValidarCompra{


    @Autowired
    LivroRepository livroRepository;

    @Override
    public void validar( DadosCadastroCompra dados ) {
        var livro = livroRepository.getReferenceById(dados.idLivro());


        if (livro.getQuantidadeEstoque() < dados.quantidade()) {
            throw new ValidacaoException("Livro está fora de estoque!");
        }


    }
}
