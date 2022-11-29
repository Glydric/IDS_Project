package it.unicam.ids.studenti.ll.model;

abstract class Dipendente extends Persona {
    public int identificativo;
    private String password;
	public Azienda lavoraIn;

    public int getIdentificativo() {
        return this.identificativo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param password
     */
    public boolean isPasswordValid(String password) {
        // TODO - implement Dipendente.isPasswordValid
        throw new UnsupportedOperationException();
    }

}