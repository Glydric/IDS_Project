package it.unicam.ids.studenti.ll.model;

import java.nio.file.Path;

public class Prodotto {
    public String idCodiceBarre;
    public String nome;
    public String descrizione;
    private Path imgPath;
    private int prezzo;

    public Path getImgPath() {
        return this.imgPath;
    }

    public void setImgPath(Path imgPath) {
        this.imgPath = imgPath;
    }

    public int getPrezzo() {
        return this.prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

}