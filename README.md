# Fotoexpress API - Tech Challenge FIAP

Bem-vindo ao repositório do projeto "Fotoexpress", desenvolvido como parte do desafio tecnológico da FIAP. Este projeto visa implementar uma API para uma empresa de fotografia, utilizando princípios de Domain-Driven Design (DDD), EventStorming e Clean Code.

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.10-brightgreen?style=flat&logo=spring&logoColor=white)
![Java 17](https://img.shields.io/badge/Java-17-blue?style=flat&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.8.5-orange?style=flat&logo=apachemaven&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat&logo=mysql&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-3.0-brightgreen?style=flat&logo=swagger&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-20.10-blue?style=flat&logo=docker&logoColor=white)

## Sumário

- [Visão Geral](#visão-geral)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Como Executar o Projeto](#como-executar-o-projeto)
- [Endpoints da API](#endpoints-da-api)
- [Contribuindo](#contribuindo)
- [Licença](#licença)

## Visão Geral

O projeto consiste na criação de uma API para gerenciar processos e operações de uma empresa de fotografia. O desenvolvimento foi guiado pelos princípios de DDD para uma melhor organização do código e arquitetura, EventStorming para uma compreensão clara dos eventos e fluxos do sistema, e Clean Code para garantir um código limpo e sustentável.

[Event Storming](https://miro.com/app/board/uXjVK6a5xfM=/?share_link_id=868388996904)
[Wiki](URL-do-Link)
[Documentação](URL-do-Link)


## Estrutura do Projeto

- **/src/main/java**: Código fonte da aplicação.
- **/src/main/resources**: Arquivos de configuração e recursos.
- **/src/test/java**: Testes unitários e de integração.
- **/docker**: Arquivos de configuração do Docker.

## Como Executar o Projeto

Para rodar o projeto localmente, siga os passos abaixo:

1. **Clone o repositório:**

    ```bash
    git clone https://github.com/LuizRomao02/tech-challenger-fiap.git
    ```

2. **Navegue até o diretório do projeto:**

    ```bash
    cd tech-challenger-fiap
    ```

3. **Construa o projeto com Maven:**

    ```bash
    mvn clean install
    ```

4. **Inicie a aplicação localmente:**

    ```bash
    mvn spring-boot:run
    ```

5. **Para executar usando Docker:**

    - **Construa a imagem Docker:**

        ```bash
        docker build -t fotoexpress .
        ```

    - **Execute o container Docker:**

        ```bash
        docker run -p 8080:8080 fotoexpress
        ```

    - **Para iniciar o banco de dados MySQL com Docker:**

        ```bash
        docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=fotoexpressdb -p 3306:3306 -d mysql:latest
        ```

## Endpoints da API

A API pode ser explorada e testada utilizando o Swagger. A documentação está disponível em:

http://localhost:8080/api-docs

## Contribuindo

Contribuições são bem-vindas! Para contribuir com o projeto, por favor siga estes passos:

1. Faça um fork do repositório.
2. Crie uma branch para sua feature ou correção (`git checkout -b feature/nova-feature`).
3. Faça commit das suas mudanças (`git commit -am 'Adiciona nova feature'`).
4. Envie suas alterações para o repositório (`git push origin feature/nova-feature`).
5. Abra um pull request.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
