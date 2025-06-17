package br.alura.desafio.livros.livrariasql.repository;
import br.alura.desafio.livros.livrariasql.model.Autor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @EntityGraph(attributePaths = "livros")
    List<Autor> findAll();
    @EntityGraph(attributePaths = "livros")
    List<Autor> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(int ano, int ano2);
}
