package br.alura.desafio.livros.livrariasql.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String idioma;
    private int numeroDownloads;

    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(int numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    @Override
    public String toString() {
        return """
            -------------------------------
            Título: %s
            Autor: %s
            Idioma: %s
            Downloads: %d
            """.formatted(titulo, autor.getNome(), idioma, numeroDownloads);
    }
}
