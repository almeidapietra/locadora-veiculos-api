package edu.adatech.locadoradeveiculos.repository;
import edu.adatech.locadoradeveiculos.model.VeiculoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<VeiculoModel, String> {
    List<VeiculoModel> findByModelo(String modelo);

    Optional<VeiculoModel> findByModeloContainingIgnoreCase(String modelo);
}

