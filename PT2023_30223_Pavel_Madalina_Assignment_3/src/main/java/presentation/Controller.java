package presentation;

import DAO.ClientDAO;
import DAO.OrderDao;
import DAO.ProductDao;
import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import bll.validators.EmailValidator;
import model.Bill;
import model.Client;
import model.Orderr;
import model.Product;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Controller {
    /**
     * Cea mai importanta clasa din acest proiect.
     *  Aceasta gestioneaza interactiunea intre diferitele interfete grafice și logica de business a aplicatiei.
     *Prin intermediul constructorului, clasa Controller se asociaza cu interfetele grafice ClientView, ProductView și OrderrView.
     * Fiecare interfata are butoane si actiuni asociate, de care se ocupa aceasta clasa.
     *
     */
    private ClientView c;
    private ProductView pv;
    private OrderrView o;

    public Controller() {
        Principal p = new Principal();
        p.getClientt().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c = new ClientView();
                c.getBackButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        c.getF().setVisible(false);
                    }
                });

                c.getAddButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nume = c.getT1().getText();
                        String prenume = c.getT2().getText();
                        String email = c.getT3().getText();
                        EmailValidator em=new EmailValidator();
                        try{
                            em.validate(email);
                            ClientBLL cc=new ClientBLL();
                            cc.insertClient("'" + nume + "', '" + prenume + "','" + email + "'");
                            JOptionPane.showMessageDialog(null, "Clientul a fost adaugat cu succes!", " Succes", JOptionPane.INFORMATION_MESSAGE);
                        }catch(Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, "Introduceti un email valid!", " Error", JOptionPane.WARNING_MESSAGE);
                        }


                        //ClientDAO cc = new ClientDAO();
                        //cc.insert("'" + nume + "', '" + prenume + "','" + email + "'");


                    }
                });
                c.getDeleteButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nume = c.getT1().getText();
                        String prenume = c.getT2().getText();
                        String email = c.getT3().getText();
                        EmailValidator em=new EmailValidator();
                        try{
                            em.validate(email);
                            ClientBLL cc=new ClientBLL();
                            cc.deleteClient(email,"email");
                            JOptionPane.showMessageDialog(null, "Clientul a fost sters cu succes!", " Succes", JOptionPane.INFORMATION_MESSAGE);
                        }catch(Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, "Introduceti un email valid!", " Error", JOptionPane.WARNING_MESSAGE);
                        }

                        //ClientDAO cc = new ClientDAO();
                        //cc.delete(email, "email");

                    }
                });

                c.getViewButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        c.getPanel1().remove(c.getSp());
                        c.setSp(ClientView.createTabel());
                        c.getPanel1().add(c.getSp());

                    }
                });

                c.getEditButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String[] valori = new String[10];
                        String numele = new String();
                        String prenumele = new String();
                        String emailul = new String();
                        numele = c.getT1().getText();
                        prenumele = c.getT2().getText();
                        emailul = c.getT3().getText();
                        EmailValidator em=new EmailValidator();
                        try{
                            em.validate(emailul);
                        }catch(Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, "Introduceti un email valid!", " Error", JOptionPane.WARNING_MESSAGE);
                        }

                        Frameid f = new Frameid(numele, prenumele, emailul);
                        f.getB().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                //ClientDAO cll = new ClientDAO();
                                ClientBLL cll=new ClientBLL();
                                int id = Integer.parseInt(f.getT().getText());
                                if (!c.getT1().getText().isEmpty())
                                    cll.updateClient(id,c.getT1().getText(),"nume");
                                if (!c.getT2().getText().isEmpty())
                                    cll.updateClient(id,c.getT2().getText(),"prenume");
                                if (!c.getT3().getText().isEmpty())
                                    cll.updateClient(id,c.getT3().getText(),"email");
                                JOptionPane.showMessageDialog(null, "Detaliile clientului au fost modificate cu succes!", " Succes", JOptionPane.INFORMATION_MESSAGE);


                               // cll.update(id, valori);
                                f.getFrame().setVisible(false);

                            }
                        });
                    }
                });

            }
        });

        p.getComanda().addActionListener(new ActionListener() {
            int idClient;
            int idProdus;
            @Override
            public void actionPerformed(ActionEvent e) {
                o = new OrderrView();
                o.getTabelClienti().addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        TableModel modelClient=o.selectareTabelClienti(evt);
                        int rowC = o.getTabelClienti().getSelectedRow();
                       // int colC = modelClient.getColumnCount();
                        //for (int i = 0; i < colC; i++) {
                            //Object value = modelClient.getValueAt(rowC, i);
                           // System.out.println(value.toString());
                            //OrderDao odd=new OrderDao();
                            idClient=Integer.parseInt(modelClient.getValueAt(rowC,0).toString());
                            System.out.println(idClient);

                        //}
                    }
                } );
                o.getTabelProduse().addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        TableModel modelProdus=o.selectareTabelProduse(evt);
                        int rowC = o.getTabelProduse().getSelectedRow();
                        // int colC = modelClient.getColumnCount();
                        //for (int i = 0; i < colC; i++) {
                        //Object value = modelClient.getValueAt(rowC, i);
                        // System.out.println(value.toString());
                        //OrderDao odd=new OrderDao();
                        idProdus=Integer.parseInt(modelProdus.getValueAt(rowC, 0).toString());
                        System.out.println(idProdus);
                    }
                } );

                o.getBackButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        o.getFrame().setVisible(false);
                    }
                });
                o.getAddButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int cantitate=0;
                        try {
                            cantitate=Integer.parseInt(o.getT2().getText());
                        } catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, "Va rugam introduceti cantitatea!", " Error", JOptionPane.WARNING_MESSAGE);
                        }


                        //ProductDao prod=new ProductDao();
                        ProductBLL prod=new ProductBLL();
                        Product produs=prod.findProductById(idProdus);
                        //if(cantitate)
                        float pret=cantitate*produs.getPret();
                        int stoc=produs.getStoc();
                        o.getT3().setText(String.valueOf(pret));
                        //OrderDao order=new OrderDao();
                        //ProductDao productDao=new ProductDao();
                        OrderBLL order=new OrderBLL();
                        ProductBLL productDao=new ProductBLL();
                        if(pret>0 && (cantitate>0 && cantitate<=stoc))
                        {
                            Orderr ord=new Orderr(idClient,idProdus,pret,cantitate);
                            List<Orderr> listComenzi=order.findAllOrders();
                            int iddd=listComenzi.size();
                            Bill.generare(ord,iddd);
                            order.insertOrder("'" + idClient + "', '" + idProdus + "','" + pret + "','"+cantitate+"'");
                            stoc=stoc-cantitate;
                            productDao.updateProducts(idProdus,String.valueOf(stoc),"stoc");
                            JOptionPane.showMessageDialog(null, "Comanda a fost plasata cu succes!", " Succes", JOptionPane.INFORMATION_MESSAGE);

                        }
                        else
                            if(cantitate>stoc)
                            {
                                o.getT3().setText("0");
                                JOptionPane.showMessageDialog(null, "Nu exista suficiente produse in stoc!", " Error", JOptionPane.WARNING_MESSAGE);
                            }
                    }
                });
            }
        });
        p.getProduss().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pv = new ProductView();
                pv.getBackButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pv.getFrame().setVisible(false);
                    }
                });
                pv.getAddButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nume = pv.getT1().getText();
                        String pret = pv.getT2().getText();
                        String stoc = pv.getT3().getText();
                       // ProductDao pp = new ProductDao();
                        ProductBLL pp=new ProductBLL();
                        pp.insertProduct("'" + nume + "', '" + pret + "','" + stoc + "'");
                        JOptionPane.showMessageDialog(null, "Produsul a fost adaugat cu succes!", " Succes", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                pv.getDeleteButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nume = pv.getT1().getText();
                        String pret = pv.getT2().getText();
                        String stoc = pv.getT3().getText();
                        String id = pv.getT4().getText();
                        //ProductDao pp = new ProductDao();
                        ProductBLL pp=new ProductBLL();
                        pp.deleteProduct(id, "id");
                        JOptionPane.showMessageDialog(null, "Produsul a fost sters cu succes!", " Succes", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                pv.getEditButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        /*String[] valori = new String[10];
                        String numele = new String();
                        String pret = new String();
                        String stoc = new String();
                        String ids=new String();
                        numele = pv.getT1().getText();
                        pret = pv.getT2().getText();
                        stoc = pv.getT3().getText();

                        valori[0] = numele;
                        valori[1] = pret;
                        valori[2] = stoc;
                        ProductDao pp = new ProductDao();
                        pp.update(id, valori);*/
                        String ids=pv.getT4().getText();
                        int id=Integer.parseInt(ids);
                        //ProductDao prod = new ProductDao();
                        ProductBLL prod=new ProductBLL();
                        if (!pv.getT1().getText().isEmpty())
                            prod.updateProducts(id,pv.getT1().getText(),"denumire");
                        if (!pv.getT2().getText().isEmpty())
                            prod.updateProducts(id,pv.getT2().getText(),"pret");
                        if (!pv.getT3().getText().isEmpty())
                            prod.updateProducts(id,pv.getT3().getText(),"stoc");
                        JOptionPane.showMessageDialog(null, "Detaliile produsului au fost modificate cu succes!", " Succes", JOptionPane.INFORMATION_MESSAGE);


                    }
                });
                pv.getViewButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pv.getPanel1().remove(pv.getSp());
                        pv.setSp(ProductView.creareTabel());
                        pv.getPanel1().add(pv.getSp());
                    }
                });

            }
        });
    }
}