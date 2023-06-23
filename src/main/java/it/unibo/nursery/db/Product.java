package it.unibo.nursery.db;

public class Product {
    private int id_prodotto;
    private String tipo;
    private float prezzo;
    private String descrizione;
    
    public Product(int id_prodotto, String tipo, float prezzo, String descrizione) {
        this.id_prodotto = id_prodotto;
        this.tipo = tipo;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
    }
    
    public int getId_prodotto() {
        return id_prodotto;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public float getPrezzo() {
        return prezzo;
    }
    
    public String getDescrizione() {
        return descrizione;
    }
}
