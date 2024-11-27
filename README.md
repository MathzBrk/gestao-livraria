Gestão de Livraria
Descrição do Projeto
O Gestão de Livraria é um sistema de gestão para uma livraria, permitindo a administração de livros, autores e clientes. O sistema oferece funcionalidades como cadastro de livros, clientes e autores, associação de livros aos clientes e autenticação via JWT para controle de acesso.

Tecnologias Utilizadas
Java 17: Plataforma de desenvolvimento.
Spring Boot 3.x: Framework para criação de aplicativos Java baseados em Spring.
Spring Security: Para autenticação e autorização.
JWT (JSON Web Token): Para autenticação e controle de acesso seguro.
Spring Data JPA: Para persistência de dados em banco de dados.
MySQL: Banco de dados relacional utilizado para persistir os dados.
Flyway: Para controle de migrações do banco de dados.
Lombok: Para reduzir o boilerplate de código (como getters, setters, etc.).
Spring Boot DevTools: Para facilitar o desenvolvimento com reinicializações automáticas.
Funcionalidades
Cadastro de Livros: Cadastro de novos livros com informações como título, autor e preço.
Cadastro de Clientes: Cadastro de clientes para que possam realizar compras e associar livros a seus cadastros.
Cadastro de Autores: Cadastro de autores para os livros.
Associação de Livros a Clientes: Associar livros a um cliente após a compra.
Listagem de Livros, Clientes e Autores: Listar todos os itens cadastrados no sistema.
Autenticação via JWT: Proteção das APIs com autenticação baseada em token JWT.
Como Rodar o Projeto
1. Pré-requisitos
Java 17 ou superior.
MySQL instalado e configurado.
Postman ou outra ferramenta para testar as APIs.
2. Configuração do Banco de Dados
Crie o banco de dados livraria no MySQL:

sql
CREATE DATABASE livraria;
3. Configuração do application.properties
Configure a conexão com o banco de dados no arquivo src/main/resources/application.properties:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/livraria
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=update
spring.flyway.enabled=true
spring.security.user.name=admin
spring.security.user.password=admin_password
server.error.include-stacktrace=never

4. Rodando o Projeto
Importe o projeto na sua IDE (IntelliJ IDEA ou Eclipse).
Execute a aplicação como uma aplicação Spring Boot.

6. Testando as APIs com Postman
Após rodar o servidor, você pode testar as APIs usando o Postman. 
