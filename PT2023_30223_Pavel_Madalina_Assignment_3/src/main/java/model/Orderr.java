package model;

public class Orderr {
    public Orderr()
    {

    }
    private int id;
    private int id_client;
    private int id_produs;

    public int getId() {
        return id;
    }

    public int getId_client() {
        return id_client;
    }

    public int getId_produs() {
        return id_produs;
    }

    public float getValoare() {
        return valoare;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setId_produs(int id_produs) {
        this.id_produs = id_produs;
    }

    public void setValoare(float valoare) {
        this.valoare = valoare;
    }

    public void setNr_produse(int nr_produse) {
        this.nr_produse = nr_produse;
    }

    public int getNr_produse() {
        return nr_produse;
    }

    private float valoare;
    private int  nr_produse;

    public Orderr(int id_client, int id_produs, float valoare, int nr_produse) {
        this.id_client = id_client;
        this.id_produs = id_produs;
        this.valoare = valoare;
        this.nr_produse = nr_produse;
    }
}
