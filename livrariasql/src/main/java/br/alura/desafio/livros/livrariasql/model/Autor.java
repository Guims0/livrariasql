package br.alura.desafio.livros.livrariasql.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Livro> livros = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("""
            -------------------------------
            Nome: %s
            Nascimento: %s
            Falecimento: %s
            Livros:
            """.formatted(nome,
                anoNascimento != null ? anoNascimento : "Desconhecido",
                anoFalecimento != null ? anoFalecimento : "Desconhecido"));

        if (livros == null || livros.isEmpty()) {
            sb.append("  Nenhum livro registrado.\n");
        } else {
            for (Livro livro : livros) {
                sb.append("(( ").append(livro.getTitulo()).append(" ))").append("\n");
            }
        }

        return sb.toString();
    }

}
