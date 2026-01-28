# Projeto de Automação - Tricentis Vehicle Insurance

Projeto de automação de testes utilizando Java, Selenium WebDriver e Cucumber para testar o formulário de seguro de veículo da Tricentis.

## 📋 Requisitos

- Java 11 ou superior
- Maven 3.6 ou superior
- Navegador Chrome, Firefox ou Edge instalado

## 🚀 Configuração do Projeto

1. Clone ou baixe o projeto
2. Abra o terminal na raiz do projeto
3. Execute o seguinte comando para baixar as dependências:

```bash
mvn clean install
```

## 🏗️ Estrutura do Projeto

```
projetoselenium/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── tricentis/
│   │               ├── config/
│   │               │   └── WebDriverConfig.java
│   │               └── pages/
│   │                   ├── BasePage.java
│   │                   ├── EnterVehicleDataPage.java
│   │                   └── EnterInsurantDataPage.java
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── tricentis/
│       │           ├── hooks/
│       │           │   └── Hooks.java
│       │           ├── runner/
│       │           │   └── TestRunner.java
│       │           └── steps/
│       │               └── VehicleInsuranceSteps.java
│       └── resources/
│           └── features/
│               └── vehicle_insurance.feature
├── pom.xml
└── README.md
```

## 🧪 Executando os Testes

### Executar todos os testes

```bash
mvn test
```

### Executar testes específicos

Para executar um cenário específico, você pode usar tags no Cucumber. Edite o arquivo `vehicle_insurance.feature` e adicione tags aos cenários, depois execute:

```bash
mvn test -Dcucumber.filter.tags="@tagName"
```

### Executar com navegador específico

Por padrão, os testes são executados no Chrome. Para usar outro navegador:

```bash
mvn test -Dbrowser=firefox
# ou
mvn test -Dbrowser=edge
```

## 📝 Features Implementadas

### 1. Enter Vehicle Data
- Preenchimento do formulário de dados do veículo
- Validação do campo "Cylinder Capacity" (aceita apenas valores numéricos positivos)
- Validação do campo "Engine Performance" (aceita apenas valores numéricos positivos)
- Navegação para a próxima aba

### 2. Enter Insurant Data
- Preenchimento do formulário de dados do segurado
- Navegação para a próxima aba

## 🎯 Padrão PageObjects

O projeto utiliza o padrão PageObjects, onde cada página do formulário possui sua própria classe:

- **BasePage**: Classe base com métodos comuns para todas as páginas
- **EnterVehicleDataPage**: PageObject para a aba "Enter Vehicle Data"
- **EnterInsurantDataPage**: PageObject para a aba "Enter Insurant Data"

## ✅ Validações Implementadas

### Enter Vehicle Data

1. **Cylinder Capacity**: Valida que o campo aceita apenas valores numéricos positivos entre 1 e 10000
2. **Engine Performance**: Valida que o campo aceita apenas valores numéricos positivos entre 1 e 2000

## 🔧 Configurações

### WebDriverConfig

A classe `WebDriverConfig` gerencia a inicialização do WebDriver usando WebDriverManager, que baixa automaticamente os drivers necessários.

### Hooks

A classe `Hooks` contém os métodos `@Before` e `@After` do Cucumber para configurar e limpar o ambiente de teste.

## 📊 Relatórios

Após a execução dos testes, os relatórios serão gerados em:

### Relatórios Cucumber
- **HTML**: `target/cucumber-reports.html` - Relatório HTML padrão do Cucumber
- **JSON**: `target/cucumber-reports.json` - Relatório em formato JSON para integração

### Extent Reports
- **HTML**: `target/extent-reports/ExtentReport.html` - Relatório visual interativo com gráficos e estatísticas

### Screenshots
- **Localização**: `target/screenshots/` - Screenshots capturados durante a execução dos testes
- Screenshots são capturados automaticamente em:
  - Cada step importante do teste
  - Antes e depois de cliques em botões
  - Em caso de falha do cenário
  - Após validações

Os screenshots são nomeados com prefixos numéricos para facilitar a identificação da ordem de execução:
- `01_PaginaInicial.png`
- `02_SelecionadoMake.png`
- `03_SelecionadoModel.png`
- etc.

## 🐛 Troubleshooting

### Erro: Driver não encontrado
O WebDriverManager deve baixar automaticamente os drivers. Se houver problemas, verifique sua conexão com a internet.

### Erro: Elemento não encontrado
Verifique se os seletores estão corretos. Os IDs dos elementos podem variar dependendo da versão do site.

### Timeout em elementos
Ajuste o tempo de espera na classe `WebDriverConfig` se necessário.

## 📚 Tecnologias Utilizadas

- **Java 11**: Linguagem de programação
- **Selenium WebDriver 4.15.0**: Framework de automação web
- **Cucumber 7.14.0**: Framework BDD
- **JUnit 5**: Framework de testes
- **WebDriverManager 5.6.2**: Gerenciamento automático de drivers
- **Extent Reports 5.0.9**: Geração de relatórios HTML interativos
- **Maven**: Gerenciamento de dependências

## 📸 Captura de Evidências

O projeto captura automaticamente screenshots em pontos estratégicos:

1. **Início de cada cenário**: Screenshot da página inicial
2. **Preenchimento de formulários**: Screenshots após cada campo importante
3. **Validações**: Screenshots após validações de campos
4. **Navegação**: Screenshots antes e depois de cliques em botões
5. **Falhas**: Screenshot automático quando um cenário falha

Todos os screenshots são salvos em `target/screenshots/` e anexados automaticamente aos relatórios.

