package edu.adatech.locadoradeveiculos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Setter
    private String logradouro;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && nome.matches("[a-zA-Z\\s]+")) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("Nome inválido: deve conter apenas letras e espaços.");
        }
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
}
