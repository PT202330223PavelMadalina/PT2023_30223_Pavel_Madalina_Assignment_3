package bll;

import DAO.ProductDao;
import model.Product;

import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {
    /**
     * Aici sunt apelate practic metodele din clasa ProductDao, pentru a pute avea o logica mai buna a programului.
     *
     */
    private ProductDao productDao;

    public ProductBLL() {
        productDao = new ProductDao();
    }

    public Product findProductById(int id) {
        Product cl = productDao.findById(id);
        if (cl == null) {
            throw new NoSuchElementException("Produsul cu id-ul =" + id + " nu a fost gasit!");
        }
        return cl;
    }

    public void insertProduct(String t) {
        productDao.insert(t);
    }
    public void deleteProduct(String t,String nume) {
        productDao.delete(t,nume);
    }
    public void updateProducts(int id, String valoare,String camp) {
        productDao.update(id,valoare,camp);
    }
    public List<Product> findAllProducts() {
        return productDao.findAll();
    }
    public JTable createTableProducts(List<Product> produse) {
        return productDao.createTable(produse);
    }

}
