# Gestão de Livraria

## Descrição do Projeto
Este projeto é uma aplicação para gerenciar uma livraria, permitindo o cadastro, atualização, exclusão e consulta de clientes, autores e livros. A aplicação oferece uma API RESTful baseada em Spring Boot, com funcionalidades para gerenciar clientes, autores, livros e compras.

Além das operações básicas de CRUD (Create, Read, Update, Delete), a aplicação utiliza as seguintes tecnologias e práticas:
- **Autenticação**: Implementação de token JWT para segurança.
- **Validação de Dados**: Utilização de DTOs com Bean Validation.
- **Banco de Dados**: Integração com MySQL e versionamento com Flyway.
- **Documentação**: API documentada com Swagger.
- **Testes**: Testes implementados para o controller de compras.

A estrutura do projeto está organizada nos pacotes:
```
controller / infra / service / domain
```
O pacote `domain` contém subpacotes para:
```
cliente / livro / autor / usuario / compra
```

## Funcionalidades
### 1. Clientes
**Listagem de Clientes**  
A API permite a listagem paginada de clientes cadastrados na plataforma.  
- **Endpoint**: `GET /clientes`

**Cadastro de Clientes**  
Um novo cliente pode ser cadastrado, fornecendo seus dados através de um DTO de cadastro.  
- **Endpoint**: `POST /clientes`

**Atualização de Cliente**  
Clientes já cadastrados podem ter seus dados atualizados.  
- **Endpoint**: `PUT /clientes`

**Exclusão de Cliente**  
Clientes podem ser excluídos do sistema.  
- **Endpoint**: `DELETE /clientes/{id}`

**Busca por Cliente**  
Permite a busca de um cliente específico através do seu ID.  
- **Endpoint**: `GET /clientes/{id}`

- Listar livros de um cliente: `GET /clientes/{id}/livros`

### 2. Autores
**Listagem de Autores**  
A API permite a listagem paginada de autores cadastrados no sistema.  
- **Endpoint**: `GET /autores`

**Cadastro de Autores**  
Um novo autor pode ser cadastrado fornecendo os dados através do DTO de cadastro.  
- **Endpoint**: `POST /autores`

**Atualização de Autor**  
É possível atualizar os dados de um autor já cadastrado.  
- **Endpoint**: `PUT /autores`

**Exclusão de Autor**  
Permite a exclusão de um autor do sistema.  
- **Endpoint**: `DELETE /autores/{id}`

**Busca de Autor por ID**  
Permite buscar as informações detalhadas de um autor específico através de seu ID.  
- **Endpoint**: `GET /autores/{id}`

**Listagem de Livros do Autor**  
Permite listar todos os livros associados a um autor específico.  
- **Endpoint**: `GET /autores/{id}/livros`

### 3. Livros
**Listagem de Livros**  
A API permite a listagem paginada de livros disponíveis no sistema.  
- **Endpoint**: `GET /livros`

**Cadastro de Livros**  
Um novo livro pode ser cadastrado fornecendo os dados através do DTO de cadastro. É necessário que o autor do livro exista no sistema.  
- **Endpoint**: `POST /livros`

**Atualização de Livro**  
É possível atualizar os dados de um livro já cadastrado.  
- **Endpoint**: `PUT /livros`

**Exclusão de Livro**  
Permite a exclusão de um livro do sistema.  
- **Endpoint**: `DELETE /livros/{id}`

**Busca de Livro por ID**  
Permite buscar as informações detalhadas de um livro específico através de seu ID.  
- **Endpoint**: `GET /livros/{id}`

### 4. Compras
**Realização de Compras**  
Permite realizar compras de livros, validando o estoque.  
- **Endpoint**: `POST /compras`

**Consulta de Compras**  
Permite consultar compras realizadas.  
- **Endpoint**: `GET /compras`

## Tecnologias Utilizadas
- **Spring Boot**: Framework principal para desenvolvimento da API.
- **MySQL**: Banco de dados relacional para armazenamento de dados.
- **Flyway**: Controle de versionamento do banco de dados.
- **JWT**: Implementação de autenticação e autorização.
- **Swagger**: Documentação interativa da API.
- **Bean Validation**: Validação de dados com anotações em DTOs.
- **JUnit**: Testes para validação das funcionalidades.

## Como Executar
1. Clone o repositório:
   ```bash
   (https://github.com/MathzBrk/gestao-livraria)
   ```
2. Configure o banco de dados MySQL no arquivo `application.properties`.
3. Execute o Flyway para criar as tabelas no banco de dados.
4. Inicie a aplicação:
   ```bash
   mvn spring-boot:run
   ```
5. Acesse a documentação da API no Swagger:
   - **URL**: `http://localhost:8080/swagger-ui.html`

## Estrutura do Projeto
```
src
├── main
│   ├── java
│   │   ├── controller
│   │   ├── domain
│   │   │   ├── cliente
│   │   │   ├── livro
│   │   │   ├── autor
│   │   │   ├── usuario
│   │   │   ├── compra
│   │   ├── infra
│   │   └── service
│   └── resources
│       ├── db/migration
│       └── application.properties
```


