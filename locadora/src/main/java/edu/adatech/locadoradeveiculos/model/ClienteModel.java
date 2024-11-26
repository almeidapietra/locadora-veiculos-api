package edu.adatech.locadoradeveiculos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CLIENTES")
public class ClienteModel {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String nome;

    @Getter
    @Setter
    private String logradouro;

    public void setNome(String nome) {
        if (nome != null && nome.matches("[a-zA-Z\\s]+")) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("Nome inválido: deve conter apenas letras e espaços.");
        }
    }

}
