package edu.adatech.locadoradeveiculos.steps;

import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

public class AgenciaSteps {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private CucumberSpringConfiguration config; // Injeção da configuração

    private String nome;
    private String endereco;
    private Long idAgencia;
    private HttpStatusCode status;
    private String resposta;

    @Given("que eu quero criar uma agência com nome {string} e endereço {string}")
    public void definirDadosDaAgencia(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    @Given("que eu tenho uma agência com nome {string} e endereço {string}")
    public void prepararAgenciaExistente(String nome, String endereco) {
        Map<String, String> novaAgencia = new HashMap<>();
        novaAgencia.put("nome", nome);
        novaAgencia.put("endereco", endereco);

        resposta = webClientBuilder.baseUrl("http://localhost:" + config.getPort() + "/api")
                .build()
                .post()
                .uri("/agencias")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers -> headers.setBasicAuth("admin", "admin"))
                .bodyValue(novaAgencia)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        idAgencia = JsonPath.parse(resposta).read("$.id", Long.class);
    }

    @When("eu enviar uma requisição para criar a agência")
    public void enviarRequisicaoCriar() {
        Map<String, String> novaAgencia = new HashMap<>();
        novaAgencia.put("nome", nome);
        novaAgencia.put("endereco", endereco);

        try {
            resposta = webClientBuilder.baseUrl("http://localhost:" + config.getPort() + "/api")
                    .build()
                    .post()
                    .uri("/agencias")
                    .contentType(MediaType.APPLICATION_JSON)
                    .headers(headers -> headers.setBasicAuth("admin", "admin"))
                    .bodyValue(novaAgencia)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            status = HttpStatusCode.valueOf(201);
        } catch (Exception e) {
            status = HttpStatusCode.valueOf(500);
        }
    }

    @When("eu enviar uma requisição para buscar a agência pelo ID")
    public void enviarRequisicaoBuscar() {
        try {
            resposta = webClientBuilder.baseUrl("http://localhost:" + config.getPort() + "/api")
                    .build()
                    .get()
                    .uri("/agencias/" + idAgencia)
                    .headers(headers -> headers.setBasicAuth("admin", "admin"))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            status = HttpStatusCode.valueOf(200);
        } catch (Exception e) {
            status = HttpStatusCode.valueOf(500);
        }
    }

    @When("eu enviar uma requisição para atualizar a agência para nome {string} e endereço {string}")
    public void enviarRequisicaoAtualizar(String novoNome, String novoEndereco) {
        Map<String, String> agenciaAtualizada = new HashMap<>();
        agenciaAtualizada.put("nome", novoNome);
        agenciaAtualizada.put("endereco", novoEndereco);

        try {
            resposta = webClientBuilder.baseUrl("http://localhost:" + config.getPort() + "/api")
                    .build()
                    .put()
                    .uri("/agencias/" + idAgencia)
                    .contentType(MediaType.APPLICATION_JSON)
                    .headers(headers -> headers.setBasicAuth("admin", "admin"))
                    .bodyValue(agenciaAtualizada)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            status = HttpStatusCode.valueOf(200);
        } catch (Exception e) {
            status = HttpStatusCode.valueOf(500);
        }
    }

    @When("eu enviar uma requisição para deletar a agência")
    public void enviarRequisicaoDeletar() {
        try {
            webClientBuilder.baseUrl("http://localhost:" + config.getPort() + "/api")
                    .build()
                    .delete()
                    .uri("/agencias/" + idAgencia)
                    .headers(headers -> headers.setBasicAuth("admin", "admin"))
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();

            status = HttpStatusCode.valueOf(204);
        } catch (Exception e) {
            status = HttpStatusCode.valueOf(500);
        }
    }

    @Then("a resposta deve ter o status {int} e o nome {string}")
    public void validarResposta(int statusEsperado, String nomeEsperado) {
        assert status.value() == statusEsperado : "Status esperado: " + statusEsperado + ", recebido: " + status.value();
        assert JsonPath.parse(resposta).read("$.nome", String.class).equals(nomeEsperado) :
                "Nome esperado: " + nomeEsperado + ", recebido: " + JsonPath.parse(resposta).read("$.nome", String.class);
    }

    @Then("a resposta deve ter o status {int}")
    public void validarStatus(int statusEsperado) {
        assert status.value() == statusEsperado : "Status esperado: " + statusEsperado + ", recebido: " + status.value();
    }
}
