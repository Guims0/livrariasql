
# 📚 LivrariaSQL - Uma Aplicação de Catálogo de Livros

Este projeto é uma **aplicação de linha de comando em Java** desenvolvida com **Spring Boot** que interage com um banco de dados relacional (PostgreSQL) para gerenciar um catálogo de livros e autores. Ele consome a API **Gutendex** para buscar informações de livros, persistindo-as localmente para consultas futuras.

---

## Recursos e Funcionalidades

A LivrariaSQL oferece um conjunto robusto de funcionalidades para a gestão e consulta de um acervo literário:

* **Busca e Persistência de Livros**: Pesquise livros por título diretamente na **API Gutendex**. Se o livro for encontrado e ainda não estiver no banco de dados, ele é automaticamente persistido, incluindo os dados do autor. Caso o livro já exista, uma mensagem informará sua presença, exibindo seus detalhes.
* **Listagem Completa de Livros**: Visualize todos os livros que foram cadastrados e armazenados em seu banco de dados local.
* **Listagem de Autores Registrados**: Obtenha uma lista completa de todos os autores que tiveram seus livros registrados na aplicação.
* **Filtragem de Autores por Ano de Vida**: Encontre autores que estavam vivos em um ano específico, ideal para pesquisar obras de um determinado período histórico.
* **Filtragem de Livros por Idioma**: Liste livros disponíveis em um idioma específico (ex: "en" para inglês, "pt" para português, "es" para espanhol, "fr" para francês).
* **Interface Interativa**: Um menu de console amigável guia o usuário através das opções disponíveis.
* **Persistência de Dados Robusta**: Utiliza **Spring Data JPA** para um mapeamento objeto-relacional eficiente, garantindo a integridade e o armazenamento seguro dos dados no PostgreSQL.

---

## Tecnologias Empregadas

Esta aplicação foi construída com um conjunto de tecnologias modernas e amplamente utilizadas no ecossistema Java:

* **Java 17+**: A linguagem de programação base para todo o projeto.
* **Spring Boot**: Framework que simplifica o desenvolvimento de aplicações Java, provendo auto-configuração e um ecossistema de bibliotecas coeso.
* **Spring Data JPA**: Abstração que facilita a interação com bancos de dados relacionais, simplificando a criação de repositórios e consultas.
* **Hibernate**: Implementação JPA padrão, responsável pelo mapeamento objeto-relacional.
* **PostgreSQL**: Sistema de gerenciamento de banco de dados relacional robusto e de código aberto, utilizado para armazenar os dados da livraria.
* **Jackson (FasterXML)**: Biblioteca para serialização e desserialização de objetos Java para e de JSON, crucial para o consumo da API Gutendex.
* **Gutendex API**: Uma API pública que fornece acesso a metadados de livros do Projeto Gutenberg, utilizada para buscar novas obras.

---

## Configuração e Execução

Para iniciar e operar esta aplicação em seu ambiente, siga os passos abaixo:

### Pré-requisitos

Certifique-se de ter os seguintes componentes instalados em sua máquina:

* **Java Development Kit (JDK) 17 ou superior**.
* **Maven** ou **Gradle** (para gerenciamento de dependências, embora o projeto seja autônomo com Spring Boot).
* Um servidor de banco de dados **PostgreSQL** em execução.

### Configuração do Banco de Dados

1.  **Crie um banco de dados** PostgreSQL para a aplicação. Por exemplo, `livrariasql`.
2.  **Atualize as configurações** no arquivo `src/main/resources/application.properties` com as credenciais do seu banco de dados PostgreSQL:

    ```properties
    spring.application.name=livrariasql
    spring.datasource.url=jdbc:postgresql://localhost/livrariasql  # Altere 'livrariasql' para o nome do seu DB, se diferente
    spring.datasource.username=postgres # Seu usuário do PostgreSQL
    spring.datasource.password=sua_senha # Sua senha do PostgreSQL
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.jpa.hibernate.ddl-auto=update # Permite que o Hibernate crie/atualize as tabelas automaticamente
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect # Garante o dialeto correto para PostgreSQL
    ```
    **Atenção**: O `hibernate.dialect` deve ser ajustado para `PostgreSQLDialect` e não `HSQLDialect` para uso com PostgreSQL.

### Compilação e Execução

Você pode executar a aplicação de algumas maneiras:

1.  **Via IDE (IntelliJ IDEA, Eclipse)**:
    * Importe o projeto como um projeto Maven ou Gradle.
    * Execute a classe `LivrariasqlApplication.java` diretamente (clicando com o botão direito e selecionando "Run").

2.  **Via Linha de Comando (Maven)**:
    * Navegue até o diretório raiz do projeto no terminal.
    * Compile o projeto:
        ```bash
        ./mvnw clean install
        ```
    * Execute a aplicação:
        ```bash
        java -jar target/livrariasql-0.0.1-SNAPSHOT.jar # O nome do arquivo JAR pode variar ligeiramente
        ```

Ao iniciar, o aplicativo exibirá o menu principal no console, permitindo que você interaja com ele.

---

## Estrutura do Projeto

A arquitetura do projeto segue uma organização modular, facilitando a manutenção e expansão:

* `src/main/java/br/alura/desafio/livros/livrariasql/`:
    * `LivrariasqlApplication.java`: A classe principal da aplicação Spring Boot.
    * `dto/`: Contém os **Data Transfer Objects (DTOs)** (`AutorDTO.java`, `LivroDTO.java`) que representam a estrutura dos dados recebidos da API Gutendex.
    * `model/`: Define as **entidades JPA** (`Autor.java`, `Livro.java`) que mapeiam as tabelas no banco de dados.
    * `principal/`: Contém a classe `Principal.java`, que gerencia a lógica de interação com o usuário (menu, chamadas de serviço, exibição de resultados).
    * `repository/`: Interfaces **Spring Data JPA** (`AutorRepository.java`, `LivroRepository.java`) para acesso e manipulação de dados no banco de dados.
    * `servico/`: Contém a classe `GutendexClient.java`, responsável por realizar as requisições HTTP à API Gutendex e processar suas respostas.

---
