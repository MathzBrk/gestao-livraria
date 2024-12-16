package br.com.livraria.gestao_livraria.domain.compra;

import java.time.LocalDate;

public record DadosListagemCompra(Long id, Long idCliente, Long idLivro, LocalDate dataCompra) {
    public DadosListagemCompra(Compra compra){
        this(compra.getId(), compra.getCliente().getId(), compra.getLivro().getId(), compra.getDataCompra());
    }
}
