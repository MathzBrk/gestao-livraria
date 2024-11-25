package br.com.livraria.gestao_livraria.domain.autor;

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
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String nacionalidade;
    private Boolean ativo;

    @OneToMany(mappedBy = "autor")
    private List<Livro> livros = new ArrayList<>();

    public Autor( DadosCadastroAutor autor ) {
        this.nome = autor.nome();
        this.nacionalidade = autor.nacionalidade();
        this.ativo = true;
    }
}
