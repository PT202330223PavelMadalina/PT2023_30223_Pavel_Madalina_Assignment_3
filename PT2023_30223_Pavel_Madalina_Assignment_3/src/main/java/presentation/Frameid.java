package presentation;

import DAO.ClientDAO;

import javax.swing.*;
import java.awt.*;

public class Frameid {
    private  JFrame frame;
    private JPanel panel1;
    private JLabel l1;
    private JTextField t;
    private JButton b;

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JLabel getL1() {
        return l1;
    }

    public void setL1(JLabel l1) {
        this.l1 = l1;
    }

    public JTextField getT() {
        return t;
    }

    public void setT(JTextField t) {
        this.t = t;
    }

    public JButton getB() {
        return b;
    }

    public void setB(JButton b) {
        this.b = b;
    }

    public Frameid(String nume, String prenume, String email)
    {
        frame = new JFrame("ID");
        ClientDAO cl=new ClientDAO();

        panel1=new JPanel();
        panel1.setBounds(0,0,200,200);
        panel1.setBackground(new Color(135,206,250));
        panel1.setLayout(null);
        l1=new JLabel("INTRODUCETI ID-UL");
        l1.setBounds(20,0,160,30);
        l1.setFont(new Font("serif",Font.BOLD,15));
        l1.setForeground(new Color(70,130,180));
        panel1.add(l1);
        t =new JTextField();
        t.setBounds(70,60,50,30);
        panel1.add(t);
        b=new JButton("EDIT");
        b.setBackground(new Color(0,0,128));
        b.setForeground(Color.white);
        b.setBounds(40,100,100,30);
        panel1.add(b);
        frame.setBounds(0, 0, 200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel1);
        frame.setVisible(true);
    }
}
