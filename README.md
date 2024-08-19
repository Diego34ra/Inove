# Inove
 Sistema de educação inovador

#### Requisitos mínimos para rodar o projeto:

1. **Java:** Certifique-se de ter o Java JDK (versão 17) instalado.

2. **Banco de Dados:** Um banco de dados MySQL (Versão 8).

### EXECUTAR O SISTEMA

#### Para executar o Sistema, siga estas etapas:

1. **Configuração do Banco de Dados:**
    - Configurar o arquivo de conexão com o banco de dados `src/main/resource/application.yml` com as credenciais do banco de dados.
    - Configurar o `username` com o usuário, e `password` com a senha do servidor de banco de dados da maquina em que a API será executada.

2. **Executar o Main Class:**
    - Abra o arquivo `InoveApplication.java` localizado em `com.ifgoiano.inove` no seu IDE.
    - Execute o método `main` na classe `Main` para inicializar o Sistema.

3. **Ao Iniciar:**
    - A base de dados será Criada automaticamente, Graças as Migrações do Flyway.
    - As operações do CRUD podem ser testadas tanto pelo Postman quanto pelo Swagger(Recomendado).
    - Link de acesso ao Swagger: `localhost:8080/swagger-ui/index.html`.