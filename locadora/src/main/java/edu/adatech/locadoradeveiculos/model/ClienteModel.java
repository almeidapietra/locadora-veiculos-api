package edu.adatech.locadoradeveiculos.model;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Table(name = "CLIENTES")
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
