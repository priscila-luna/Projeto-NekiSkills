
# API Neki Skills

## Tecnologias utilizadas
- Lombok
- Spring Security
- Tokens JWT

## Swagger
A documentação do Swagger pode ser encontrada em: 
```
http://localhost:8080/swagger-ui/index.html
```
Lá você haverá todos os endpoints da aplicação e suas especificações.


## Como rodar?


### Primeira Iniciação

Por padrão, o tipo de criação é `create` para que seja criado as tabelas no banco. Além disso, na primeira vez é criado um usuário Administrador e o banco é populado com algumas Skills.

#### Dados do Administrador padrão.

| Login | Password |
|-------|----------|
| admin | admin    |

Para mudar as credenciais padrões ou a skills, basta editar o arquivo [Import.sql](https://github.com/priscila-luna/Projeto-NekiSkills/blob/main/server/src/main/resources/import.sql)

Logo após a inicialização, é recomendado atualizar para `Update` a propriedade `spring.jpa.hibernate.ddl-auto
`, encontrada no [Application.Properties](https://github.com/priscila-luna/Projeto-NekiSkills/blob/main/server/src/main/resources/application.properties)

⚠️ Lembre-se de atualizar a conexão com seu banco local postgres no arquivo Application.Properties

### Demais usos

Basta iniciar a aplicação normalmente, utilizando a IDE preferida.

Por padrão, a aplicação é iniciada em `http://localhost:8080/`
