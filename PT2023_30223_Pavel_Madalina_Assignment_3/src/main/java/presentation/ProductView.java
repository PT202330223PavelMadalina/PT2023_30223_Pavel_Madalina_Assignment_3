package presentation;
import DAO.ClientDAO;
import DAO.ProductDao;
import bll.ProductBLL;
import model.Client;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProductView {
    /**
     * Clasa care cuprinde interfata Produsului dar si metoda de creare a tabelului de Produse existente
     */
    private JTable tabelProduse=new JTable();
    private JScrollPane sp=new JScrollPane();
    private JFrame frame;
    private JPanel panel1=new JPanel();
    private Label l1=new Label("PRODUCT");

    public Label getL5() {
        return l5;
    }

    public void setL5(Label l5) {
        this.l5 = l5;
    }

    public JTextField getT4() {
        return t4;
    }

    public void setT4(JTextField t4) {
        this.t4 = t4;
    }

    private Label l2=new Label("Name:");
    private Label l5=new Label("Id:");
    private JTextField t4=new JTextField();

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

    public JButton getBackButton() {
        return backButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }

    private Label l3=new Label("Price:");
    private Label l4=new Label("Stock:");
    private JTextField t1=new JTextField();
    private JTextField t2=new JTextField();
    private JTextField t3=new JTextField();
    private JButton addButton=new JButton("ADD PRODUCT");
    private JButton deleteButton=new JButton("DELETE PRODUCT");
    private JButton editButton=new JButton("EDIT PRODUCT");
    private JButton viewButton=new JButton("VIEW PRODUCTS");
    private JButton backButton=new JButton("BACK");

    public JTable getTabelProduse() {
        return tabelProduse;
    }

    public JScrollPane getSp() {
        return sp;
    }

    public void setSp(JScrollPane sp) {
        this.sp = sp;
    }

    public ProductView()
    {
         frame = new JFrame("PRODUCT VIEW");

        panel1.setBounds(0,0,700,500);
        panel1.setBackground(new Color(135,206,250));
        panel1.setLayout(null);
        l1.setBounds(220,0,300,30);
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

        l5.setBounds(20,200,100,30);
        l5.setFont(new Font("serif",Font.ITALIC,17));
        l5.setForeground(new Color(70,130,180));
        panel1.add(l5);

        t4.setBounds(150,200,100,30);
        panel1.add(t4);

        addButton.setBackground(new Color(0,0,128));
        addButton.setForeground(Color.white);
        addButton.setBounds(20,250,200,30);
        panel1.add(addButton);

        deleteButton.setBackground(new Color(0,0,128));
        deleteButton.setForeground(Color.white);
        deleteButton.setBounds(20,300,200,30);
        panel1.add(deleteButton);

        editButton.setBackground(new Color(0,0,128));
        editButton.setForeground(Color.white);
        editButton.setBounds(250,250,200,30);
        panel1.add(editButton);

        viewButton.setBackground(new Color(0,0,128));
        viewButton.setForeground(Color.white);
        viewButton.setBounds(250,300,200,30);
        panel1.add(viewButton);

        backButton.setBackground(new Color(0,0,128));
        backButton.setForeground(Color.white);
        backButton.setBounds(150,350,200,30);
        panel1.add(backButton);

        sp=creareTabel();
        panel1.add(sp);



        frame.setBounds(0, 0, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel1);
        frame.setVisible(true);



    }

    public static JScrollPane creareTabel()
    {
        String[] coloane={"ID","NUME","PRET","STOC"};
        //ProductDao prod=new ProductDao();
        ProductBLL prod=new ProductBLL();
        // System.out.println();
        List<Product> produsse = prod.findAllProducts();
        List<Product> productnumber = prod.findAllProducts();
        int nb=0;
        for ( Product p : productnumber) {
            nb++;
        }
       /* String[][] info = new String[nb+1][4];

        int i=0,j=0;
        for (Product c : produsse){
            info[i][0] = Integer.toString(c.getId());
            info[i][1] = c.getDenumire();
            info[i][2] = String.valueOf(c.getPret());
            info[i][3] = String.valueOf(c.getStoc());
            i++;
        }
        JTable tabelProduse=new JTable(info,coloane);*/
        JTable tabelProduse=prod.createTableProducts(produsse);
        JScrollPane sp=new JScrollPane(tabelProduse);

        sp.setBounds(0,400,750,60);
        return sp;
    }
}
