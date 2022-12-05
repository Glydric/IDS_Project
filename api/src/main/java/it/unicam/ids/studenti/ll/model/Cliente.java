package it.unicam.ids.studenti.ll.model;

import org.w3c.dom.CDATASection;

import java.time.LocalDate;
import java.util.*;

public class Cliente extends Persona {

    private String numeroTelefono;
    private boolean isFamily = false;
    private int identificativoTessera;
    private String email;
	private List<Commerciante> listaCommercianti;

    public Cliente(String nome, String cognome, LocalDate dataNascita, String numeroTelefono, boolean isFamily, int identificativoTessera, String email, List<Commerciante> listaComm){
        super(nome, cognome, dataNascita);
        this.numeroTelefono = numeroTelefono;
        this.isFamily = isFamily;
        this.identificativoTessera = identificativoTessera;
        this.email = email;
        this.listaCommercianti = listaComm;
    }

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

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public boolean isFamily() {
        return isFamily;
    }

    public void setFamily(boolean family) {
        isFamily = family;
    }

    public List<Commerciante> getListaCommercianti() {
        return listaCommercianti;
    }

    public void setListaCommercianti(List<Commerciante> listaCommercianti) {
        this.listaCommercianti = listaCommercianti;
    }
}