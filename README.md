# 🛒 Sistema de E-commerce - API RESTful

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white)

## 📌 Sobre o Projeto
Uma API REST desenvolvida em Java com Spring Boot para gerenciar o catálogo e estoque de um sistema de e-commerce. Este projeto foi criado com o objetivo de aplicar conceitos modernos de arquitetura de back-end, persistência de dados em bancos relacionais e conteinerização de infraestrutura.

## 🚀 Tecnologias Utilizadas
* **Linguagem:** Java 21
* **Framework:** Spring Boot 3 (Web, Data JPA)
* **Banco de Dados:** PostgreSQL (Conteinerizado)
* **Infraestrutura:** Docker & Docker Compose
* **Gerenciamento de Dependências:** Maven

## ⚙️ Como Executar

### Pré-requisitos
* Ter o [Java JDK 21+](https://www.oracle.com/java/technologies/downloads/) instalado.
* Ter o [Docker Desktop](https://www.docker.com/products/docker-desktop/) instalado e rodando.

### Passo a Passo

1. **Clone o repositório:**
```bash
git clone [https://github.com/braiandaros/SistemaEcommerce.git](https://github.com/braiandaros/SistemaEcommerce.git)
cd SistemaEcommerce
```

2. **Suba o Banco de Dados (Docker):**
```bash
docker-compose up -d
```

3. **Inicie a Aplicação (Spring Boot):**
```bash
./mvnw spring-boot:run
```
> A API estará disponível em: `http://localhost:8080`

---

## 🛠️ Endpoints da API (CRUD de Produtos)

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/api/produtos` | Cadastra um novo produto |
| `GET` | `/api/produtos` | Lista todos os produtos cadastrados |
| `GET` | `/api/produtos/{id}` | Busca um produto específico pelo ID |
| `PUT` | `/api/produtos/{id}` | Atualiza os dados de um produto existente |
| `DELETE` | `/api/produtos/{id}` | Deleta um produto do banco de dados |

### Exemplo de Payload (JSON)
Para cadastrar ou atualizar um produto, envie o seguinte corpo na requisição:
```json
{
  "nome": "Monitor 27 Polegadas",
  "preco": 1500.00
}
```

---
*Desenvolvido com dedicação por [Braian Daros](https://github.com/braiandaros).*
