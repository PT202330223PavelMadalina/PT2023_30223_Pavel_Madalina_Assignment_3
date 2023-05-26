package presentation;

import DAO.ClientDAO;
import DAO.ProductDao;
import bll.ClientBLL;
import bll.ProductBLL;
import model.Client;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

public class OrderrView {
    /**
     * Clasa care cuprinde interfata Comenzii dar si doua metode care creaza cate un tabel cu produsele si clientii existenti
     */
    private JFrame frame;
    private JTable tabelClienti;
    private JTable tabelProduse;
    private JScrollPane spC;
    private JScrollPane spP;
    private JPanel panel1 = new JPanel();

    public JTable getTabelClienti() {
        return tabelClienti;
    }

    public JTable getTabelProduse() {
        return tabelProduse;
    }

    private Label l1 = new Label("ORDER");
    private Label l2 = new Label("Client:");
    private Label l3 = new Label("Product:");
    private Label l4 = new Label("Quantity:");
    private Label l5 = new Label("Total:");
    ///private JTextField t1=new JTextField();
    private JComboBox products = new JComboBox<>();
    private JTextField t2 = new JTextField();
    private JTextField t3 = new JTextField();
    private JComboBox clients = new JComboBox<>();
    private JButton addButton = new JButton("ADD ORDER");
    private JButton backButton = new JButton("BACK");

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

    public void setTabelClienti(JTable tabelClienti) {
        this.tabelClienti = tabelClienti;
    }

    public void setSpC(JScrollPane spC) {
        this.spC = spC;
    }

    public void setSpP(JScrollPane spP) {
        this.spP = spP;
    }

    public JScrollPane getSpC() {
        return spC;
    }

    public JScrollPane getSpP() {
        return spP;
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

    public Label getL5() {
        return l5;
    }

    public void setL5(Label l5) {
        this.l5 = l5;
    }

    public JComboBox getProducts() {
        return products;
    }

    public void setProducts(JComboBox products) {
        this.products = products;
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

    public JComboBox getClients() {
        return clients;
    }

    public void setClients(JComboBox clients) {
        this.clients = clients;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }

    public OrderrView() {
        frame = new JFrame("ORDER VIEW");
        ClientDAO cl = new ClientDAO();

        panel1.setBounds(0, 0, 700, 500);
        panel1.setBackground(new Color(135, 206, 250));
        panel1.setLayout(null);
        l1.setBounds(220, 0, 300, 30);
        l1.setFont(new Font("serif", Font.BOLD, 20));
        l1.setForeground(new Color(70, 130, 180));
        panel1.add(l1);


        l2.setBounds(20, 50, 100, 30);
        l2.setFont(new Font("serif", Font.ITALIC, 17));
        l2.setForeground(new Color(70, 130, 180));
        panel1.add(l2);
        // clients.setBounds(150,50,200,30);
        //panel1.add(clients);
        /*String[][] clientData = new String[15][3];
        ClientDAO client = new ClientDAO();
        List<Client> clients = client.findAll();
        int i=0;
        for (Client c : clients){
            clientData[i][0] = Integer.toString(c.getId());
            clientData[i][1] = c.getName();
            i++;
        }*/


        l3.setBounds(20, 130, 100, 30);
        l3.setFont(new Font("serif", Font.ITALIC, 17));
        l3.setForeground(new Color(70, 130, 180));
        panel1.add(l3);
        //products.setBounds(150,100,200,30);
        //panel1.add(products);


        l4.setBounds(20, 200, 100, 30);
        l4.setFont(new Font("serif", Font.ITALIC, 17));
        l4.setForeground(new Color(70, 130, 180));
        panel1.add(l4);
        t2.setBounds(150, 200, 100, 30);
        panel1.add(t2);


        l5.setBounds(20, 250, 100, 30);
        l5.setFont(new Font("serif", Font.ITALIC, 17));
        l5.setForeground(new Color(70, 130, 180));
        panel1.add(l5);
        t3.setBounds(150, 250, 100, 30);
        panel1.add(t3);


        addButton.setBackground(new Color(0, 0, 128));
        addButton.setForeground(Color.white);
        addButton.setBounds(150, 300, 200, 30);
        panel1.add(addButton);


        backButton.setBackground(new Color(0, 0, 128));
        backButton.setForeground(Color.white);
        backButton.setBounds(150, 350, 200, 30);
        panel1.add(backButton);

        spC = createTabelClienti();
        panel1.add(spC);

        spP = creareTabelProduse();
        panel1.add(spP);


        frame.setBounds(0, 0, 800, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel1);
        frame.setVisible(true);


    }

    public JScrollPane createTabelClienti() {
        //String[] coloane={"ID","NUME","PRENUME","EMAIL"};
        //ClientDAO clt=new ClientDAO();
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
        JTable tabell=clt.createTableClients(clientss);
        this.tabelClienti=tabell;
        JScrollPane sp=new JScrollPane(tabell);

        sp.setBounds(150,50,600,60);
        //this.panel1.add(sp);
        return sp;
    }

    public  JScrollPane creareTabelProduse() {
        //String[] coloane={"ID","NUME","PRENUME","EMAIL"};
        //ProductDao pp=new ProductDao();
        ProductBLL pp=new ProductBLL();
        // System.out.println();
        List<Product> productt = pp.findAllProducts();
        List<Product> producttnumber = pp.findAllProducts();
        int nb=0;
        for (Product p : producttnumber) {
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
        JTable tabell=pp.createTableProducts(productt);
        this.tabelProduse=tabell;
        JScrollPane sp=new JScrollPane(tabell);


        sp.setBounds(150,130,600,60);
        //this.panel1.add(sp);
        return sp;
    }

    public TableModel selectareTabelClienti(java.awt.event.MouseEvent evt) {
        TableModel modelClient = tabelClienti.getModel();
        /*int rowC = this.getTabelClienti().getSelectedRow();
        int colC = modelClient.getColumnCount();
        for (int i = 0; i < colC; i++) {
            Object value = modelClient.getValueAt(rowC, i);
            System.out.println(value.toString());
        }*/
        return modelClient;
    }
    public TableModel selectareTabelProduse(java.awt.event.MouseEvent evt) {
        TableModel modelProdus = tabelProduse.getModel();
        /*int rowP = this.getTabelProduse().getSelectedRow();
        int colP = modelProdus.getColumnCount();
        for (int i = 0; i < colP; i++) {
            Object value = modelProdus.getValueAt(rowP, i);
            System.out.println(value.toString());
        }*/
        return modelProdus;
    }


}
