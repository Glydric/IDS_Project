package it.unicam.ids.studenti.ll.model;

public class Cliente extends Persona {
    public String numeroTelefono;
    public boolean isFamily = false;
    private int identificativoTessera;
    private String email;

    public int getIdentificativoTessera() {
        return this.identificativoTessera;
    }

    public void setIdentificativoTessera(int identificativoTessera) {
        this.identificativoTessera = identificativoTessera;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}