package br.com.vetfind.vet_find_app.modelo;

import java.io.Serializable;

public class Raca implements Serializable {

    private Long id;
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return getId() + " - " + getNome();
    }
}
