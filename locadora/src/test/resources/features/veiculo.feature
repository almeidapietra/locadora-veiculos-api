Feature: Gerenciamento de Veículos

  Scenario: Criar um novo veículo com sucesso
    Given que eu quero criar um veículo com modelo "Kicks" e tipo "CARRO"
    When eu enviar uma requisição para criar o veículo
    Then a resposta do veículo deve ter o status 201 e o modelo "Kicks"

  Scenario: Buscar um veículo existente por ID
    Given que eu tenho um veículo com modelo "Kicks" e tipo "CARRO"
    When eu enviar uma requisição para buscar o veículo pelo ID
    Then a resposta do veículo deve ter o status 200 e o modelo "Kicks"

  Scenario: Atualizar um veículo existente
    Given que eu tenho um veículo com modelo "Kicks" e tipo "CARRO"
    When eu enviar uma requisição para atualizar o veículo para modelo "Corolla" e tipo "CARRO"
    Then a resposta do veículo deve ter o status 200 e o modelo "Corolla"

  Scenario: Deletar um veículo existente
    Given que eu tenho um veículo com modelo "Kicks" e tipo "CARRO"
    When eu enviar uma requisição para deletar o veículo
    Then a resposta do veículo deve ter o status 204