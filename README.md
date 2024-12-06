# 🚗 API Locadora de Veículos
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/almeidapietra/locadora-veiculos-api/blob/main/LICENSE)

Este projeto foi desenvolvido como trabalho final da Formação Back-end ADA TECH. Uma API REST para gerenciamento de uma locadora de veículos, desenvolvida em Java com Spring Boot.

## 👥 Integrantes do Grupo
- Gustavo de Queiroz
- Mayara Santos
- Pietra Almeida
- Rosângela Batista

## 🛠 Tecnologias Utilizadas
- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven
- JUnit
- Swagger/OpenAPI

## 📋 Funcionalidades Principais
- Cadastro e gerenciamento de veículos
- Cadastro e gerenciamento de clientes
- Sistema de aluguel de veículos
- Controle de disponibilidade de veículos
- Cálculo automático de valores de locação
- Gestão de devolução de veículos

## 🚀 Como Executar o Projeto

### Pré-requisitos
- Java 17 ou superior
- Maven

### Passos para Execução
1. Clone o repositório
2. Navegue até a pasta do projeto
3. Execute o comando: `mvn spring-boot:run`
4. A aplicação estará disponível em `http://localhost:8080`

## 📊 Banco de Dados

O projeto utiliza o H2 Database, um banco de dados em memória. Para acessar o console do H2:

1. Acesse `http://localhost:8080/h2-console`
2. Configure a conexão:
   - Driver Class: `org.h2.Driver`
   - JDBC URL: `jdbc:h2:file:./locadora_veiculos`
   - User Name: `sa`
   - Password: (deixe em branco)

## 📝 Documentação da API

A documentação completa da API está disponível através do Swagger UI:
- URL: `http://localhost:8080/swagger-ui.html`

## 🧪 Testes

O projeto inclui testes unitários e de integração. Para executar os testes:

```bash
mvn test
```

### Cobertura de Testes
- Testes unitários para todas as camadas (Controller, Service, Repository)
- Testes de integração para fluxos completos
- Validação de regras de negócio
- Testes de casos de sucesso e erro

## 📌 Endpoints Principais

### Veículos
- GET /api/veiculos - Lista todos os veículos
- POST /api/veiculos - Cadastra novo veículo
- PUT /api/veiculos/{id} - Atualiza veículo
- DELETE /api/veiculos/{id} - Remove veículo

### Clientes
- GET /api/clientes - Lista todos os clientes
- POST /api/clientes - Cadastra novo cliente
- PUT /api/clientes/{id} - Atualiza cliente
- DELETE /api/clientes/{id} - Remove cliente

### Aluguéis
- POST /api/alugueis - Realiza novo aluguel
- PUT /api/alugueis/{id}/devolucao - Registra devolução
- GET /api/alugueis - Lista todos os aluguéis

### Agências
- POST /api/agencias - Cria nova ageência
- PUT /api/agencias/{id}/ Atualiza agência por ID
- GET /api/agencias - Lista todos as agências
- DELETE /api/agencias/{id} - Remove agência

## 🤝 Contribuição

Este é um projeto acadêmico desenvolvido como trabalho final. Contribuições, sugestões e melhorias são sempre bem-vindas.

