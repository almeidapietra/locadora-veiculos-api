package edu.adatech.locadoradeveiculos.controller;

import edu.adatech.locadoradeveiculos.model.VeiculoModel;
import edu.adatech.locadoradeveiculos.model.Aluguel;
import edu.adatech.locadoradeveiculos.model.Cliente;
import edu.adatech.locadoradeveiculos.repository.AluguelRepository;
import edu.adatech.locadoradeveiculos.service.AluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;



@RestController
@RequestMapping("/api/alugueis")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;

    @Autowired
    private AluguelRepository aluguelRepository; // Injeção do AluguelRepository

    @PostMapping("/alugar")
    public Aluguel alugarVeiculo(
            @RequestParam Cliente cliente,
            @RequestParam VeiculoModel veiculo,
            @RequestParam String localRetirada) {

        return aluguelService.alugarVeiculo(cliente, veiculo, localRetirada, Instant.now());
    }

    @PostMapping("/devolver/{id}")
    public Aluguel devolverVeiculo(
            @PathVariable Long id,
            @RequestParam String localDevolucao) {

        return aluguelService.devolverVeiculo(id, localDevolucao, Instant.now());
    }

    @GetMapping("/alugueis")
    public Page<Aluguel> listarAlugueis(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        int size = 10;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return aluguelRepository.findAll(pageable);
    }
}