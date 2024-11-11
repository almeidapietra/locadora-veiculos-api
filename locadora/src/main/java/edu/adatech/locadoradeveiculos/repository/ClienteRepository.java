package edu.adatech.locadoradeveiculos.repository;

import edu.adatech.locadoradeveiculos.model.ClienteModel;
import edu.adatech.locadoradeveiculos.model.ClientePessoaFisicaModel;
import edu.adatech.locadoradeveiculos.model.ClientePessoaJuridicaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    Optional<ClientePessoaFisicaModel> findByCpf(String cpf);

    Optional<ClientePessoaJuridicaModel> findByCnpj(String cnpj);

}
