# Sprint 03 | Java Playground

## Rodando

```bash
# Clone o projeto
git clone https://github.com/omininola/sprint3_java

# Vá até a pasta do projeto
cd sprint3_java

# Rode o Docker Compose
docker compose up -d --build
```

## Testes

Entre na url: [http://localhost:8080/web/usuarios/register](http://localhost:8080/web/usuarios/register)

1. Registre um novo usuário com a role de ADMIN

```json
{
  "senha": "string",
  "email": "string@gmail.com",
  "role": "ADMIN"
}
```

2. Seu acesso será liberado para a aplicação toda
3. Se você for ADMIN, você pode criar novas Filiais e insirir Motos nelas