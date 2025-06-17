package br.alura.desafio.livros.livrariasql.servico;

import br.alura.desafio.livros.livrariasql.dto.LivroDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class GutendexClient {
    private static final String API_URL = "https://gutendex.com/books/?search=";
    private final ObjectMapper mapper = new ObjectMapper();

    public Optional<LivroDTO> buscarLivro(String titulo) {
        try {
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + titulo.replace(" ","+")))
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            var jsonNode = mapper.readTree(response.body());
            var livroNode = jsonNode.get("results").get(0);

            return Optional.of(mapper.treeToValue(livroNode, LivroDTO.class));
        } catch (Exception e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
            return Optional.empty();
        }
    }
}