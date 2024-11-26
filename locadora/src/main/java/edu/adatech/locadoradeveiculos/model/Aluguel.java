package edu.adatech.locadoradeveiculos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private VeiculoModel veiculo;

    @ManyToOne
    private ClienteModel cliente;

    private String localRetirada;
    private String localDevolucao;
    private Instant dataInicio;
    private Instant dataFim;
    private boolean ativo = true;
    private boolean devolvido = false;

    // Construtores, Getters e Setters

    public Aluguel(VeiculoModel veiculo, ClienteModel cliente, String localRetirada, Instant dataInicio) {
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.localRetirada = localRetirada;
        this.dataInicio = dataInicio;
    }

    public Aluguel() {

    }

    public void finalizarAluguel(String localDevolucao, Instant dataFim) {
        this.localDevolucao = localDevolucao;
        this.dataFim = dataFim;
        this.ativo = false;
        this.devolvido = true;
    }
}