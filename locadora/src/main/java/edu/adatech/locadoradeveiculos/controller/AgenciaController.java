package edu.adatech.locadoradeveiculos.controller;

import edu.adatech.locadoradeveiculos.model.AgenciaModel;
import edu.adatech.locadoradeveiculos.service.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/agencias")

public class AgenciaController {

    private final AgenciaService agenciaService;

    @Autowired
    public AgenciaController(AgenciaService agenciaService) {
        this.agenciaService = agenciaService;
    }

    // Rota para buscar todas as agências
    @GetMapping
    public ResponseEntity<List<AgenciaModel>> getAllAgencias() {
        List<AgenciaModel> agencias = agenciaService.findAll();
        return ResponseEntity.ok(agencias);
    }

    // Rota para buscar uma agência pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<AgenciaModel> getAgenciaById(@PathVariable Long id) {
        Optional<AgenciaModel> agencia = agenciaService.findById(id);
        return agencia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Rota para buscar uma agência pelo nome
    @GetMapping("/nome/{nome}")
    public ResponseEntity<AgenciaModel> getAgenciaByNome(@PathVariable String nome) {
        Optional<AgenciaModel> agencia = agenciaService.findByNome(nome);
        return agencia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Rota para buscar agências com nome parcial
    @GetMapping("/nome/contendo/{nomeParcial}")
    public ResponseEntity<List<AgenciaModel>> getAgenciasByNomeContaining(@PathVariable String nomeParcial) {
        List<AgenciaModel> agencias = agenciaService.findByNomeContaining(nomeParcial);
        return ResponseEntity.ok(agencias);
    }

    // Rota para buscar agências com endereço parcial
    @GetMapping("/endereco/contendo/{enderecoParcial}")
    public ResponseEntity<List<AgenciaModel>> getAgenciasByEnderecoContaining(@PathVariable String enderecoParcial) {
        List<AgenciaModel> agencias = agenciaService.findByEnderecoContaining(enderecoParcial);
        return ResponseEntity.ok(agencias);
    }

    // Rota para criar uma nova agência
    @PostMapping
    public ResponseEntity<AgenciaModel> createAgencia(@RequestBody AgenciaModel agenciaModel) {
        AgenciaModel savedAgencia = agenciaService.save(agenciaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAgencia);
    }

    // Rota para atualizar uma agência existente
    @PutMapping("/{id}")
    public ResponseEntity<AgenciaModel> updateAgencia(@PathVariable Long id, @RequestBody AgenciaModel agenciaModel) {
        Optional<AgenciaModel> existingAgencia = agenciaService.findById(id);
        if (existingAgencia.isPresent()) {
            agenciaModel.setId(id);  // Define o ID para garantir que estamos atualizando
            AgenciaModel updatedAgencia = agenciaService.save(agenciaModel);
            return ResponseEntity.ok(updatedAgencia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Rota para deletar uma agência por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgencia(@PathVariable Long id) {
        if (agenciaService.findById(id).isPresent()) {
            agenciaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}