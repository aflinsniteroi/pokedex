/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.reactiveWeb.model;

import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author CP39
 */
@Document
public class Pokemon implements Comparable<Pokemon>{
    @Id
    private String id;

    private String nome;
    private String categoria;
    private String habilidades;
    private Double peso;
    private Pokemon evolucao;
    private Pokemon formaAlternativa;
    private String tipo;
    private Integer ordem;

    
    public Pokemon(String id, String nome, String categoria, String habilidades, Double peso, Integer ordem) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.habilidades = habilidades;
        this.peso = peso;
        this.ordem = ordem;

    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Pokemon getEvolucao() {
        return evolucao;
    }

    public void setEvolucao(Pokemon evolucao) {
        this.evolucao = evolucao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public Pokemon getFormaAlternativa() {
        return formaAlternativa;
    }

    public void setFormaAlternativa(Pokemon formaAlternativa) {
        this.formaAlternativa = formaAlternativa;
    }

    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pokemon other = (Pokemon) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pokemon{" + "id=" + id + ", nome=" + nome + ", categoria=" + categoria + ", habilidades=" + habilidades + ", peso=" + peso + ", evolucao=" + evolucao + ", tipo=" + tipo + '}';
    }

    @Override
    public int compareTo(Pokemon pokemon) {
        if (this.ordem < pokemon.getOrdem()) {
            return -1;
        }
        if (this.ordem > pokemon.getOrdem()) {
            return 1;
        }
        return 0;
    }
  
    
}
