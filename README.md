**Monitor de Consumo Energético Residencial** — Aplicação Web Full-Stack estruturada no padrão MVC com persistência em MySQL.

## Propósito do Projeto
Este sistema foi desenvolvido como um projeto prático para o 3º semestre de Engenharia de Software na Universidade de Mogi das Cruzes (UMC). O objetivo principal é gerenciar eletrodomésticos de uma casa inteligente e calcular de forma automatizada o **consumo mensal estimado em kWh**, baseado na potência (Watts) e nas horas de uso diário de cada dispositivo.

### Diferencial de Regra de Negócio (UX)
O sistema possui um analisador automático de consumo: dispositivos que ultrapassam o limite saudável de **50 kWh/mês** recebem dinamicamente um destaque visual vermelho (`table-danger` do Bootstrap) na listagem, alertando o usuário sobre os principais focos de gasto na residência.

## Arquitetura e Critérios Técnicos Atendidos

O projeto foi construído seguindo rigorosamente as diretrizes arquiteturais e de segurança exigidas:

1. **Padrão MVC Strict:** Separação total de responsabilidades. A View (JSP) foca no visual, o Controller (Servlet) processa as regras de negócio e cálculos, e o Model encapsula os dados.
2. **Segurança (Prevenção a SQL Injection):** Todas as operações de persistência na camada DAO utilizam exclusivamente a interface `PreparedStatement` para sanitização dos dados.
3. **CRUD Completo:** Implementação das quatro operações fundamentais integradas ao banco de dados:
   * **Create:** Cadastro via formulário estruturado (`index.html`).
   * **Read:** Listagem dinâmica com filtro global em tempo real via JavaScript (`resultadoconsultartodos.jsp`).
   * **Update:** Edição assistida com busca por ID e preenchimento prévio automático dos campos (`resultadoconsultaratualizar.jsp`).
   * **Delete:** Exclusão física com confirmação de segurança nativa do navegador.

## Stack Tecnológica
* **Back-end:** Java 25 (JDK 25), Servlets e JSP (JavaServer Pages).
* **Front-end:** HTML5, CSS3, JavaScript Vanilla e Bootstrap 5.
* **Banco de Dados:** MySQL 8.0+ com driver JDBC (`mysql-connector-j`).
* **IDE/Build:** NetBeans IDE com suporte a Apache Ant.
* **Servidor:** Apache Tomcat 8.5+.

## Estrutura do Banco de Dados

Execute o script abaixo no seu ambiente MySQL antes de iniciar a aplicação:

create database if not exists smart_home_db;
use smart_home_db;

create table if not exists dispositivos (
    id int auto_increment primary key,
    nome varchar(100) not null,
    comodo varchar(50) not null,
    marca varchar(50),
    potencia double not null,
    horas_uso double not null,
    voltagem int not null,
    status varchar(20) not null,
    consumo_mensal double not null,
    observacao varchar(255)
);
