# Sprint 03 | Java

## Integrantes

| Nome |  RM  |
| ---- | :--: |
| Otavio Miklos Nogueira | 554513 |
| Luciayla Yumi Kawakami | 557987 |
| João Pedro Amorim Brito | 559213 |

## Vídeo
URL: [https://youtube.com](https://youtube.com)

## Instalação

```bash
# Clone o projeto
git clone https://github.com/omininola/sprint3_java

# Vá até a pasta do projeto
cd sprint3_java

# Rode a aplicação
./mvnw spring-boot:run
```

## Execução

Entre na url: [http://localhost:8080/web/usuarios/register](http://localhost:8080/web/usuarios/register)

1. Registre um novo usuário com a role de ADMIN
2. Seu acesso será liberado para a aplicação toda
3. Se você for ADMIN, você pode criar novas Filiais e insirir Motos nelas

## Rotas

### Usuarios

| Função    | Segurança | URL |
| :-------- | :-------- | :-- |
| Cadastro  | Livre | [http://localhost:8080/web/usuarios/register](http://localhost:8080/web/usuarios/register) |
| Login     | Livre | [http://localhost:8080/web/usuarios/login](http://localhost:8080/web/usuarios/login) |
| Listagem  | Autenticado | [http://localhost:8080/web/usuarios](http://localhost:8080/web/usuarios) |
| Atualizar | Autenticado & Autorizado (ADMIN) | [http://localhost:8080/web/usuarios/atualizar/5](http://localhost:8080/web/usuarios/atualizar/5) |
| Deletar   | Autenticado & Autorizado (ADMIN) | [http://localhost:8080/web/usuarios/deletar/5](http://localhost:8080/web/usuarios/deletar/5) |

### Filiais

| Função      | Segurança   | URL |
| :---------- | :---------- | :-- |
| Listagem    | Autenticado | [http://localhost:8080/web/filiais](http://localhost:8080/web/filiais) |
| Criar Nova  | Autenticado & Autorizado (ADMIN) | [http://localhost:8080/web/filiais/new](http://localhost:8080/web/filiais/new) |
| Atualizar   | Autenticado & Autorizado (ADMIN) | [http://localhost:8080/web/filiais/atualizar/5](http://localhost:8080/web/filiais/atualizar/5) |
| Deletar     | Autenticado & Autorizado (ADMIN) | [http://localhost:8080/web/filiais/deletar/5](http://localhost:8080/web/filiais/deletar/5) |

### Motos

| Função      | Segurança   | URL |
| :---------- | :---------- | :-- |
| Listagem    | Autenticado | [http://localhost:8080/web/motos](http://localhost:8080/web/motos) |
| Criar Nova  | Autenticado & Autorizado (ADMIN) | [http://localhost:8080/web/motos/new](http://localhost:8080/web/motos/new) |
| Criar Nova a partir de uma filial | Autenticado & Autorizado (ADMIN) | [http://localhost:8080/web/motos/new/filial/1](http://localhost:8080/web/motos/new/filial/1) |
| Atualizar   | Autenticado & Autorizado (ADMIN) | [http://localhost:8080/web/motos/atualizar/5](http://localhost:8080/web/motos/atualizar/5) |
| Deletar     | Autenticado & Autorizado (ADMIN) | [http://localhost:8080/web/motos/deletar/5](http://localhost:8080/web/motos/deletar/5) |