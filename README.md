# Aplicação: Tokyo Finances
![alt text](./readme-assets/imagem_sistema.png)

# SOBRE O PROJETO
Trata-se do desenvolvimento de um **sistema de agendamento de transferências financeiras** - **Tokyo Finances** - capaz de realizar aplicação de taxas - seguindo regras de negócio - e emitir extrato ao usuário de todos os agendamentos cadastrados.
O escopo do projeto seguira como POC (_proof of concept_), tendo como utilização banco em memória e arquitetura monolitica modular, trazendo agilidade sem perder a capacidade de crescimento futuro.

## Tecnologias
<table>
  <thead align="center">
    <tr border: none;>
      <td><b>BACKEND</b></td>
      <td><b>FRONTEND</b></td>
      <td><b>BANCO DE DADOS</b></td>
    </tr>
  </thead>
  <tbody align="center">
    <tr>
      <td>
        <img alt="" src="https://img.shields.io/badge/java-sdk11-black?style=for-the-badge&logo=coffeescript&logoColor=white&label=java&labelColor=red&color=black"/>
        <img alt="" src="https://img.shields.io/badge/springboot-V2.7.18-black?style=for-the-badge&logo=springboot&logoColor=white&label=springboot&labelColor=green&color=black"/>
      </td>
      <td>
        <img alt="" src="https://img.shields.io/badge/node-24.14.1LTS-blue?style=for-the-badge&logo=nodedotjs&logoColor=white&labelColor=green&color=black"/>
        <img alt="" src="https://img.shields.io/badge/vue.js-3.5.31-blue?style=for-the-badge&logo=vuedotjs&logoColor=white&labelColor=green&color=black"/>
      </td>
      <td>
        <img alt="" src="https://img.shields.io/badge/h2database-blue?style=for-the-badge&logo=h2database&logoColor=white&labelColor=blue&color=blue"/>
      </td>
    </tr>
  </tbody>
</table>

# EXECUTANDO CÓDIGO
## Backend
Na raiz do projeto `tokyofinances-backend`, execute os seguintes comando:

Instalando dependencias e building
```
mvn clean install
```

Inicializando servidor backend
```
mvnw spring-boot:run
```

Com o servidor inicializado, os seguintes endpoins podem ser acessados via Postman/Insomnia:
- Base URL: http://localhost:8080/api
- GET /contas: Busca todas as contas cadastradas
- GET /transferencias: Extrato das transferências agendadas
- POST /transferencias: Realiza agendamento

Request payload para realização de agendamento:
```
{
    "contaOrigem": "1111111111", // Necessita ser 10 caracteres
    "contaDestino": "2222222222", // Necessita ser 10 caracteres
    "valor": "50.00",
    "dataTransferencia": "2027-04-03"
}
```

## Frontend
Antes de instalar as dependencias e iniciar o servidor de frontend, certifique-se que esteja:
- Utilizando **Node versão 24 LTS**
- **Na raiz no projeto `tokyofinances-frontend`**
- **Servidor backend rodando** e na porta **8080**, conforme configuração padrão

Instalando dependencias
```
npm install
```

Rodando o servido frontend
```
npm run dev
```

# REQUISITOS DO SOFTWARE
## Introdução
**Tokyo Finances**, cliente ficticio do ramo financeiro, passará a fornecer serviços de **agendamento de transferência financeira** e, para isso, demandará de uma prova de conceito para pontuar melhorias no serviço, assim como, visualização de integração na jornada do prestador em seu sistema existente.

A priori, a POC contará com serviços de agendamento e consulta a extrato de todos os agendamentos cadastrados.

Para o desenvolvimento de uma Prova de Conceito (POC - Proof of Concept) do sistema de agendamentos de transferência do _Tokyo Finances_, o princípio que norteará o desenvolvimento será o **KISS** - Keep it Simple... - tendo um enfoque em um produto minimamente utilizável, para futuramente receber iterações e melhorias.

## Personas
- Usuário: Prestador de serviços da Tokyo Finances, o qual realizará os agendamentos e poderá ver o extrado de todos os agendamentos cadastros
- Sistema: Serviço especifico ao agendamento de transferência financeira da Tokyo Finances, o qual esse documento faz referência

## Escopo
Prova de Conceito (POC), tendo como MVP o serviço de agendamento de transgerência financeira e a tela de utilização do usuário a nível de homologação.

