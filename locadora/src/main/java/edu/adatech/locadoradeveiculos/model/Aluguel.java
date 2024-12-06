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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VeiculoModel getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(VeiculoModel veiculo) {
        this.veiculo = veiculo;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public String getLocalRetirada() {
        return localRetirada;
    }

    public void setLocalRetirada(String localRetirada) {
        this.localRetirada = localRetirada;
    }

    public String getLocalDevolucao() {
        return localDevolucao;
    }

    public void setLocalDevolucao(String localDevolucao) {
        this.localDevolucao = localDevolucao;
    }

    public Instant getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Instant dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Instant getDataFim() {
        return dataFim;
    }

    public void setDataFim(Instant dataFim) {
        this.dataFim = dataFim;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public void finalizarAluguel(String localDevolucao, Instant dataFim) {
        this.localDevolucao = localDevolucao;
        this.dataFim = dataFim;
        this.ativo = false;
        this.devolvido = true;
    }
}