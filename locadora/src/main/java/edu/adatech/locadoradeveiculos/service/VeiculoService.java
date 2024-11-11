package edu.adatech.locadoradeveiculos.service;

import edu.adatech.locadoradeveiculos.model.VeiculoModel;
import edu.adatech.locadoradeveiculos.repository.VeiculoRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veic;

    public VeiculoModel cadastrarVeiculo(VeiculoModel veiculo) {
        if (!veic.findByModelo(veiculo.getModelo()).isEmpty()) {
            throw new IllegalArgumentException("Erro: Veículo já existe.");
        }
        return veic.save(veiculo);
    }

    public VeiculoModel alterarVeiculo(Long id, VeiculoModel veiculoAtualizado) {
        Optional<VeiculoModel> veiculoOptional = veic.findById(String.valueOf(id));
        if (veiculoOptional.isEmpty()) {
            throw new IllegalArgumentException("Veículo não encontrado.");
        }
        VeiculoModel veiculo = veiculoOptional.get();
        veiculo.setModelo(veiculoAtualizado.getModelo());
        veiculo.setDisponivel(veiculoAtualizado.isDisponivel());
        return veic.save(veiculo);
    }

    public List<VeiculoModel> listarVeiculos() {
        return veic.findAll();
    }
    public VeiculoModel buscarVeiculoPorModelo(String modelo) {
        List<VeiculoModel> veiculos = veic.findByModelo(modelo);
        if (veiculos.isEmpty()) {
            throw new IllegalArgumentException("Veículo não encontrado.");
        }
        return veiculos.get(0);
    }

    public void deletarVeiculo(Long id) {
        if (!veic.existsById(String.valueOf(id))) {
            throw new IllegalArgumentException("Erro: Veículo não encontrado.");
        }
        veic.deleteById(String.valueOf(id));
    }
}
