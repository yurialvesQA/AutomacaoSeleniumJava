# language: pt
Funcionalidade: Preenchimento de formulário de seguro de veículo
  Como um usuário
  Eu quero preencher o formulário de seguro de veículo
  Para obter uma cotação de seguro

  Cenário: Preencher formulário de dados do veículo com validações
    Dado que estou na página inicial do formulário de seguro
    Quando preencho o formulário "Enter Vehicle Data" com dados válidos
    E valido que o campo "Cylinder Capacity" aceita apenas valores numéricos positivos
    E valido que o campo "Engine Performance" aceita apenas valores numéricos positivos
    E clico no botão "Next" da aba "Enter Vehicle Data"
    Então devo ser redirecionado para a aba "Enter Insurant Data"

  Cenário: Preencher formulário de dados do segurado
    Dado que estou na aba "Enter Insurant Data"
    Quando preencho o formulário "Enter Insurant Data" com dados válidos
    E clico no botão "Next" da aba "Enter Insurant Data"
    Então devo ser redirecionado para a próxima aba do formulário
