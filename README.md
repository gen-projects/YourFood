
# Plataforma de Delivery de Alimentos Saudáveis

Nosso aplicativo é uma solução digital inovadora, projetada para facilitar o acesso a opções de alimentação saudável de forma rápida e prática. Com filtros personalizados para atender diferentes perfis e preferências, a plataforma promove versatilidade e economia de tempo, permitindo que nossos clientes desfrutem de refeições equilibradas sem complicações. O objetivo principal é aliar praticidade e saúde, ajudando nossos usuários a adotar um estilo de vida mais saudável enquanto economizam tempo e recursos.

## Estrutura do Projeto

| Camada             | Elementos                                               |
| ----------------- | ---------------------------------------------------------------- |
| Model      |  Usuário, ViagemModel, CategoriaModel|
| Repository | UsuarioRepository, ViagemRepository, CategoriaRepository |
| Service      | UsuarioService, ViagemService, CategoriaService |
| Controller  | UsuarioController, ViagemController, CategoriaController |


## Documentação de Testes de API - Sistema de Delivery

Este repositório reúne os testes realizados para a API de gerenciamento de pedidos. A API fornece diversas funcionalidades, incluindo a visualização de detalhes dos pedidos, como a identificação de refeições saudáveis por ID, além de operações como criar, atualizar e cancelar pedidos. Os testes documentados aqui foram desenvolvidos para garantir o funcionamento correto de cada endpoint, assegurando a qualidade e a confiabilidade da API

## Testes Realizados


## 1. Visualizar por Saudável ou não Saudável
| Teste                | Descrição                                               | Resultado Esperado                                         |
|----------------------|---------------------------------------------------------|-----------------------------------------------------------|
| Teste Válido         | Verificar se o sistema retorna produtos válidos.        | Status HTTP 200 e lista de produtos para compra.          |
| Teste Inválido       | Verificar se o sistema retorna erro quando o produto não existe. | Status HTTP 404 e mensagem de erro adequada.              |
| Saudável ou não Saudável | Verificar a lógica de negócios relacionada à classificação de produtos como saudáveis ou não. | Lógica implementada corretamente no serviço correspondente. |



## 2. Visualizar Todos os Produtos (GET)
| Teste                     | Descrição                                                  | Resultado Esperado                            |
|---------------------------|------------------------------------------------------------|-----------------------------------------------|
| Ver todos pedidos      | Verificar se o sistema retorna todos os produtos cadastradas. | Status HTTP 200 e lista completa de viagens.  |
| Formato da lista de produtos| Verificar se a lista de produtos é retornada no formato correto. | Status HTTP 200 e formato JSON adequado.      |

## 3.Visualizar Produto por ID (GET)

| Teste       | Descrição                                                               | Resultado Esperado                                     |
|-------------|-------------------------------------------------------------------------|-------------------------------------------------------|
| ID válido   | Verificar se a produto é retornada corretamente quando um ID válido é fornecido. | Status HTTP 200 e dados da viagem correspondentes ao ID fornecido. |
| ID inválido | Verificar se o sistema retorna erro quando o ID não existe.            | Status HTTP 404 e mensagem de erro adequada.          |

## 4.Novo Produto (POST)
| Teste           | Descrição                                                                  | Resultado Esperado                                              |
|-----------------|----------------------------------------------------------------------------|------------------------------------------------------------------|
| Produtp válida   | Verificar se o sistema cadastra um produto com dados válidos.                 | Status HTTP 201 (Criado) e dados da viagem criada.              |
| Dados inválidos | Verificar se o sistema retorna erro quando dados inválidos são fornecidos. | Status HTTP 400 (Bad Request) e mensagem de erro adequada.      |


## 5. Atualizar Pedido (PUT)
| Teste               | Descrição                                                                 | Resultado Esperado                                          |
|---------------------|---------------------------------------------------------------------------|------------------------------------------------------------|
| Atualização válida  | Verificar se o sistema atualiza corretamente os dados de um produto existente. | Status HTTP 200 (OK) e dados atualizados da viagem.        |
| ID não encontrado   | Verificar se o sistema retorna erro quando o ID do produto não existe.     | Status HTTP 404 (Not Found) e mensagem de erro adequada.   |




## Stack utilizada

- Java 17  
- Spring Boot API REST  
- Spring Web  
- Spring Boot Dev Tools  
- Validation  
- Spring Data JPA  
- MySQL Driver  

## Autores

- [@Martinelii](https://www.github.com/Martinelii)
- [@biancasuarz](https://www.github.com/biancasuarz)
- [@Kagradiel](https://www.github.com/Kagradiel)
- [@DandaraaAfroo](https://www.github.com/DandaraaAfroo)
- [@GabrielJonatas](https://www.github.com/GabrielJonatas)
