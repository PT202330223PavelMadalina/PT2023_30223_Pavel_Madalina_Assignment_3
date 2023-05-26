package presentation;
import DAO.ClientDAO;
import bll.ClientBLL;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ClientView {
    /**
     * Clasa care cuprinde interfata pentru Client dar si metoda de creare a tabelului de Clienti existenti
     */
    private JFrame f;
    private int ultimId=0;

    public int getUltimId() {
        return ultimId;
    }

    private JPanel panel1=new JPanel();
    private JScrollPane sp;

    public JScrollPane getSp() {
        return sp;
    }

    public void setSp(JScrollPane sp) {
        this.sp = sp;
    }

    private Label l1=new Label("CLIENT");
    private Label l2=new Label("Nume:");
    private Label l3=new Label("Prenume:");
    private Label l4=new Label("Email:");
    private JTextField t1=new JTextField();
    private JTextField t2=new JTextField();
    private JTextField t3=new JTextField();
    private JTextField t4=new JTextField();
    private JButton addButton=new JButton("ADD CLIENT");
    private JButton deleteButton=new JButton("DELETE CLIENT");
    private JButton editButton=new JButton("EDIT CLIENT");
    private JButton viewButton=new JButton("VIEW CLIENTS");
    private JButton backButton=new JButton("BACK");
    private JTable tabelClienti=new JTable();


    public JFrame getF() {
        return f;
    }

    public void setF(JFrame f) {
        this.f = f;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public Label getL1() {
        return l1;
    }

    public void setL1(Label l1) {
        this.l1 = l1;
    }

    public Label getL2() {
        return l2;
    }

    public void setL2(Label l2) {
        this.l2 = l2;
    }

    public Label getL3() {
        return l3;
    }

    public void setL3(Label l3) {
        this.l3 = l3;
    }

    public Label getL4() {
        return l4;
    }

    public void setL4(Label l4) {
        this.l4 = l4;
    }

    public JTextField getT1() {
        return t1;
    }

    public void setT1(JTextField t1) {
        this.t1 = t1;
    }

    public JTextField getT2() {
        return t2;
    }

    public void setT2(JTextField t2) {
        this.t2 = t2;
    }

    public JTextField getT3() {
        return t3;
    }

    public void setT3(JTextField t3) {
        this.t3 = t3;
    }

    public JTextField getT4() {
        return t4;
    }

    public void setT4(JTextField t4) {
        this.t4 = t4;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public void setEditButton(JButton editButton) {
        this.editButton = editButton;
    }

    public JButton getViewButton() {
        return viewButton;
    }

    public void setViewButton(JButton viewButton) {
        this.viewButton = viewButton;
    }

    public void setTabelClienti(JTable tabelClienti) {
        this.tabelClienti = tabelClienti;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }

    public JTable getTabelClienti() {
        return tabelClienti;
    }

    public ClientView()
    {
         f = new JFrame("CLIENT VIEW");

        panel1.setBounds(0,0,700,500);
        panel1.setBackground(new Color(135,206,250));
        panel1.setLayout(null);
        l1.setBounds(220,0,150,30);
        l1.setFont(new Font("serif",Font.BOLD,20));
        l1.setForeground(new Color(70,130,180));
        panel1.add(l1);

        t1.setBounds(150,50,100,30);
        panel1.add(t1);
        l2.setBounds(20,50,100,30);
        l2.setFont(new Font("serif",Font.ITALIC,17));
        l2.setForeground(new Color(70,130,180));
        panel1.add(l2);
        t2.setBounds(150,100,100,30);
        panel1.add(t2);
        l3.setBounds(20,100,100,30);
        l3.setFont(new Font("serif",Font.ITALIC,17));
        l3.setForeground(new Color(70,130,180));
        panel1.add(l3);

        l4.setBounds(20,150,100,30);
        l4.setFont(new Font("serif",Font.ITALIC,17));
        l4.setForeground(new Color(70,130,180));
        panel1.add(l4);

        t3.setBounds(150,150,100,30);
        panel1.add(t3);


        addButton.setBackground(new Color(0,0,128));
        addButton.setForeground(Color.white);
        addButton.setBounds(20,200,200,30);
        panel1.add(addButton);

        deleteButton.setBackground(new Color(0,0,128));
        deleteButton.setForeground(Color.white);
        deleteButton.setBounds(20,250,200,30);
        panel1.add(deleteButton);

        editButton.setBackground(new Color(0,0,128));
        editButton.setForeground(Color.white);
        editButton.setBounds(250,200,200,30);
        panel1.add(editButton);

        viewButton.setBackground(new Color(0,0,128));
        viewButton.setForeground(Color.white);
        viewButton.setBounds(250,250,200,30);
        panel1.add(viewButton);

        backButton.setBackground(new Color(0,0,128));
        backButton.setForeground(Color.white);
        backButton.setBounds(150,350,200,30);
        panel1.add(backButton);

        //JTable t=createTabel();

        sp=createTabel();

        //sp.setBounds(0,400,750,60);
       panel1.add(sp);




        f.setBounds(0, 0, 800, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.add(panel1);
        f.setVisible(true);






    }
    public static JScrollPane createTabel()
    {
        //String[] coloane={"ID","NUME","PRENUME","EMAIL"};
       // ClientDAO clt=new ClientDAO();
        ClientBLL clt=new ClientBLL();
        // System.out.println();
        List<Client> clientss = clt.findAllClients();
        List<Client> clientssnumber = clt.findAllClients();
        int nb=0;
        for (Client c : clientssnumber) {
            nb++;
        }
        /*String[][] info = new String[nb+1][4];

        int i=0,j=0;
        for (Client c : clientss){
            info[i][0] = Integer.toString(c.getId());
            info[i][1] = c.getNume();
            info[i][2] = c.getPrenume();
            info[i][3] = c.getEmail();
            //ultimId=c.getId();
            i++;
        }
        JTable tabelClienti=new JTable(info,coloane);
        JScrollPane sp=new JScrollPane(tabelClienti);*/
        //JTable tabelClienti=clt.createTable(clientss);
        //tabelClienti=clt.createTable(clientss);
        JTable tabelClienti=clt.createTableClients(clientss);
        JScrollPane sp=new JScrollPane(tabelClienti);
        sp.setBounds(0,400,750,60);

        //this.panel1.add(sp);
        return sp;
    }

}
