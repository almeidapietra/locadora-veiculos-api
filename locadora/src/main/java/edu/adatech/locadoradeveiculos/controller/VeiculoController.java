package edu.adatech.locadoradeveiculos.controller;
import edu.adatech.locadoradeveiculos.model.VeiculoModel;
import edu.adatech.locadoradeveiculos.repository.VeiculoRepository;
import edu.adatech.locadoradeveiculos.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<VeiculoModel> cadastrarVeiculo(@RequestBody VeiculoModel veiculo) {
        return ResponseEntity.ok(veiculoService.cadastrarVeiculo(veiculo));
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<VeiculoModel> alterarVeiculo(@PathVariable Long id, @RequestBody VeiculoModel veiculoAtualizado) {
        return ResponseEntity.ok(veiculoService.alterarVeiculo(id, veiculoAtualizado));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<VeiculoModel>> listarVeiculos() {
        return ResponseEntity.ok(veiculoService.listarVeiculos());
    }

    @GetMapping("/buscar/{modelo}")
    public ResponseEntity<VeiculoModel> buscarVeiculoPorModelo(@PathVariable String modelo) {
        return ResponseEntity.ok(veiculoService.buscarVeiculoPorModelo(modelo));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarVeiculo(@PathVariable Long id) {
        veiculoService.deletarVeiculo(id);
        return ResponseEntity.ok("Veículo deletado com sucesso.");
    }

}
