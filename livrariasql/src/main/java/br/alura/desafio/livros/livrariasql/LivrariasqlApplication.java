package br.alura.desafio.livros.livrariasql;

import br.alura.desafio.livros.livrariasql.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LivrariasqlApplication implements CommandLineRunner {
	@Autowired
	private Principal livroService;
	public static void main(String[] args) {
		SpringApplication.run(LivrariasqlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		livroService.exibirMenu();
	}
}
