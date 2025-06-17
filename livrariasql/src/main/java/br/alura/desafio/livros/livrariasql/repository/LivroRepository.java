package br.alura.desafio.livros.livrariasql.repository;

import br.alura.desafio.livros.livrariasql.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIdioma(String idioma);

    Optional<Livro> findFirstByTituloIgnoreCase(String titulo);

    boolean existsByTituloIgnoreCase(String titulo);

    List<Livro> findByTituloIgnoreCase(String titulo);
}

