package com.example.myglass;

public class Occhiale {
    public int occhiale_id;
    public String descrizione;
    public float prezzo;
    public String percorso_immagine;
    public String nome;

    public Occhiale(){

    }

    public Occhiale(String descrizione, float prezzo, String percorso_immagine, String nome, int occhiale_id){
        this.occhiale_id = occhiale_id;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.percorso_immagine = percorso_immagine;
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public void setPercorso_immagine(String percorso_immagine) {
        this.percorso_immagine = percorso_immagine;
    }

    public void setCategoria(String categoria) {
        this.nome = categoria;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public String getPercorso_immagine() {
        return percorso_immagine;
    }

    public String getCategoria() {
        return nome;
    }

    public int getOcchiale_id() {
        return occhiale_id;
    }

    public void setOcchiale_id(int occhiale_id) {
        this.occhiale_id = occhiale_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Occhiale{" +
                "descrizione='" + descrizione + '\'' +
                ", prezzo=" + prezzo +
                ", percorso_immagine='" + percorso_immagine + '\'' +
                ", categoria='" + nome + '\'' +
                '}';
    }
}