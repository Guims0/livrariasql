package br.alura.desafio.livros.livrariasql.principal;


import br.alura.desafio.livros.livrariasql.dto.AutorDTO;
import br.alura.desafio.livros.livrariasql.model.Autor;
import br.alura.desafio.livros.livrariasql.model.Livro;
import br.alura.desafio.livros.livrariasql.repository.AutorRepository;
import br.alura.desafio.livros.livrariasql.repository.LivroRepository;
import br.alura.desafio.livros.livrariasql.servico.GutendexClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class Principal {

    private final Scanner scanner = new Scanner(System.in);
    private final GutendexClient client = new GutendexClient();
    @Autowired
    private LivroRepository livroRepo;
    @Autowired private AutorRepository autorRepo;

    public void exibirMenu(){
        int opcao = -1;
        while (opcao != 0) {
            String menu = """
                Escolha o número de sua opção:
                1- buscar livro pelo titulo
                2- listar livros registrados
                3- listar autores registrados
                4- listar autores vivos em determinado ano
                5- listar livros em um determinado idioma
                0- sair
                """;
            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();


            switch (opcao) {
                case 1:
                    buscarLivro();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    autoresVivos();
                    break;
                case 5:
                    listarPorIdioma();
                    break;
                case 0:
                default:
                    break;
            }

        }
    }

    private void buscarLivro() {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();


        if (livroRepo.existsByTituloIgnoreCase(titulo)) {
            System.out.println("Livro já cadastrado.");
            livroRepo.findByTituloIgnoreCase(titulo).forEach(System.out::println);
            return;
        }


        client.buscarLivro(titulo).ifPresent(dto -> {
            AutorDTO autorDTO = dto.getAutores().get(0);
            Autor autor = new Autor();
            autor.setNome(autorDTO.getName());
            autor.setAnoNascimento(autorDTO.getBirthYear());
            autor.setAnoFalecimento(autorDTO.getDeathYear());

            Livro livro = new Livro();
            livro.setTitulo(dto.getTitle());
            livro.setIdioma(dto.getLanguages().get(0));
            livro.setNumeroDownloads(dto.getDownloadCount());
            livro.setAutor(autor);

            livroRepo.save(livro);

            System.out.println("\nLivro salvo com sucesso:");
            System.out.println(livro);
        });
    }



    private void listarLivros() {
        System.out.println("Livros:");
        livroRepo.findAll().forEach(System.out::println);
    }

    private void listarPorIdioma() {
        String paises = """
                Digite o idioma que deseja:
                en - inglês
                pt - português
                es - espanhol
                fr - francês
                """;
        System.out.print(paises);
        String idioma = scanner.nextLine().trim();

        var livros = livroRepo.findByIdioma(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado no idioma " + idioma + ".");
            return;
        }

        System.out.println("\nLivros no idioma " + idioma + " :");
        livros.forEach(System.out::println);
    }


    private void listarAutores() {
        System.out.println("\nAutores cadastrados:");
        var autores = autorRepo.findAll();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado.");
            return;
        }
        autores.forEach(System.out::println);
    }

    private void autoresVivos() {
        System.out.print("Ano de referência: ");
        int ano = scanner.nextInt();
        scanner.nextLine();

        var autores = autorRepo.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(ano, ano);
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor estava vivo em " + ano);
            return;
        }

        System.out.println("\nAutores vivos no ano de  " + ano + ":");
        autores.forEach(System.out::println);
    }

}
