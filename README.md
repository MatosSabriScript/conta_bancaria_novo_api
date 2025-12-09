🏦 Pagamentos com Taxas e Autenticação IoT
Evolução do Sistema de Conta Bancária – Sprint: Pagamentos, Taxas e Integração IoT

Este projeto é a continuação do sistema de Conta Bancária, agora expandido para incluir pagamentos com aplicação de taxas financeiras, autenticação avançada via IoT, melhorias de segurança e arquitetura em camadas seguindo DDD.

📌 Objetivo da Sprint

Aprimorar o sistema bancário já desenvolvido, adicionando:

Módulo de pagamentos (boletos e contas de serviço)

Cálculo automático de taxas financeiras

Novas entidades de domínio (Pagamento, Taxa, DispositivoIoT, CodigoAutenticacao)

Integração com um dispositivo IoT via MQTT

Autenticação multifator para operações sensíveis

Documentação completa via Swagger

Regra de acesso baseada em perfis (cliente / gerente)

Tratamento padronizado de exceções com ProblemDetail

🧱 Arquitetura e Padrões Utilizados

O sistema segue conceitos de:

DDD (Domain-Driven Design)

Arquitetura em camadas

Domain: regras de negócio e serviços de domínio

Application: orquestração, DTOs, casos de uso

Infrastructure: repositórios JPA, MQTT, segurança

API: controllers REST

Spring Boot + Spring Security

Autenticação JWT

Swagger/OpenAPI para documentação

MQTT (paho / HiveMQ) para comunicação IoT

🧩 Novas Funcionalidades
1. 🔹 Módulo de Pagamentos

Foi criada a entidade Pagamento, representando qualquer operação de pagamento realizada a partir de uma conta.

Pagamento

Atributos principais:

id

conta (ManyToOne)

boleto

valorPago

dataPagamento

status (SUCESSO, FALHA, SALDO_INSUFICIENTE etc.)

taxas (ManyToMany com Taxa)

Regras:

O valor debitado = valorPago + totalDeTaxas

Se saldo insuficiente → operação falha

Se boleto vencido → operação falha

Toda lógica central fica no PagamentoDomainService

Persistência e orquestração no PagamentoAppService

2. 🔹 Entidade Taxa

Usada para definir custos variáveis sobre o pagamento.

Taxa

id

descricao (ex: IOF, Tarifa Bancária, Conversão Internacional)

percentual

valorFixo (opcional)

Apenas gerentes podem cadastrar e gerenciar taxas.

3. 🔹 Autenticação IoT via MQTT

Para operações sensíveis (pagamentos, saques, transferências), o sistema exige uma confirmação IoT por biometria.

Foram criadas duas novas entidades:

DispositivoIoT

id

codigoSerial

chavePublica

ativo

Relacionamento @OneToOne com Cliente

CodigoAutenticacao

id

codigo

expiraEm

validado

cliente

🔐 Fluxo de Autenticação IoT

O cliente inicia uma operação sensível

O backend publica no tópico:

banco/autenticacao/{idCliente}


O dispositivo IoT solicita a biometria

Ao validar, publica:

banco/validacao/{idCliente}


O backend verifica:

se o código é válido

se está no prazo

Libera ou bloqueia a operação

Se o tempo expirar → AutenticacaoIoTExpiradaException

⚠️ Exceções Personalizadas

Seguindo padrão com RestControllerAdvice + ProblemDetail:

SaldoInsuficienteException

PagamentoInvalidoException

TaxaInvalidaException

AutenticacaoIoTExpiradaException

CodigoAutenticacaoInvalidoException

Entre outras...

🔐 Segurança

Autenticação: JWT

Autorização:

clientes → realizar pagamentos

gerentes → gerenciar taxas e consultar dados sensíveis

Todos endpoints /pagamentos e /taxas exigem token válido

🧪 Endpoints (Principais)
Pagamentos (/pagamentos)

POST /pagamentos – criar pagamento

GET /pagamentos/{id} – consultar pagamento

GET /pagamentos – listar

Taxas (/taxas)

POST /taxas – criar taxa (somente gerentes)

GET /taxas – listar

IoT (/iot)

POST /iot/dispositivo – registrar dispositivo

POST /iot/codigo – gerar código de autenticação

Toda documentação está disponível no Swagger.

🔌 Integração MQTT

O backend usa um broker MQTT para:

publicar solicitações de autenticação

assinar confirmações de validação

Tópicos utilizados:

banco/autenticacao/{idCliente}
banco/validacao/{idCliente}

📘 Documentação da API

Disponível em:

/swagger-ui.html


ou

/api-docs

✔️ Objetivos de Aprendizagem da Sprint

Aplicar DDD na modelagem de regras complexas

Criar serviços de domínio e aplicação

Usar Spring Security com JWT

Integrar com IoT via MQTT

Implementar cálculos financeiros e validações

Documentar tudo profissionalmente com Swagger

Criar um sistema robusto, próximo de um ambiente real

📦 Como Executar o Projeto
mvn clean install
mvn spring-boot:run

🧑‍💻 Autor

Projeto desenvolvido como parte da sprint de Pagamentos e Autenticação IoT do curso de desenvolvimento.
