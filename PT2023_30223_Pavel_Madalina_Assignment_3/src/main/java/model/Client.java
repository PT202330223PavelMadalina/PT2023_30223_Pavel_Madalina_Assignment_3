package model;
public class Client {
    private int id;
    private String nume;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String prenume;
    private String email;
    public Client()
    {

    }
    public Client(int id, String nume, String prenume,String email)
    {
        this.id=id;
        this.nume=nume;
        this.prenume=prenume;
        this.email=email;
    }
}
