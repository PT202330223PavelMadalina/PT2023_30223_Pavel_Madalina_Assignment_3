package model;
public class Product {
    private int id;
    private String denumire;
    private float pret;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }

    private int stoc;
    public Product()
    {

    }

    public Product(int id, String denumire, float pret, int stoc) {
        this.id=id;
        this.denumire = denumire;
        this.pret = pret;
        this.stoc = stoc;
    }
}
