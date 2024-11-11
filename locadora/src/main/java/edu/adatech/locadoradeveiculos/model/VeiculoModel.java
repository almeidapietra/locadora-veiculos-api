package edu.adatech.locadoradeveiculos.model;

import edu.adatech.locadoradeveiculos.enums.TipoVeiculo;
import jakarta.persistence.*;

@Entity
public class VeiculoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private boolean disponivel;
    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipo;
    private String placa;


    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setDisponivel(boolean b) {

    }

    public boolean isDisponivel() { return disponivel; }
}
