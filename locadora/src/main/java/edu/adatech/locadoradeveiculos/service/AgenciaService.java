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

    // Método para buscar todas as agências
    public List<AgenciaModel> findAll() {
        return agenciaRepository.findAll();
    }

    // Método para buscar uma agência pelo ID
    public Optional<AgenciaModel> findById(Long id) {
        return agenciaRepository.findById(id);
    }

    // Método para buscar uma agência pelo nome exato
    public Optional<AgenciaModel> findByNome(String nome) {
        return agenciaRepository.findByNome(nome);
    }

    // Método para buscar agências com nome parcial
    public List<AgenciaModel> findByNomeContaining(String nomeParcial) {
        return agenciaRepository.findByNomeContaining(nomeParcial);
    }

    // Método para buscar agências pelo endereço exato
    public List<AgenciaModel> findByEndereco(String endereco) {
        return agenciaRepository.findByEndereco(endereco);
    }

    // Método para buscar agências com endereço parcial
    public List<AgenciaModel> findByEnderecoContaining(String enderecoParcial) {
        return agenciaRepository.findByEnderecoContaining(enderecoParcial);
    }

    // Método para salvar uma nova agência ou atualizar uma existente
    public AgenciaModel save(AgenciaModel agenciaModel) {
        return agenciaRepository.save(agenciaModel);
    }

    // Método para deletar uma agência pelo ID
    public void deleteById(Long id) {
        agenciaRepository.deleteById(id);
    }
}


