package edu.adatech.locadoradeveiculos.steps;

import com.jayway.jsonpath.JsonPath;
import edu.adatech.locadoradeveiculos.enums.TipoVeiculo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

public class VeiculoSteps {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private CucumberSpringConfiguration config;

    private String modelo;
    private TipoVeiculo tipo;
    private Long idVeiculo;
    private HttpStatusCode status;
    private String resposta;

    @Given("que eu quero criar um veículo com modelo {string} e tipo {string}")
    public void definirDadosDoVeiculo(String modelo, String tipo) {
        this.modelo = modelo;
        this.tipo = TipoVeiculo.valueOf(tipo.toUpperCase());
    }

    @Given("que eu tenho um veículo com modelo {string} e tipo {string}")
    public void prepararVeiculoExistente(String modelo, String tipo) {
        Map<String, String> novoVeiculo = new HashMap<>();
        novoVeiculo.put("modelo", modelo);
        novoVeiculo.put("tipo", tipo.toUpperCase());

        resposta = webClientBuilder.baseUrl("http://localhost:" + config.getPort() + "/api")
                .build()
                .post()
                .uri("/veiculos/cadastrar") // Corrigido para a rota correta
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers -> headers.setBasicAuth("admin", "admin"))
                .bodyValue(novoVeiculo)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        idVeiculo = JsonPath.parse(resposta).read("$.id", Long.class);
    }

    @When("eu enviar uma requisição para criar o veículo")
    public void enviarRequisicaoCriar() {
        Map<String, String> novoVeiculo = new HashMap<>();
        novoVeiculo.put("modelo", modelo);
        novoVeiculo.put("tipo", tipo.name());

        try {
            resposta = webClientBuilder.baseUrl("http://localhost:" + config.getPort() + "/api")
                    .build()
                    .post()
                    .uri("/veiculos/cadastrar") // Corrigido para a rota correta
                    .contentType(MediaType.APPLICATION_JSON)
                    .headers(headers -> headers.setBasicAuth("admin", "admin"))
                    .bodyValue(novoVeiculo)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            status = HttpStatusCode.valueOf(201);
        } catch (Exception e) {
            status = HttpStatusCode.valueOf(500);
        }
    }

    @When("eu enviar uma requisição para buscar o veículo pelo ID")
    public void enviarRequisicaoBuscar() {
        try {
            resposta = webClientBuilder.baseUrl("http://localhost:" + config.getPort() + "/api")
                    .build()
                    .get()
                    .uri("/veiculos/" + idVeiculo) // Corrigido para a rota correta
                    .headers(headers -> headers.setBasicAuth("admin", "admin"))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            status = HttpStatusCode.valueOf(200);
        } catch (Exception e) {
            status = HttpStatusCode.valueOf(500);
        }
    }

    @When("eu enviar uma requisição para atualizar o veículo para modelo {string} e tipo {string}")
    public void enviarRequisicaoAtualizar(String novoModelo, String novoTipo) {
        Map<String, String> veiculoAtualizado = new HashMap<>();
        veiculoAtualizado.put("modelo", novoModelo);
        veiculoAtualizado.put("tipo", novoTipo.toUpperCase());

        try {
            resposta = webClientBuilder.baseUrl("http://localhost:" + config.getPort() + "/api")
                    .build()
                    .put()
                    .uri("/veiculos/alterar/" + idVeiculo) // Corrigido para a rota correta
                    .contentType(MediaType.APPLICATION_JSON)
                    .headers(headers -> headers.setBasicAuth("admin", "admin"))
                    .bodyValue(veiculoAtualizado)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            status = HttpStatusCode.valueOf(200);
        } catch (Exception e) {
            status = HttpStatusCode.valueOf(500);
        }
    }

    @When("eu enviar uma requisição para deletar o veículo")
    public void enviarRequisicaoDeletar() {
        try {
            webClientBuilder.baseUrl("http://localhost:" + config.getPort() + "/api")
                    .build()
                    .delete()
                    .uri("/veiculos/deletar/" + idVeiculo) // Corrigido para a rota correta
                    .headers(headers -> headers.setBasicAuth("admin", "admin"))
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();

            status = HttpStatusCode.valueOf(204);
        } catch (Exception e) {
            status = HttpStatusCode.valueOf(500);
        }
    }

    @Then("a resposta do veículo deve ter o status {int} e o modelo {string}")
    public void validarResposta(int statusEsperado, String modeloEsperado) {
        assert status.value() == statusEsperado : "Status esperado: " + statusEsperado + ", recebido: " + status.value();
        assert JsonPath.parse(resposta).read("$.modelo", String.class).equals(modeloEsperado) :
                "Modelo esperado: " + modeloEsperado + ", recebido: " + JsonPath.parse(resposta).read("$.modelo", String.class);
    }

    @Then("a resposta do veículo deve ter o status {int}")
    public void validarStatus(int statusEsperado) {
        assert status.value() == statusEsperado : "Status esperado: " + statusEsperado + ", recebido: " + status.value();
    }
}

