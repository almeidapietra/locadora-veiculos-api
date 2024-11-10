package edu.adatech.locadoradeveiculos.repository;

import edu.adatech.locadoradeveiculos.model.AgenciaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgenciaRepository extends JpaRepository<AgenciaModel, Long> {


    Optional<AgenciaModel> findByNome(String nome);


    List<AgenciaModel> findByEndereco(String endereco);


    List<AgenciaModel> findByNomeContaining(String nomeParcial);


    List<AgenciaModel> findByEnderecoContaining(String enderecoParcial);
}