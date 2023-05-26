package model;

import bll.ClientBLL;
import bll.ProductBLL;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

public class Bill {
    /**
     * Clasa "Bill" generează și salvează o factură într-un fișier text. Metoda "generare" primește detalii despre comanda și ID-ul facturii,
     * iar apoi creează un fișier text în care sunt scrise informațiile corespunzătoare facturii.
     */
    public Bill()
    {

    }
    public static void generare(Orderr order,int id)
    {
        LocalDateTime dataOraCurenta = LocalDateTime.now();
        //System.out.println(dataOraCurenta);
        int idC= order.getId_client();
        int idP= order.getId_produs();
        int cantitate= order.getNr_produse();
        ProductBLL prodB=new ProductBLL();
        ClientBLL clB=new ClientBLL();
        Product p=prodB.findProductById(idP);
        Client c=clB.findClientById(idC);
        float total=p.getPret()*cantitate;
        String content="";

        try {
            FileWriter myWriter = new FileWriter("Bill.txt"+id);
            //myWriter.write(content);
            myWriter.write("Order number "+id+"\n");
            myWriter.write(dataOraCurenta.toString()+"\n");
            myWriter.write("Client:"+"\n");
            myWriter.write("Name: "+c.getNume()+" "+c.getPrenume()+"\n");
            myWriter.write("Product: "+p.getDenumire()+" ");
            myWriter.write(p.getPret()+"x"+cantitate+"                       "+total+"\n");
            myWriter.write(".............................................................."+"\n");
            myWriter.write("                                                TOTAL:"+total);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("A aparut o eroare.");
            e.printStackTrace();
        }

    }
}
