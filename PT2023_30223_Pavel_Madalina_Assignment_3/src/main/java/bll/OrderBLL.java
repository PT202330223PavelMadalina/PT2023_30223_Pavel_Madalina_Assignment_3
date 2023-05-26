package bll;

import DAO.OrderDao;
import model.Orderr;

import java.util.List;
import java.util.NoSuchElementException;

public class OrderBLL {
    /**
     * Aici sunt apelate toate metodele din clase OrderDao.
     */
    private OrderDao orderDao;

    public OrderBLL() {
        orderDao = new OrderDao();
    }

    public Orderr findOrderById(int id) {
        Orderr cl = orderDao.findById(id);
        if (cl == null) {
            throw new NoSuchElementException("Comanda cu id-ul =" + id + " nu a fost gasita!");
        }
        return cl;
    }

    public void insertOrder(String t) {
        orderDao.insert(t);
    }
    public void deleteOrder(String t,String nume) {
        orderDao.delete(t,nume);
    }
    public void updateOrder(int id, String valoare,String camp) {
        orderDao.update(id,valoare,camp);
    }
    public List<Orderr> findAllOrders() {
        return orderDao.findAll();
    }

}
