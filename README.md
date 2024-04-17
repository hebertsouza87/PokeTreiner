# PokeTreiner

PokeTreiner é um projeto de estudo onde é possível cadastrar um treinador de pokémon.
Ao realizar esta ação, será enviado uma mensagem para um tópico do Apache Kafka.
Um consumidor irá ouvir este tópico e irá atribuir um pokémon aleatório ao treinador cadastrado.

## Bibliotecas Utilizadas

- **Spring Boot**: Framework usado para facilitar o desenvolvimento de aplicações Spring.
- **Spring Data JPA**: Usado para persistência de dados e facilitar a interação com o banco de dados.
- **Spring Validation**: Usado para validação de dados.
- **Spring Kafka**: Usado para integração com o Apache Kafka.
- **Flyway**: Usado para migração de banco de dados.
- **PostgreSQL**: Driver JDBC para o banco de dados PostgreSQL.
- **SpringDoc OpenAPI**: Usado para gerar documentação da API.
- **JBehave**: Usado para testes de comportamento.
- **Jacoco**: Usado para cobertura de código.
- **TestContainer**: Usado para rodar testes com Docker.

## Como Rodar o Projeto

### Sem Docker

1. Certifique-se de ter o PostgreSQL instalado e rodando na sua máquina.
2. Atualize o arquivo `src/main/resources/application.properties` com as credenciais corretas do seu banco de dados
   PostgreSQL.
3. Execute o comando `./gradlew bootRun` no terminal para iniciar a aplicação.

### Com Docker

1. Certifique-se de ter o Docker e o Docker Compose instalados na sua máquina.
2. Execute o comando `./gradlew build` para gerar o arquivo.
3. Execute o comando `docker-compose up` no terminal para iniciar a aplicação.

### Recomendação

Utilize o docker-compose para subir as dependencias do projeto, mas para rodar a aplicação utilize o
comando `./gradlew bootRun`.
Ou remova o comentário da biblioteca spring-boot-docker-compose no arquivo build.gradle.
A biblioteca nem sempre funciona corretamente, mas ela serve para subir as dependencias do projeto automaticamente,
quando você executar a aplicação.

## Documentação da API

A documentação da API está disponível através do Swagger UI.
Após iniciar a aplicação, você pode acessar a documentação da API em `http://localhost:8080/swagger-ui.html`.

## Testes e Cobertura de Código

Este projeto utiliza JUnit5, JBehave, TestContainer e Jacoco para testes e cobertura de código.

### Como Rodar os Testes

Para rodar os testes, você pode usar o comando `./gradlew test`.

A comando `./gradlew check` executa todos os testes e verificações.

Você precisará ter o Docker e o Docker Compose instalados na sua máquina para rodar os testes.

### JBehave

JBehave é uma estrutura para testes de comportamento orientados por cenários.

Ele permite que você escreva testes que são descritos em uma linguagem natural, tornando-os mais legíveis e
compreensíveis.

Os testes são executados automaticamente quando você executa o comando `./gradlew test`

Após a execução dos testes, você pode visualizar o relatório de testes em `target/jbehave/view/index.html`.

### Jacoco

Jacoco é uma biblioteca usada para medir a cobertura de código de testes de aplicativos Java.

Ele fornece informações sobre quais partes do código foram executadas durante o processo de teste.

Para gerar um relatório de cobertura de código com Jacoco, você pode usar o comando `./gradlew jacocoTestReport`.

Após a execução dos testes, você pode visualizar o relatório de cobertura de código
em `build/jacocoHtml/index.html`.

O comando `./gradlew jacocoTestCoverageVerification` verifica se a cobertura de código é maior que 60%.
