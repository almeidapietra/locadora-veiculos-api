package edu.adatech.locadoradeveiculos.controller;

import edu.adatech.locadoradeveiculos.model.AgenciaModel;
import edu.adatech.locadoradeveiculos.repository.AgenciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AgenciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AgenciaRepository agenciaRepository;

    @BeforeEach
    void setup() {

        agenciaRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void deveCriarNovaAgencia() throws Exception {
        String novaAgenciaJson = """
            {
                "nome": "Agência Norte",
                "endereco": "Rua 456"
            }
        """;

        mockMvc.perform(post("/api/agencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(novaAgenciaJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Agência Norte"));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void deveRetornarTodasAsAgencias() throws Exception {
        agenciaRepository.save(new AgenciaModel("Agência Centro", "Rua 123"));

        mockMvc.perform(get("/api/agencias")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Agência Centro"));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void deveBuscarAgenciaPorId() throws Exception {
        AgenciaModel agencia = agenciaRepository.save(new AgenciaModel("Agência Centro", "Rua 123"));

        mockMvc.perform(get("/api/agencias/" + agencia.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Agência Centro"));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void deveRetornar404AoBuscarAgenciaInexistente() throws Exception {
        mockMvc.perform(get("/api/agencias/999")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void deveAtualizarAgencia() throws Exception {
        AgenciaModel agencia = agenciaRepository.save(new AgenciaModel("Agência Antiga", "Endereço Antigo"));

        String agenciaAtualizadaJson = """
            {
                "nome": "Agência Atualizada",
                "endereco": "Endereço Atualizado"
            }
        """;

        mockMvc.perform(put("/api/agencias/" + agencia.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(agenciaAtualizadaJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Agência Atualizada"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void deveDeletarAgencia() throws Exception {
        AgenciaModel agencia = agenciaRepository.save(new AgenciaModel("Agência Centro", "Rua Centro"));

        mockMvc.perform(delete("/api/agencias/" + agencia.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void deveRetornar404AoDeletarAgenciaInexistente() throws Exception {
        mockMvc.perform(delete("/api/agencias/999"))
                .andExpect(status().isNotFound());
    }
}
