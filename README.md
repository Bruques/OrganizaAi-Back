Organiza Ai API 📊   
Uma API REST robusta e intuitiva para controle financeiro, desenvolvida com Java e Spring Boot, projetada para auxiliar Microempreendedores Individuais (MEIs) e Trabalhadores Autônomos a gerenciar suas finanças de forma eficiente.

Este projeto foi inicialmente concebido como uma iniciativa de estudo aprofundado em desenvolvimento backend, mas evoluiu com o propósito de oferecer uma ferramenta prática para registro e gerenciamento de receitas e despesas, além de um dashboard consolidado para uma visão clara da saúde financeira.



✨Funcionalidades Principais

- Autenticação Segura (JWT): Registro e login de usuários com tokens JWT para acesso seguro aos dados.

- Gestão de Receitas: Adicione, liste, atualize e delete suas fontes de renda.

- Gestão de Despesas: Registre, liste, atualize e delete todos os seus gastos.

- Dashboard Financeiro:

- Total de receitas e despesas.

- Cálculo de saldo mensal.

- Estimativa de imposto (atualmente fixado em 6% das receitas).

- Análise de receitas por categoria.



🛠️ Tecnologias Utilizadas

- Java 17

- Spring Boot

- Spring Security com JWT

- MongoDB

- JUnit 5

- Maven


🔒 Autenticação

A aplicação usa autenticação com JWT (JSON Web Token). Os usuários precisam se registrar e fazer login para obter um token JWT. Esse token deve ser enviado no header de autorização (Authorization: Bearer token) para acessar os endpoints protegidos.

Endpoints de Autenticação

POST /auth/register - Cria um novo usuário.

Requisição:
```
{
  "name": "Bruno Marques",
  "email": "bruno@email.com",
  "password": "123456"
}
```

POST /auth/login - Retorna o token JWT.

Requisição:
```
{
  "email": "bruno@email.com",
  "password": "123456"
}
```

Resposta:
```
{
  "token": "eyJhbGciOiJIUzI1NiJ9..." 
}
```

Segurança com Spring Security

Endpoints públicos: /auth/**

Todos os outros endpoints exigem token JWT válido.

Token JWT é validado em cada requisição.


🚀 Endpoints

Receitas

- POST /api/v1/income

- GET /api/v1/income

- PUT /api/v1/income/{id}

- DELETE /api/v1/income/{id}

Despesas

- POST /api/v1/expense

- GET /api/v1/expense

- PUT /api/v1/expense/{id}

- DELETE /api/v1/expense/{id}


Dashboard

GET /api/v1/dashboard?mes={}&ano={}


📦 Estrutura de Pacotes

com.OrganizaAi

├── config

├── controller

├── dto

├── model

├── repository

├── service

└── tests

🧪 Testes

Os testes unitários são implementados com JUnit 5.

Cada método de controller e service possui pelo menos um 👀 teste cobrindo cenário feliz.

Arquivos de teste estão localizados em: src/test/java/...

💡 Observações

- Todos os valores monetários são armazenados com BigDecimal no banco de dados para manter a precisão financeira.

- A transformação para double é feita apenas no DashboardDTO, por ser um objeto voltado para exibição.

- O token de autenticação JWT é utilizado para identificar o userId e garantir acesso aos dados corretos.

▶️ Como Rodar o Projeto

- Clone o repositório

- git clone https://github.com/seuusuario/controle-financeiro-api.git

- Configure o MongoDB (local ou Atlas)

- Configure o application.properties ou application.yml:

- spring.data.mongodb.uri=mongodb+srv://usuario:senha@cluster.mongodb.net/seubanco

▶️ Execute a aplicação

./mvnw spring-boot:run

Acesse os endpoints usando Postman, Insomnia ou outro cliente REST.

📞 Contato

- Desenvolvido por Bruno Marques

- Email: brunonmarques98@gmail.com

- LinkedIn: https://linkedin.com/in/bruno-marques-12b497219/


- Este projeto faz parte de um sistema completo de organização financeira para pequenos empreendedores, com planos futuros de expansão para aplicativo móvel e funcionalidades de IA.
