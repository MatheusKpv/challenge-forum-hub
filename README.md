# Challenge Forum Hub API

API REST da aplicação Challenge Forum Hub, desenvolvida como parte do programa ONE (Oracle Next Education). A aplicação permite o gerenciamento de tópicos de um fórum, com funcionalidades de CRUD (Create, Read, Update, Delete) e autenticação de usuários via JWT.

## Funcionalidades

- **Autenticação:**
  - `POST /login`: Autenticação de um usuário e retorno de um token JWT.
- **Tópicos:**
  - `GET /topicos`: Lista todos os tópicos de forma paginada.
  - `GET /topicos/{id}`: Busca um tópico específico pelo ID.
  - `POST /topicos`: Cria um novo tópico.
  - `PUT /topicos/{id}`: Atualiza um tópico existente.
  - `DELETE /topicos/{id}`: Deleta um tópico.

## Tecnologias Utilizadas

- **Backend:**
  - Java 21
  - Spring Boot 3
  - Spring Security (com autenticação JWT)
  - Maven
  - JPA (Hibernate)
- **Banco de Dados:**
  - MySQL 8.0
  - Flyway (para migrations)
- **Outros:**
  - Docker e Docker Compose
  - Lombok
  - SpringDoc (para documentação OpenAPI/Swagger)

## Como Executar a Aplicação

### Pré-requisitos

- Docker e Docker Compose instalados.
- Java 21 e Maven 3.9+ (para execução local).

### Executando com Docker

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/seu-usuario/challenge-forum-hub.git
    cd challenge-forum-hub
    ```

2.  **Crie o arquivo `.env`:**
    Crie um arquivo `.env` na raiz do projeto, baseado no `.env.example`, e preencha as variáveis de ambiente:
    ```
    CONTAINER_NAME=challenge_forum_hub_db
    MYSQL_ROOT_PASSWORD=root
    MYSQL_PASSWORD=user
    MYSQL_PORT=3307
    JWT_SECRET=sua-chave-secreta-aqui
    ```

3.  **Suba os containers:**
    ```bash
    docker-compose up --build
    ```
    A aplicação estará disponível em `http://localhost:8181`.

### Executando Localmente

1.  **Configure o banco de dados:**
    - Inicie uma instância do MySQL.
    - Crie um banco de dados chamado `challenge_forum_hub`.

2.  **Configure as variáveis de ambiente:**
    - Configure as variáveis de ambiente no seu sistema ou na sua IDE, com as mesmas chaves do arquivo `.env.example`.

3.  **Execute a aplicação:**
    ```bash
    mvn spring-boot:run
    ```
    A aplicação estará disponível em `http://localhost:8080`.

## Documentação da API (Swagger)

A documentação da API está disponível no Swagger UI. Após iniciar a aplicação, acesse:

`http://localhost:8181/swagger-ui.html` (se executando com Docker)
ou
`http://localhost:8080/swagger-ui.html` (se executando localmente)

Na interface do Swagger, você pode testar todos os endpoints, incluindo os protegidos. Para isso, primeiro utilize o endpoint `POST /login` para obter um token JWT e depois adicione o token no botão "Authorize" no topo da página no formato `Bearer <seu-token>`.
