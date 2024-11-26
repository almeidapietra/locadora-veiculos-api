package edu.adatech.locadoradeveiculos.model;

import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class ClientePessoaFisicaModel extends ClienteModel {

    private String cpf;

    public void setCpf(String cpf) {
        if (cpf != null && cpf.matches("\\d{11}")) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("CPF inválido: deve conter exatamente 11 dígitos.");
        }
    }

}
