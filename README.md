# INOVE

Inovação Online para Vivências Educacionais

#### Contextualização

No cenário educacional atual, a utilização de ferramentas de ensino e metodologias inovadoras tem se mostrado cada vez mais essencial para promover uma experiência de aprendizado efetiva e engajadora. Este projeto surge da necessidade de acompanhar o avanço tecnológico e inovar no processo de ensino.

#### Objetivo do Projeto

O objetivo do projeto INOVE é desenvolver uma plataforma educacional online voltada especificamente para professores, proporcionando ferramentas inovadoras e metodologias pedagógicas mais ativas e personalizadas.

#### Resultados esperados

- Aumento do rendimento das aulas: Melhor engajamento dos alunos com o uso de ferramentas diversificadas.

- Facilidade de acesso a informações: Professores terão fácil acesso a dados importantes sobre seus alunos.

- Plataforma intuitiva: Desenvolver um sistema de fácil utilização que contribua diretamente para o processo de ensino e aprendizagem.

- Capacitação dos professores: Participantes serão treinados para o uso da plataforma e das ferramentas inovadoras.
  Monitoramento contínuo: Avaliação constante dos resultados para garantir a qualidade e a relevância do projeto.

#### Nome dos Membros do Projeto

```bash
Diego Ribeiro Araújo
Flávio Diniz de Sousa
Italo Gonçalves Meireles Faria
João Gabriel de Oliveira Meireles
José Antonio Ribeiro Souto
Pedro Henrique Marques
```

#### Logo do Projeto

#### Instruções de Uso do Projeto

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
