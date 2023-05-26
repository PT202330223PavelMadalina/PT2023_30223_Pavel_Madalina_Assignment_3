import DAO.AbstractDAO;
import DAO.ClientDAO;
import model.Client;
import presentation.*;

import java.sql.SQLException;

public class Main {
    /**
     * Clasa care contine metoda main, unde practic se porneste programul
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
      // ClientView c=new ClientView();
       //ProductView p=new ProductView();
        //OrderrView o=new OrderrView();
        //ClientDAO c=new ClientDAO();
      Controller cc=new Controller();
       // ProductView p=new ProductView();
        //String j=c.createInsertQuery("'"+"mada"+"', '"+"pavel"+"','"+"email'");
        //System.out.println(j.toString());
        //System.out.println(  c.createInsertQuery("haha").toString());


    }
}

