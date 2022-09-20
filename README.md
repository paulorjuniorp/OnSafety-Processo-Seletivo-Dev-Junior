# OnSafety-Processo-Seletivo-Dev-Junior
Aplicação para registro de pessoas em um CRUD

## OBJETIVOS
1. Desenvolver uma aplicação em JAVA Web para gestão de Pessoas;
2. Disponibilizar o código completo do projeto em um repositório GIT;

## ESPECIFICAÇÕES
A aplicação consiste em uma API REST, com os dados persistidos em um banco de dados com
interface que permita o CRUD dos registros.
A aplicação deve ser capaz de auto configurar sua base na inicialização, ou deve conter um
README com os passos necessários para sua execução correta.
Incluir ao menos uma validação na interface, uma na API e uma no banco de dados;
Validar e formatar CPF;
Obrigatória a utilização do Spring Boot no backend e banco de dados MySql.

# 📑Requisitos
Para o desenvolvimento deste projeto foram utilizadas as ferramentas:

1. Spring Boot 2.7.3
  * Lombok
  * Spring Boot DevTools
  * Validation
  * MySQL Driver
  * Spring Data JPA
  * Spring Web
  * Swagger 2.9.0
2. Angular 14.2.0
  * Angular Material 14.2.2
3. MySQL 8.0.27
4. Maven 3.2.0


# 🚀Executando o projeto
##Para executar a API é necessário:
1. Entrar na pasta projectonsafety;
2. Abrir a pasta no terminal ou Windows PowerShell;
3. Digitar o comando: mvn clean install;
4. Logo após entrar na pasta target;
5. Por fim, digitar o comando: java -jar peoplemanagement-0.0.1.jar
O endereço da API é **``http://localhost:8081``** banco de dados é auto configurável 

##Para executar a interface é necessário:
1. Entrar na pasta onsafetyinterface;
2. Abrir a pasta no terminal, ou Windows PowerShell, ou VS Code; 
3. Digitar o comando: npm install
4. Por fim, digitar o comando: ng serve
O aplicativo front-end é acessado pelo endereço **``http://localhost:4200``**

# :fireworks: Interface
## Tela de início
![imagem1](https://user-images.githubusercontent.com/26314416/191150859-f3654340-5704-4d7a-976f-87f3f5009208.png)
## Tela de listagem dos usuários
![imagem2](https://user-images.githubusercontent.com/26314416/191152063-fe010c6b-87be-49b5-b23e-1514bb7c3e79.png)
## Tela de cadastro
![imagem3](https://user-images.githubusercontent.com/26314416/191152120-a77d3c04-27eb-4643-8c48-2ec56b6e4337.png)
obs: A tela de atualização muda apenas o texto do botão de cadastro
## Tela de exclusão
![imagem4](https://user-images.githubusercontent.com/26314416/191152136-6f5626e1-ea00-4e5c-ab07-b1a83e39b15d.png)

# 📝Documentação
Para documentação das API's foi utilizado o framework Swagger.

Com os serviços em execução, a documentação das API's pode ser acessada em:

http://localhost:8081/swagger-ui.html
