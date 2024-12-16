package br.com.livraria.gestao_livraria.domain.cliente;

import br.com.livraria.gestao_livraria.domain.compra.Compra;
import br.com.livraria.gestao_livraria.domain.livro.Livro;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private Boolean ativo;

    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras = new ArrayList<>();

    public Cliente( DadosCadastroCliente dados ) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.ativo = true;
    }
}
