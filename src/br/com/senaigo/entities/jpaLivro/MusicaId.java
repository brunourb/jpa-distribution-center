package br.com.senaigo.entities.jpaLivro;

import java.io.Serializable;

/**
 * Regras:
 * 1) A classe deve ter um construtor público sem parâmetros;
 2) A classe deve implementar a interface Serializable ;
 3) A classe deve sobrescrever os métodos hashCode e equals .
 * Created by bruno on 01/04/16.
 */
public class MusicaId implements Serializable {

    private int duracaoSegundos;
    private String nome;

    public MusicaId(){

    }

    public MusicaId(int duracaoSegundos, String nome) {
        super();
        this.duracaoSegundos = duracaoSegundos;
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MusicaId)) return false;

        MusicaId musicaId = (MusicaId) o;

        if (duracaoSegundos != musicaId.duracaoSegundos) return false;
        return nome.equals(musicaId.nome);

    }

    @Override
    public int hashCode() {
        int result = duracaoSegundos;
        result = 31 * result + nome.hashCode();
        return result;
    }
}
