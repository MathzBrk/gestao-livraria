package br.com.livraria.gestao_livraria.infra.exception;

public class LivroNaoEncontradoException extends RuntimeException {
    public LivroNaoEncontradoException(String mensagem) {super(mensagem);
    }
}

