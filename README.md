# JadeMG Backend

## Configuração de Segurança

### Variáveis de Ambiente
1. Copie o arquivo `.env.example` para `.env`:
```bash
cp .env.example .env
```

2. Configure as variáveis de ambiente no arquivo `.env`:
- `DB_URL`: URL de conexão com o banco de dados
- `DB_USERNAME`: Usuário do banco de dados
- `DB_PASSWORD`: Senha do banco de dados
- `JWT_SECRET`: Chave secreta para JWT (mínimo 32 caracteres)
- `JWT_EXPIRATION`: Tempo de expiração do token em milissegundos
- `ADMIN_USERNAME`: Nome de usuário do administrador
- `ADMIN_PASSWORD`: Senha do administrador

### Segurança
- Nunca comite o arquivo `.env` no repositório
- Use senhas fortes em produção
- Mantenha o JWT_SECRET seguro e único para cada ambiente
- Em produção, use HTTPS
- Altere as credenciais padrão antes de implantar

### Desenvolvimento
1. Instale as dependências:
```bash
./mvnw install
```

2. Execute o projeto:
```bash
./mvnw spring-boot:run
```

### Produção
- Altere todas as senhas e chaves antes de implantar
- Configure um banco de dados seguro
- Use variáveis de ambiente do servidor em vez de arquivo .env
- Configure HTTPS
- Implemente rate limiting
- Monitore logs de segurança 