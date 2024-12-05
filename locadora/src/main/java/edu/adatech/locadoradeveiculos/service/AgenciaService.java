package edu.adatech.locadoradeveiculos.service;

import edu.adatech.locadoradeveiculos.model.AgenciaModel;
import edu.adatech.locadoradeveiculos.repository.AgenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenciaService {

    private final AgenciaRepository agenciaRepository;

    @Autowired
    public AgenciaService(AgenciaRepository agenciaRepository) {
        this.agenciaRepository = agenciaRepository;
    }

    public List<AgenciaModel> findAll() {
        return agenciaRepository.findAll();
    }

    public Optional<AgenciaModel> findById(Long id) {
        return agenciaRepository.findById(id);
    }

    public Optional<AgenciaModel> findByNome(String nome) {
        return agenciaRepository.findByNome(nome);
    }

    public List<AgenciaModel> findByNomeContaining(String nomeParcial) {
        return agenciaRepository.findByNomeContaining(nomeParcial);
    }

    public List<AgenciaModel> findByEndereco(String endereco) {
        return agenciaRepository.findByEndereco(endereco);
    }

    public List<AgenciaModel> findByEnderecoContaining(String enderecoParcial) {
        return agenciaRepository.findByEnderecoContaining(enderecoParcial);
    }

    public AgenciaModel save(AgenciaModel agenciaModel) {
        return agenciaRepository.save(agenciaModel);
    }

    public void deleteById(Long id) {
        Optional<AgenciaModel> agencia = agenciaRepository.findById(id);

        if (agencia.isEmpty()) {
            throw new IllegalArgumentException("Agência com ID " + id + " não encontrada");
        }

        agenciaRepository.deleteById(id);
    }
}
