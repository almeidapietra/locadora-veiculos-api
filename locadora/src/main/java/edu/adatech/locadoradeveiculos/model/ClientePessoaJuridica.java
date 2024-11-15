package edu.adatech.locadoradeveiculos.model;

import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class ClientePessoaJuridicaModel extends ClienteModel {

    private String cnpj;

    public void setCnpj(String cnpj) {
        if (cnpj != null && cnpj.matches("\\d{14}")) {
            this.cnpj = cnpj;
        } else {
            throw new IllegalArgumentException("CNPJ inválido: deve conter exatamente 14 dígitos.");
        }
    }

}
