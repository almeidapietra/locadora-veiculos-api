package edu.adatech.locadoradeveiculos.model;

import edu.adatech.locadoradeveiculos.enums.TipoVeiculo;
import jakarta.persistence.*;
import lombok.Setter;

@Entity
public class VeiculoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private boolean disponivel;
    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipo;
    @Setter
    private String placa;


    public VeiculoModel() {
    }


    public VeiculoModel(String modelo, boolean disponivel, TipoVeiculo tipo, String placa) {
        this.modelo = modelo;
        this.disponivel = disponivel;
        this.tipo = tipo;
        this.placa = placa;
    }

    public Long getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVeiculo tipo) {
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }

}
