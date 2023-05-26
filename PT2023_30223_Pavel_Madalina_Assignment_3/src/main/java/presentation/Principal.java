package presentation;

import javax.swing.*;
import java.awt.*;

public class Principal {
    /**
     * Clasa care cuprinde interfata principala, de aici utilizatorul decide daca doreste sa efectueze o
     * operatiune pentru Produse, pentru Clienti sau sa plaseze o comanda
     */
 private JFrame f;
 private JPanel panel1=new JPanel();
 private JLabel l1=new JLabel("PLEASE CHOOSE AN OPERATION FOR:");
 private JButton clientt=new JButton("CLIENT");
 private JButton produss=new JButton("PRODUCT");
 private JButton comanda=new JButton("ORDER");
 public Principal()
 {
     JFrame frame = new JFrame("HOME");

     panel1.setBounds(0,0,700,500);
     panel1.setBackground(new Color(135,206,250));
     panel1.setLayout(null);
     l1.setBounds(50,10,400,30);
     l1.setFont(new Font("serif",Font.BOLD,20));
     l1.setForeground(new Color(70,130,180));
     panel1.add(l1);




     clientt.setBackground(new Color(0,0,128));
     clientt.setForeground(Color.white);
     clientt.setBounds(150,100,200,30);
     panel1.add(clientt);

     produss.setBackground(new Color(0,0,128));
     produss.setForeground(Color.white);
     produss.setBounds(150,180,200,30);
     panel1.add(produss);

     comanda.setBackground(new Color(0,0,128));
     comanda.setForeground(Color.white);
     comanda.setBounds(150,260,200,30);
     panel1.add(comanda);

     frame.setBounds(0, 0, 500, 450);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     frame.add(panel1);
     frame.setVisible(true);
 }

    public JFrame getF() {
        return f;
    }

    public void setF(JFrame f) {
        this.f = f;
    }

    public JLabel getL1() {
        return l1;
    }

    public void setL1(JLabel l1) {
        this.l1 = l1;
    }

    public JButton getClientt() {
        return clientt;
    }

    public void setClientt(JButton clientt) {
        this.clientt = clientt;
    }

    public JButton getProduss() {
        return produss;
    }

    public void setProduss(JButton produss) {
        this.produss = produss;
    }

    public JButton getComanda() {
        return comanda;
    }

    public void setComanda(JButton comanda) {
        this.comanda = comanda;
    }
}
