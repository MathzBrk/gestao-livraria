package br.com.livraria.gestao_livraria.domain;

public class ValidacaoException extends RuntimeException {
    public ValidacaoException( String mensagem ) {
        super( mensagem );
    }
}
