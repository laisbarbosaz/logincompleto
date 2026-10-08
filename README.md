# Sistema de Login - Portal Escolar 

Sistema de login desenvolvido com Java 21, Spring Boot, Spring Security, Thymeleaf e MongoDB Atlas.

## 1. Pré-requisitos
- **Java JDK 21** ou superior instalado
- **Maven** (opcional, o projeto já inclui o Wrapper `./mvnw`)
- **Conta no MongoDB Atlas** (Cluster M0 gratuito)


## 2. Configuração do MongoDB Atlas
1. Acesse o [MongoDB Atlas](https://www.mongodb.com/cloud/atlas) e crie uma conta ou faça login.
2. Crie um cluster gratuito (M0) na região de sua preferência (ex: AWS sa-east-1 / São Paulo).
3. Na aba **Database Access**:
   - Crie um usuário de banco de dados com permissão de leitura e escrita (`Read and write to any database`).
   - Guarde o usuário e a senha definidos, baixe o env se ele der opção.
4. Na aba **Network Access**:
   - Adicione seu IP atual ou configure `0.0.0.0/0` para permitir acesso de qualquer IP durante o desenvolvimento.
5. Na aba **Database / Clusters**:
   - Clique em **Connect** > **Drivers**.
   - Copie a string de conexão (URI), que possui o seguinte formato: mongodb+srv://<usuario>:<senha>@cluster0.xxx.mongodb.net/
     

## 3. Configuração do Ambiente Local
Para manter as credenciais seguras e fora do Git:
1. Vá até a pasta de recursos do projeto (`src/main/resources/`).
2. Duplique o arquivo `application-local.properties.example` e renomeie a cópia para `application-local.properties`.
3. Abra o arquivo `application-local.properties` e preencha com seus dados do MongoDB Atlas e com a senha desejada para o usuário administrador inicial, esse arquivo vai constar no gitignore:

## 4. Após configurar, executar o sistema
1. Executar com: .\mvnw spring-boot:run
2. Após o início da aplicação, acesse no navegador: http://localhost:8080
3. Tem credenciais de exemplo em exemplos-login.txt

## Sobre os perfis
O sistema possui três perfis de acesso (Roles):
  ROLE_ALUNO: Perfil padrão para novos cadastros. Acessa apenas a Dashboard básica.
  ROLE_PROFESSOR: Acessa a Dashboard e o Painel do Professor.
  ROLE_ADMIN: Acessa a Dashboard, o Painel do Professor e a página de Gerenciamento de Usuários. Pode promover usuários ou desativar contas.
