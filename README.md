# Sprint 03 | Java Playground

## Rodando

```bash
# Clone o projeto
git clone https://github.com/omininola/sprint3_java_playground

# Vá até a pasta do projeto
cd sprint3_java_playground

# Rode o Docker Compose
docker compose up -d --build
```

## Testes

Entre na url: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

1. Registre um novo usuário com a role de ADMIN

```json
{
  "senha": "string",
  "email": "string@gmail.com",
  "role": "ADMIN"
}
```

2. Copie o token recebido
3. Clique no botão escrito 'Authorize' na parte superior direita
4. Cole o token e confirme
5. Agora você tera acesso a todos os endpoints :D

## Bonus

Para conseguir acessar a API pelo front, vai ser preciso inserir o token no header da request

Ex.
```javascript
const response = await fetch(`${API_URL_BASE}/filiais`, {
  headers: {
    "Content-Type": "application/json",
    Authorization: `Bearer ${token}`,
  },
});
```

Esse token deve ser usado em todas as requests que não sejam relacionadas a /usuarios/login ou /usuarios/register

Vale lembrar também que o token expira, então caso você tenha o token em mãos e ele mesmo assim não funciona, tente logar novamente (Isso vai gerar um novo token)
