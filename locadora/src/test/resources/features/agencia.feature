Feature: Gerenciamento de Agências

  Scenario: Criar uma nova agência
    Given que eu quero criar uma agência com nome "Agência Teste" e endereço "Rua Teste"
    When eu enviar uma requisição para criar a agência
    Then a resposta deve ter o status 201 e o nome "Agência Teste"

  Scenario: Buscar uma agência existente
    Given que eu tenho uma agência com nome "Agência Teste" e endereço "Rua Teste"
    When eu enviar uma requisição para buscar a agência pelo ID
    Then a resposta deve ter o status 200 e o nome "Agência Teste"

  Scenario: Atualizar uma agência existente
    Given que eu tenho uma agência com nome "Agência Teste" e endereço "Rua Teste"
    When eu enviar uma requisição para atualizar a agência para nome "Agência Atualizada" e endereço "Rua Nova"
    Then a resposta deve ter o status 200 e o nome "Agência Atualizada"

  Scenario: Excluir uma agência existente
    Given que eu tenho uma agência com nome "Agência Teste" e endereço "Rua Teste"
    When eu enviar uma requisição para deletar a agência
    Then a resposta deve ter o status 204
