package br.com.livraria.gestao_livraria.domain.compra;

import br.com.livraria.gestao_livraria.domain.cliente.Cliente;
import br.com.livraria.gestao_livraria.domain.livro.Livro;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    private LocalDate dataCompra;

    private Integer quantidade;

    private Double valorTotal;

}
