package br.com.livraria.gestao_livraria.domain.compra.validacoes;

import br.com.livraria.gestao_livraria.domain.ValidacaoException;
import br.com.livraria.gestao_livraria.domain.compra.CompraRepository;
import br.com.livraria.gestao_livraria.domain.compra.DadosCadastroCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorQuantidadePositiva implements IValidarCompra {

    @Override
    public void validar( DadosCadastroCompra dados ) {
        var quantidade = dados.quantidade();

        if( quantidade < 0) {
            throw new ValidacaoException("Quantidade deve ser um número positivo!");
        }
    }
}
