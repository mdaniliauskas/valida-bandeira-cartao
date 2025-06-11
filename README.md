# Validador de Bandeiras de Cartão ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

> **Breve descrição:**  
> Aplicação Java back-end robusta para identificação e validação de bandeiras de cartão de crédito. Desenvolvida com foco em boas práticas, Clean Code e arquitetura bem estruturada para demonstrar competências em desenvolvimento back-end.

## 🚀 Tecnologias

- ![Java](https://img.shields.io/badge/Java%2011-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
- ![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apache-maven&logoColor=white)
- ![JUnit](https://img.shields.io/badge/JUnit%205-25A162?style=flat-square&logo=junit5&logoColor=white)
- ![Git](https://img.shields.io/badge/Git-F05032?style=flat-square&logo=git&logoColor=white)

## 🎯 Destaques Técnicos

### 🏗️ **Arquitetura & Design Patterns**
- **Clean Architecture**: Separação clara de responsabilidades
- **Single Responsibility Principle**: Cada classe com propósito específico
- **Strategy Pattern**: Enum com comportamentos específicos por bandeira

### 🔍 **Algoritmos & Validações**
- **Algoritmo de Luhn**: Validação matemática da integridade do cartão
- **Regex Patterns**: Identificação precisa de 9 bandeiras principais
- **Input Sanitization**: Tratamento robusto de entradas do usuário

### 🛡️ **Segurança & Boas Práticas**
- **Data Masking**: Ocultação automática de números sensíveis
- **Validation Layers**: Múltiplas camadas de validação
- **Exception Handling**: Tratamento abrangente de erros

### 🧪 **Qualidade de Código**
- **Test Coverage**: Testes unitários com JUnit 5
- **Clean Code**: Código autodocumentado e legível
- **Conventional Commits**: Padronização de commits

## ⚙️ Como executar

### Pré-requisitos
- Java 11+ e Maven 3.6+

### Instalação e execução
```bash
# Clonar o repositório
git clone https://github.com/mdaniliauskas/valida-bandeira-cartao.git
cd valida-bandeira-cartao

# Executar com Maven
mvn clean compile exec:java

# Executar testes
mvn test
```

### Compilação alternativa
```bash
javac -d . src/main/java/com/valida/cartao/*.java
java com.valida.cartao.Main
```

## 💳 Funcionalidades

**Bandeiras Suportadas**: Visa, MasterCard, Elo, American Express, Hipercard, Discover, Diners Club, JCB, UnionPay

### Exemplo de uso
```
💳 Número do cartão: 4111 1111 1111 1111

✅ Bandeira identificada: Visa
   💳 Número: 4111 **** **** 1111
   📏 Comprimento: 16 dígitos
   🔍 Algoritmo de Luhn: ✅ Válido
```

## 🏗️ Arquitetura

```
src/main/java/com/valida/cartao/
├── Main.java                    # Interface CLI
├── CardBrandIdentifier.java     # Engine de identificação
└── CardValidationService.java   # Camada de serviço
```

## 📚 Observação

Projeto desenvolvido como parte do programa **DIO + GitHub Copilot**, demonstrando aplicação de conceitos avançados de Java back-end, arquitetura limpa e boas práticas de desenvolvimento.

## 📄 Status

> ✅ Finalizado

---

<p align="center">
  <a href="https://github.com/mdaniliauskas">
    <img src="https://img.shields.io/badge/Portfólio%20GitHub-100000?style=flat-square&logo=github&logoColor=white" alt="Portfólio GitHub">
  </a>
  <a href="mailto:marcelo.daniliauskas@gmail.com">
    <img src="https://img.shields.io/badge/E--mail-D14836?style=flat-square&logo=gmail&logoColor=white" alt="E-mail">
  </a>
  <a href="https://www.linkedin.com/in/mdaniliauskas">
    <img src="https://img.shields.io/badge/LinkedIn-0A66C2?style=flat-square&logo=linkedin&logoColor=white" alt="LinkedIn">
  </a>
</p>
