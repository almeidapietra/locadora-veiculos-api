package edu.adatech.locadoradeveiculos.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import edu.adatech.locadoradeveiculos.controller.VeiculoController;
import edu.adatech.locadoradeveiculos.model.VeiculoModel;
import edu.adatech.locadoradeveiculos.service.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VeiculoControllerTest {

    @InjectMocks
    private VeiculoController veiculoController;

    @Mock
    private VeiculoService veiculoService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(veiculoController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Cadastra um veículo quando passar dados corretamente")
    void testCadastrarVeiculo() throws Exception {
        VeiculoModel vModel = new VeiculoModel();
        vModel.setModelo("JEEP");
        vModel.setPlaca("NPT4854");

        when(veiculoService.cadastrarVeiculo(Mockito.any(VeiculoModel.class))).thenReturn(vModel);

        String veiculoJson = objectMapper.writeValueAsString(vModel);

        mockMvc.perform(post("/api/veiculos/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(veiculoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelo").value("JEEP"))
                .andExpect(jsonPath("$.placa").value("NPT4854"));


    }

    @Test
    @DisplayName("Altera um veículo com sucesso")
    void testAlterarVeiculo() throws Exception {
        VeiculoModel veiculoAtualizado = new VeiculoModel();
        veiculoAtualizado.setModelo("SUV");
        veiculoAtualizado.setPlaca("XYZ5678");

        when(veiculoService.alterarVeiculo(Mockito.eq(1L), Mockito.any(VeiculoModel.class)))
                .thenReturn(veiculoAtualizado);

        String veiculoJson = objectMapper.writeValueAsString(veiculoAtualizado);

        mockMvc.perform(put("/api/veiculos/alterar/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(veiculoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelo").value("SUV"))
                .andExpect(jsonPath("$.placa").value("XYZ5678"));
    }

    @Test
    @DisplayName("Lista todos os veículos com sucesso")
    void testListarVeiculos() throws Exception {
        VeiculoModel veiculo1 = new VeiculoModel();
        veiculo1.setModelo("Sedan");
        veiculo1.setPlaca("AAA1111");

        VeiculoModel veiculo2 = new VeiculoModel();
        veiculo2.setModelo("Hatch");
        veiculo2.setPlaca("BBB2222");

        when(veiculoService.listarVeiculos()).thenReturn(Arrays.asList(veiculo1, veiculo2));

        mockMvc.perform(get("/api/veiculos/listar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].modelo").value("Sedan"))
                .andExpect(jsonPath("$[0].placa").value("AAA1111"))
                .andExpect(jsonPath("$[1].modelo").value("Hatch"))
                .andExpect(jsonPath("$[1].placa").value("BBB2222"));
    }

    @Test
    @DisplayName("Busca veículo por modelo com sucesso")
    void testBuscarVeiculoPorModelo() throws Exception {
        VeiculoModel veiculo = new VeiculoModel();
        veiculo.setModelo("SUV");
        veiculo.setPlaca("XYZ5678");

        when(veiculoService.buscarVeiculoPorModelo("SUV")).thenReturn(veiculo);

        mockMvc.perform(get("/api/veiculos/buscar/SUV")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelo").value("SUV"))
                .andExpect(jsonPath("$.placa").value("XYZ5678"));
    }

    @Test
    @DisplayName("Deleta um veículo com sucesso")
    void testDeletarVeiculo() throws Exception {
        mockMvc.perform(delete("/api/veiculos/deletar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Veículo deletado com sucesso."));
    }

    @Test
    @DisplayName("Busca veículo por ID com sucesso")
    void testBuscarVeiculoPorId() throws Exception {
        VeiculoModel veiculo = new VeiculoModel();
        veiculo.setModelo("SUV");
        veiculo.setPlaca("XYZ5678");

        when(veiculoService.buscarVeiculoPorId(1L)).thenReturn(Optional.of(veiculo));

        mockMvc.perform(get("/api/veiculos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelo").value("SUV"))
                .andExpect(jsonPath("$.placa").value("XYZ5678"));
    }

    @Test
    @DisplayName("Retorna 404 ao buscar veículo por ID inexistente")
    void testBuscarVeiculoPorIdNotFound() throws Exception {
        when(veiculoService.buscarVeiculoPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/veiculos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
