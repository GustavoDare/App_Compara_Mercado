# 🛒 Compara Mercado

O **Compara Mercado** é um aplicativo Android nativo, colaborativo e *mobile-first*, desenvolvido para ajudar consumidores a driblar a inflação através do compartilhamento de preços de itens de supermercado.

## 👥 Integrantes do Grupo
* **Gustavo Borguetti Daré** - 818723

## 🚀 Como Executar o App

Para rodar este aplicativo localmente em sua máquina, siga os passos abaixo:

**Pré-requisitos:**
* [Android Studio](https://developer.android.com/studio) (Versão Iguana ou superior recomendada).
* Emulador Android configurado ou um dispositivo físico com depuração USB ativada.
* Conexão com a internet (para a funcionalidade de buscar ofertas da API).

**Passo a Passo:**
1. Clone este repositório em sua máquina:
   ```bash
   git clone [https://github.com/SEU_USUARIO/compara-mercado.git](https://github.com/SEU_USUARIO/compara-mercado.git)


2. Abra o **Android Studio**.
3. Selecione **File > Open** e navegue até a pasta onde o repositório foi clonado.
4. Aguarde o *Gradle Sync* terminar de baixar todas as dependências do projeto.
5. Selecione o seu emulador (ou dispositivo físico) na barra superior.
6. Clique no botão de **Run (Play verde)** ou use o atalho `Shift + F10`.

## 🛠️ Tecnologias e Arquitetura

Este projeto foi construído seguindo as melhores práticas do ecossistema Android moderno:

* **Linguagem:** Kotlin
* **Interface (UI):** Jetpack Compose (Material Design 3)
* **Arquitetura:** MVVM (Model-View-ViewModel) com UDF (Unidirectional Data Flow) e padrão Repository.
* **Persistência Local:** Android Room (SQLite)
* **Requisições de Rede:** Retrofit + Gson
* **Assincronicidade:** Kotlin Coroutines & Flows

## ✅ Requisitos do Projeto Atendidos

* **[R1] Identidade Visual:** Interface moderna 100% em Jetpack Compose, padronizada com paleta de cores e componentes do Material Design 3.
* **[R2] Múltiplas Telas:** Implementação de 8 telas fluídas gerenciadas via Compose Navigation (`NavHost`).
* **[R3] Acesso à Rede:** Integração com a API pública *DummyJSON* via Retrofit, consumindo e exibindo "Ofertas Online" na tela de pesquisa.
* **[R4] Armazenamento Local:** Banco de dados relacional simulado via Room, contendo tabelas de Usuários, Produtos e Itens do Carrinho com queries personalizadas (Multi-tenant).
* **[R5] Internacionalização (i18n):** Zero strings *hardcoded*. Suporte dinâmico para Português (padrão) e Inglês, incluindo injeção de variáveis formatadas nos recursos de texto.
* **[R6] Boas Práticas e Testes:** Separação estrita em MVVM, injeção de repositórios e cobertura de testes instrumentados (JUnit4) simulando um *In-Memory Database* para validar as operações do DAO.
