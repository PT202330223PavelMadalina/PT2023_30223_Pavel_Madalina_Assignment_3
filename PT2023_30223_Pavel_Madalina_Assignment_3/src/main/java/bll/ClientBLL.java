package bll;

import DAO.ClientDAO;
import bll.validators.EmailValidator;
import bll.validators.Validator;
import model.Client;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {
    /**
     * Aici sunt apelate toate metodele din clasa ClientDao.
     */
    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        clientDAO = new ClientDAO();
    }

    public Client findClientById(int id) {
        Client cl = clientDAO.findById(id);
        if (cl == null) {
            throw new NoSuchElementException("Clientul cu id-ul =" + id + " nu a fost gasit!");
        }
        return cl;
    }

    public void insertClient(String t) {
        clientDAO.insert(t);
    }
    public void deleteClient(String t,String nume) {
        clientDAO.delete(t,nume);
    }
    public void updateClient(int id, String valoare,String camp) {
        clientDAO.update(id,valoare,camp);
    }
    public List<Client> findAllClients() {
        return clientDAO.findAll();
    }
    public JTable createTableClients(List<Client> clienti) {
        return clientDAO.createTable(clienti);
    }

}
