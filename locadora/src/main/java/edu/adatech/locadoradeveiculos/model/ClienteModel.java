package edu.adatech.locadoradeveiculos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "CLIENTES")
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String logradouro;

    public void setNome(String nome) {
        if (nome != null && nome.matches("[a-zA-Z\\s]+")) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("Nome inválido: deve conter apenas letras e espaços.");
        }
    }

}
