
# 🏦 Pagamentos com Taxas e Autenticação IoT

## Evolução do Sistema de Conta Bancária – Sprint: Pagamentos, Taxas e Integração IoT

Este projeto é a continuação do sistema de **Conta Bancária**, agora expandido para incluir **pagamentos com aplicação de taxas**, **autenticação via IoT**, **segurança reforçada** e **arquitetura em camadas (DDD)**.

---

# 🧱 Arquitetura e Padrões Utilizados

* **DDD (Domain-Driven Design)**
* **Arquitetura em camadas**

  * Domain
  * Application
  * Infrastructure
  * API
* **Spring Boot + Spring Security**
* **JWT para autenticação**
* **MQTT para comunicação IoT**
* **Swagger/OpenAPI**

---

# 🧩 Novas Funcionalidades da Sprint

---

# 🔹 Módulo de Pagamentos

A entidade **Pagamento** representa qualquer operação de pagamento feita por uma conta.

### Atributos:

* `id`
* `conta` (ManyToOne)
* `boleto`
* `valorPago`
* `dataPagamento`
* `status`
* `taxas` (ManyToMany)

### Regras de negócio:

* valor debitado = **valorPago + totalDeTaxas**
* saldo insuficiente → operação falha
* boleto vencido → operação falha
* regras → **PagamentoDomainService**
* persistência/orquestração → **PagamentoAppService**

---

# 🔹 Entidade Taxa

Usada para calcular os custos de uma operação.

### Atributos:

* `id`
* `descricao`
* `percentual`
* `valorFixo` (opcional)

Apenas **gerentes** podem cadastrar taxas.

---

# 🔐 Autenticação IoT via MQTT

Para operações sensíveis, o cliente deve confirmar pelo dispositivo IoT (biometria + código aleatório).

---

# 📦 Novas Entidades IoT

## **DispositivoIoT**

* `id`
* `codigoSerial`
* `chavePublica`
* `ativo`
* relação **@OneToOne** com Cliente

## **CodigoAutenticacao**

* `id`
* `codigo`
* `expiraEm`
* `validado`
* `cliente`

---

# 🔐 Fluxo de Autenticação IoT

1. Cliente inicia um pagamento/saque/transferência
2. Backend publica no tópico:

   ```
   banco/autenticacao/{idCliente}
   ```
3. Dispositivo solicita biometria
4. Após validar, publica:

   ```
   banco/validacao/{idCliente}
   ```
5. Backend confere validade e prazo
6. Libera ou bloqueia a operação

Se expirar → **AutenticacaoIoTExpiradaException**

---

# ⚠️ Exceções Personalizadas

* `SaldoInsuficienteException`
* `PagamentoInvalidoException`
* `TaxaInvalidaException`
* `AutenticacaoIoTExpiradaException`
* `CodigoAutenticacaoInvalidoException`

Todas tratadas com **ProblemDetail** e **RestControllerAdvice**.

---

# 🔐 Regras de Segurança

* JWT obrigatório
* Perfil **cliente** → realizar pagamentos
* Perfil **gerente** → gerenciar taxas
* Endpoints `/pagamentos` e `/taxas` protegidos

---

# 🧪 Endpoints

## **Pagamentos (`/pagamentos`)**

* POST /pagamentos
* GET /pagamentos/{id}
* GET /pagamentos

## **Taxas (`/taxas`)**

* POST /taxas *(somente gerentes)*
* GET /taxas

## **IoT (`/iot`)**

* POST /iot/dispositivo
* POST /iot/codigo

Documentação completa no Swagger.

---

# 🔌 Integração MQTT

Tópicos utilizados:

```
banco/autenticacao/{idCliente}
banco/validacao/{idCliente}
```

---

# 📘 Documentação da API

Disponível em:

```
/swagger-ui.html
```

---

# 📦 Como Executar o Projeto

```bash
mvn clean install
mvn spring-boot:run
```

---


---

# 🧑‍💻 Autor

Projeto desenvolvido como parte da sprint **Pagamentos com Taxas e Autenticação IoT**.


