# Smart Home - Monitor de Consumo

Projeto prático de Java Web desenvolvido durante o 3º semestre de Engenharia de Software na UMC. 

O sistema é um CRUD focado no gerenciamento de eletrodomésticos residenciais. Além do cadastro e controle básicos, a aplicação possui uma lógica que calcula automaticamente a estimativa de consumo mensal de energia (em kWh) de cada aparelho cadastrado.

## Funcionalidades
- **CRUD Completo:** Cadastro, leitura, atualização e exclusão de dispositivos.
- **Cálculo Automático:** O sistema usa a potência em Watts e as horas de uso diário para calcular o consumo final no mês.
- **Alerta de Consumo:** Aparelhos que gastam mais de 50 kWh no mês recebem um destaque em vermelho na tabela para alertar o usuário.
- **Busca Dinâmica:** Filtro de pesquisa na listagem de aparelhos usando JavaScript.

## Tecnologias e Arquitetura
- Java 25 (Servlets e JSP)
- Padrão arquitetural MVC
- Banco de dados MySQL (Integração via JDBC)
- Prevenção a SQL Injection utilizando PreparedStatement
- Interface com HTML, CSS e Bootstrap 5

## Estrutura do Banco de Dados
Para rodar a aplicação, crie o banco e a tabela executando o script abaixo no seu MySQL:

```sql
create database if not exists smart_home_db;
use smart_home_db;

create table if not exists dispositivos (
    id int auto_increment primary key,
    nome varchar(50) not null,
    comodo varchar(40) not null,
    marca varchar(30),
    potencia double not null,
    horas_uso double not null,
    voltagem int not null,
    status varchar(20) not null,
    consumo_mensal double not null,
    observacao varchar(255)
);
