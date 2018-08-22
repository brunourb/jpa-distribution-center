package br.com.senaigo.entities.jpaLivro;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Created by bruno on 01/04/16.
 */
@Entity
@IdClass(MusicaId.class)
public class Musica {

    @Id
    private int duracaoSegundos;

    @Id
    private String nome;

    public Musica(){

    }

    public Musica(int duracaoSegundos, String nome) {
        this.duracaoSegundos = duracaoSegundos;
        this.nome = nome;
    }

    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }


    public void setDuracaoSegundos(int duracaoSegundos) {
        this.duracaoSegundos = duracaoSegundos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
