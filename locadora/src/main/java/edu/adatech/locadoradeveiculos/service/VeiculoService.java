package edu.adatech.locadoradeveiculos.service;

import edu.adatech.locadoradeveiculos.enums.TipoVeiculo;
import edu.adatech.locadoradeveiculos.model.VeiculoModel;
import edu.adatech.locadoradeveiculos.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veic;


    public VeiculoModel cadastrarVeiculo(VeiculoModel veiculo) {
        if (veiculo.getTipo() == null) {
            throw new IllegalArgumentException("Erro: Tipo de veículo é obrigatório.");
        }
        if (!veic.findByModelo(veiculo.getModelo()).isEmpty()) {
            throw new IllegalArgumentException("Erro: Veículo já existe.");
        }
        return veic.save(veiculo);
    }

    public VeiculoModel alterarVeiculo(Long id, VeiculoModel veiculoAtualizado) {
        Optional<VeiculoModel> veiculoOptional = veic.findById(id);
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


    public Optional<VeiculoModel> buscarVeiculoPorId(Long id) {
        return veic.findById(id);
    }

    public void deletarVeiculo(Long id) {
        if (!veic.existsById(id)) {
            throw new IllegalArgumentException("Erro: Veículo não encontrado.");
        }
        veic.deleteById(id);
    }
    public List<VeiculoModel> listarVeiculosPorTipo(TipoVeiculo tipo) {
        return veic.findByTipo(tipo);
    }
    public List<VeiculoModel> buscarVeiculosPorTipo(TipoVeiculo tipo) {
        return veic.findByTipo(tipo);
    }

}
