package br.com.livraria.gestao_livraria.domain.livro;

import br.com.livraria.gestao_livraria.domain.autor.Autor;
import br.com.livraria.gestao_livraria.domain.cliente.Cliente;
import br.com.livraria.gestao_livraria.domain.compra.Compra;
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
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;
    private Integer anoPublicacao;
    private Double preco;
    private Integer quantidadeEstoque;
    private Boolean disponivel;

    @OneToMany(mappedBy = "livro")
    private List<Compra> compras = new ArrayList<>();


    public Livro( DadosCadastroLivro dados , Autor autor) {
        this.titulo = dados.titulo();
        this.categoria = dados.categoria();
        this.anoPublicacao = dados.anoPublicacao();
        this.preco = dados.preco();
        this.quantidadeEstoque = dados.quantidadeEstoque();
        this.autor = autor;
        this.disponivel = true;
    }
}
