package br.com.livraria.gestao_livraria.domain.cliente;

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

    @ManyToMany
    @JoinTable(
            name = "compra",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "livro_id")
    )
    private List<Livro> livrosComprados = new ArrayList<>();

    public Cliente( DadosCadastroCliente dados ) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.ativo = true;
    }
}
