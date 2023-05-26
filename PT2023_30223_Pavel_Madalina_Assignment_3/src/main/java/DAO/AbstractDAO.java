package DAO;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.ConnectionW;
import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class AbstractDAO<T> {
    /**
     *Clasa "AbstractDAO" este o clasa generica, care furnizeaza metode de baza pentru operatiile CRUD (Create, Read, Update, Delete)
     * pe entitati din baza de date.
     *  Clasa este folosita ca baza pentru clasele DAO  specifice fiecarei entitati din aplicatie
     *  si permite interactiunea simpla cu baza de date prin metodele definite.
     *  Din aceasta se extind  inca doua clase, care ii utilizeaza metodele pentru obiecte de tip
     *  Client si Product
     */
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        //System.out.println(sb.toString());
        return sb.toString();
    }

    public String createInsertQuery(String valori) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append("(");
        for (Field field : type.getDeclaredFields()) {
            String fieldName = field.getName();
            if(fieldName.equals("id")==false)
            {sb.append(fieldName);
               sb.append(",");}
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");
        sb.append(" VALUES(");
        sb.append(valori);
        sb.append(");");


        return sb.toString();
    }

    public String createDeleteQuery(String valoare) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE "+valoare+" = ?"+";");
        return sb.toString();
    }

    public String createUpdateQuery(int id, String valoare, String camp) {
        StringBuilder sb = new StringBuilder();
        StringBuilder field=new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");


        for (Field fields: type.getDeclaredFields()) {
            String fieldName = fields.getName();

            if(fieldName.equals(camp)==true)
            {
                sb.append(fieldName);
                sb.append("='");
                sb.append(valoare);
                sb.append("',");
            }

        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(" WHERE ");
        sb.append("id");
        sb.append(" = '");
        sb.append(id);
        sb.append("';");
        System.out.println(sb.toString());

        return sb.toString();
    }




    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionW.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionW.close(resultSet);
            ConnectionW.close(statement);
            ConnectionW.close(connection);
        }
        return null;
    }

    public JTable createTable(List<T> objects) {
        Class<?> objectClass = objects.get(0).getClass();
        Field[] fields = objectClass.getDeclaredFields();
        String[] coloane = new String[4];

        for (int i = 0; i < 4; i++) {
            coloane[i] = fields[i].getName();
        }
        DefaultTableModel modelData = new DefaultTableModel(coloane, 0);
        for (T obj : objects) {
            Object[] info = new Object[4];

            for (int i = 0; i <4; i++) {
                fields[i].setAccessible(true);
                try {
                    info[i] = fields[i].get(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            modelData.addRow(info);
        }
        JTable table = new JTable(modelData);

        // Create a JScrollPane to hold the table
        //JScrollPane scrollPane = new JScrollPane(table);

        return table;
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionW.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionW.close(resultSet);
            ConnectionW.close(statement);
            ConnectionW.close(connection);
        }
        return null;
    }



    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    public T insert(String t) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery(t);
        try {
            connection = ConnectionW.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

            //return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionW.close(resultSet);
            ConnectionW.close(statement);
            ConnectionW.close(connection);
        }
        return null;
    }

    public T delete(String t,String nume) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createDeleteQuery(nume);
        try {
            connection = ConnectionW.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1,t);
            statement.executeUpdate();

            //return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionW.close(resultSet);
            ConnectionW.close(statement);
            ConnectionW.close(connection);
        }
        return null;
    }

    public T update(int id,String valoare,String camp) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String h = new String();
        String query = createUpdateQuery(id,valoare,camp);
        try {
            connection = ConnectionW.getConnection();
            statement = connection.prepareStatement(query);
            //statement.setInt(1,id);
            statement.executeUpdate();

            //return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionW.close(resultSet);
            ConnectionW.close(statement);
            ConnectionW.close(connection);
        }
        return null;
    }

}