## Requisitos Especificos
### Funcionalidade
#### 1. Agendar transferencia financeira
O usuário deve conseguir agendar transgerências financeiras, fornecendo:
- Conta de Origem
- Conta de Destino
- Valor de transferência
- Taxa aplicada
- Data da transferência
- Data de agendamento

Especificação dos termos:
- Contas: Tanto origem quanto destino, devem possuir o padrão de 10 digitos
- Data da transferência: Data a qual a transferência será realizada. Data futura.
- Data de agendamento: Data a qual o agendamento foi cadastrado. _Timestamp_

#### 2. Consultar extrato completo
O usuário deve conseguir ver o extrato de todos os agendamentos cadastrados.

#### 3. Calcular taxa
O sistema deve calcular - e aplicar - taxas seguindo as seguintes regras:
- Taxa deverá ser aplicada sobre o valor a ser transferido (Taxa sob Valor de Transferência)
- A porcentagem das taxas deve seguir a tabela de transferência

Tabela de Transferência
| Dias Transferência (De) | Dias Transferência (Até) |  Valor transferência (R$) | Taxa |
| :---                    | :---                     | :---                      | :--- |
| 0                       | 0                        | 3,00                      | 2,5% |
| 1                       | 10                       | 12,00                     | 0,0% |
| 11                      | 20                       | 0,00                      | 8,2% |
| 21                      | 30                       | 0,00                      | 6,9% |
| 31                      | 40                       | 0,00                      | 4,7% |
| 41                      | 50                       | 0,00                      | 1,7% |

#### 4. Alertas sobre transferência não permitida
O sistema deverá emitir alertas ao usuário de transferência não permitida caso não haja taxa aplicável.
A transferência, então, não deverá ser realizada

### Diagrama de caso de uso
![alt text](./readme-assets/diagrama_caso_uso.png)

## Arquitetura e design

### Monolito Modular e Dominio
Para arquitetura do sistema, utilizou-se o Monolito Modular em função da agilidade na produção de uma POC, além na redução da complexidade em infraestrutura e observabilidade - consequentemente no custo do sistema - após o kickoff. Com o crescimento do projeto, aumento no numero de deploys e/ou necessidade de se trabalhar com diferentes linguagens no backend, a modularidade da arquitetura possibilitará na separação dos módulos com baixo potencial de impacto no código em execução.

Os modulos foram separados seguindo a lógica de Bounded Contexts do Domain Driven Design, sendo, para esta POC, o contexto "Agendamento" e o contexto "Conta".

![alt text](./readme-assets/diagrama_hexagon_ddd-DDD_E_MONOLITO_MODULAR.drawio.png)

![alt text](./readme-assets/diagrama_hexagon_ddd-PLANO_MONOLITO_FUTURO.drawio.png)

### Hexagonal
Como arquitetura de código, optou-se pelo Hexagonal (Ports and Adapters) pelo auto nível de desacoplamento, possibilitando maior facilidade em separação dos módulos caso haja a necessiadde, protegendo a camada de dominio.

Mescla-se a estrutura hexagonal com a linguagem do Domain Driven Design pelo ganho de padronização e identificação de terminologias de negócio (linguagem ubíqua).

![alt text](./readme-assets/diagrama_hexagon_ddd-DIAGRAMAPORTSADAPT.drawio.png)

### Test Driven Development direcionado
Com enfoque no equilibrio entre agilidade e segurança, focou-se na realização do TDD no ponto central da POC com maior carga de regra de negócio, sendo na lógica do calculo dos testes.

### Strategy Pattern no calculo de taxa
Para realização do calculo das taxas conforme tabela de transferência, adotou-se o Padrão de Design Strategy por conta da possibilidade de aplicar regras de negócio especificas para cada situação, além de possibilitar acrescentar novas regras com maior facilidade, reduzindo boilerplate.

![alt text](./readme-assets/image.png)

## Referencias
- Monolito Modular: https://www.milanjovanovic.tech/blog/what-is-a-modular-monolith
- Diagrama de caso de uso: https://medium.com/operacionalti/uml-diagrama-de-casos-de-uso-29f4358ce4d5
- Microserviços: https://microservices.io/patterns/microservices.html
- DDD: https://pablo-christian.medium.com/ddd-o-que-%C3%A9-o-domain-driven-design-de-um-jeito-simples-93ea0c9a111
- Ports and Adapters: https://www.arhohuttunen.com/hexagonal-architecture-spring-boot/
- Clean Code: MARTIN, Robert C. Clean Code: A Handbook of Agile Software Craftsmanship. 1. ed. Boston: Prentice Hall, 2008. ISBN 0132350882.