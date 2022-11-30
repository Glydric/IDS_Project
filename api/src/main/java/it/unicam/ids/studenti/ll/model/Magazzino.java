package it.unicam.ids.studenti.ll.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Magazzino {

    private final Map<Prodotto, Float> prodottiQuantita = new HashMap<>();

    public List<Prodotto> getListaProdotti() {
        // TODO - implement Magazzino.getListaProdotti
        throw new UnsupportedOperationException();
    }

    public float getQuantitaProdotto(Prodotto prodotto) {
        return prodottiQuantita.get(prodotto);
    }

    /**
     * @param prodotto il prodotto da aggiungere
     * @throws IllegalArgumentException se il prodotto inserito è nullo
     */
    public void addNewProdotto(Prodotto prodotto) throws IllegalArgumentException {
        if (prodotto == null)
            throw new IllegalArgumentException("Null product not accepted");
        if (prodottiQuantita.containsKey(prodotto))
            return;
        prodottiQuantita.put(prodotto, 0f);
    }

    public void setQuantitaProdotto(Prodotto prodotto, float value) throws IllegalArgumentException {
        if (value < 0)
            throw new IllegalArgumentException("Negative number not accepted");
        addNewProdotto(prodotto);
        prodottiQuantita.replace(prodotto, value);

    }

    /**
     * Incrementa di 1 la quantità del prodotto
     *
     * @param prodotto il prodotto da incrementare
     */
    public void incrementaProdotto(Prodotto prodotto) {
        float oldValue = prodottiQuantita.getOrDefault(prodotto, 0.0f);
        setQuantitaProdotto(prodotto, ++oldValue);

        // TODO - check Magazzino.incrementaProdotto
    }

    /**
     * decrementa la quantità del prodotto di 1
     *
     * @param prodotto il prodotto da decrementare
     */
    public void decrementaProdotto(Prodotto prodotto) {
        float oldValue = prodottiQuantita.getOrDefault(prodotto, 0.0f);
        setQuantitaProdotto(prodotto, --oldValue);

        // TODO - check Magazzino.decrementaProdotto
    }


}