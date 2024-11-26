package edu.adatech.locadoradeveiculos.service;

import edu.adatech.locadoradeveiculos.model.Aluguel;
import edu.adatech.locadoradeveiculos.model.ClienteModel;
import edu.adatech.locadoradeveiculos.model.VeiculoModel;
import edu.adatech.locadoradeveiculos.repository.AluguelRepository;
import edu.adatech.locadoradeveiculos.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public Aluguel alugarVeiculo(ClienteModel cliente, VeiculoModel veiculo, String localRetirada, Instant dataInicio) {
        if (!isVeiculoDisponivel(veiculo)) {
            throw new IllegalStateException("Veículo não disponível para aluguel.");
        }

        Aluguel novoAluguel = new Aluguel(veiculo, cliente, localRetirada, dataInicio);
        return aluguelRepository.save(novoAluguel);
    }

    public Aluguel devolverVeiculo(Long aluguelId, String localDevolucao, Instant dataFim) {
        Aluguel aluguel = aluguelRepository.findById(aluguelId)
                .orElseThrow(() -> new IllegalArgumentException("Aluguel não encontrado."));

        aluguel.finalizarAluguel(localDevolucao, dataFim);
        return aluguelRepository.save(aluguel);
    }

    public List<Aluguel> listarAlugueis() {
        return aluguelRepository.findAll();
    }

    public Page<Aluguel> listarAlugueisPaginados(Pageable pageable) {
        return aluguelRepository.findAll(pageable);
    }

    public boolean isVeiculoDisponivel(VeiculoModel veiculo) {
        return veiculoRepository.findById(veiculo.getId()).isEmpty();
    }
}