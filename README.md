Organiza Ai, API de Controle Financeiro para MEIs e Autônomos

Esta é uma API REST desenvolvida com Java e Spring Boot inicialmente com o objetivo de estudar mais sobre backend, mas também com o objetivo de auxiliar microempreendedores individuais (MEIs) e trabalhadores autônomos no controle de suas finanças. A aplicação permite o registro e gerenciamento de receitas e despesas, e fornece um dashboard consolidado com saldo, imposto estimado e análise por categoria.

Tecnologias Utilizadas

Java 17

Spring Boot

MongoDB

JUnit 5

Maven

Funcionalidades

1. Receitas

- Criar nova receita

- Listar receitas

2. Despesas

- Criar nova despesa

- Listar despesas

3. Dashboard

Total de receitas

Total de despesas

Saldo mensal

Imposto estimado (6% das receitas) - ainda fixado

Estrutura de Pacotes

com.seuprojeto
├── config
├── controller
├── dto
├── model
├── repository
├── service
└── tests

Endpoints

Receitas

POST /api/v1/income
GET /api/v1/income
PUT /api/v1/income/{id}
DELETE /api/v1/income/{id}

Despesas

POST /api/v1/expense
GET /api/v1/expense
PUT /api/v1/expense/{id}
DELETE /api/v1/expense/{id}

Dashboard

GET /api/v1/dashboard?mes={}&ano={}

Testes

Os testes unitários são implementados com JUnit 5.

Cada método de controller e service possui pelo menos um 👀 teste cobrindo cenário feliz.

Arquivos de teste estão localizados em: src/test/java/...

Observações

Todos os valores monetários são armazenados com BigDecimal no banco de dados para manter a precisão financeira.

A transformação para double é feita apenas no DashboardDTO, por ser um objeto voltado para exibição.

O token de autenticação JWT é utilizado para identificar o userId e garantir acesso aos dados corretos.

Como Rodar o Projeto

Clone o repositório

git clone https://github.com/seuusuario/controle-financeiro-api.git

Configure o MongoDB (local ou Atlas)

Execute a aplicação

./mvnw spring-boot:run

Acesse os endpoints usando Postman, Insomnia ou outro cliente REST.

Contato

Desenvolvido por Bruno Marques

Email: brunonmarques98@gmail.com

LinkedIn: https://linkedin.com/in/bruno-marques-12b497219/
Este projeto faz parte de um sistema completo de organização financeira para pequenos empreendedores, com planos futuros de expansão para aplicativo móvel e funcionalidades de IA.
