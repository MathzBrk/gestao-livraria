package br.com.livraria.gestao_livraria.domain.livro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByTituloContaining( String titulo);

    @Query("SELECT l from Livro l WHERE l.quantidadeEstoque >= 1 and l.disponivel = true ")
    Page<Livro> findDisponiveis( Pageable pageable);

    @Query("SELECT l FROM Livro l JOIN l.clientes c WHERE c.id = :clienteId")
    Page<Livro> findLivrosByClienteId( @Param("clienteId") Long clienteId, Pageable pageable);



}
