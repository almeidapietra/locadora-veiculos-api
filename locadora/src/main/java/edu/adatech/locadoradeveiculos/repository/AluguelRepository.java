package edu.adatech.locadoradeveiculos.repository;

import edu.adatech.locadoradeveiculos.model.Aluguel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    Page<Aluguel> findAll(Pageable pageable);
}