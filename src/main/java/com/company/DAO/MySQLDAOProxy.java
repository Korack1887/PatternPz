package com.company.DAO;

import com.company.entities.Clients.Client;
import com.company.entities.Headphones.Headphones;
import com.company.entities.Role.Role;
import com.company.entities.Role.User;
import com.company.entities.order.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLDAOProxy implements IMyDAO {
    User user;
    IMyDAO sql = new MyDAOSQL();

    public MySQLDAOProxy() {
        user=new User();
    }

    public MySQLDAOProxy(User user) {
        this.user = user;
    }

    @Override
    public Headphones getHeadphonesByID(int id) throws SQLException {
        return sql.getHeadphonesByID(id);
    }

    @Override
    public ArrayList<Headphones> getAllHeadphones() throws SQLException {
        return sql.getAllHeadphones();
    }

    @Override
    public void insertHeadphones(Headphones headphones) throws SQLException {
        if(sql.authorise(user).equals(Role.ADMIN)){
            sql.insertHeadphones(headphones);
        }
        else {
            System.out.println("В доступе отказано!");
        }
    }

    @Override
    public void deleteHeadphones(int id) throws SQLException {
        if(sql.authorise(user).equals(Role.ADMIN)){
            sql.deleteHeadphones(id);
        }
        else {
            System.out.println("В доступе отказано!");
        }
    }

    @Override
    public void updateHeadphones(Headphones headphones) throws SQLException {
        if(sql.authorise(user).equals(Role.ADMIN)){
            sql.updateHeadphones(headphones);
        }
        else {
            System.out.println("В доступе отказано!");
        }
    }

    @Override
    public ArrayList<Headphones> getHeadphonesByMaxCost(int cost) throws SQLException {
        return sql.getHeadphonesByMaxCost(cost);
    }

    @Override
    public ArrayList<Headphones> getHeadphonesByName(String name) throws SQLException {
        return sql.getHeadphonesByName(name);
    }

    @Override
    public Client getClientByID(int id) throws SQLException {
        return sql.getClientByID(id);
    }

    @Override
    public ArrayList<Client> getAllClient() throws SQLException {
        return sql.getAllClient();
    }

    @Override
    public void insertClient(Client client) throws SQLException {
        if(sql.authorise(user).equals(Role.ADMIN)){
            sql.insertClient(client);
        }
        else {
            System.out.println("В доступе отказано!");
        }
    }

    @Override
    public void deleteClient(int id) throws SQLException {
        if(sql.authorise(user).equals(Role.ADMIN)){
            sql.deleteClient(id);
        }
        else {
            System.out.println("В доступе отказано!");
        }
    }

    @Override
    public void updateClient(Client client) throws SQLException {
            sql.updateClient(client);
    }

    @Override
    public ArrayList<Client> getClientByName(String first_name, String last_name) throws SQLException {
        return sql.getClientByName(first_name, last_name);
    }

    @Override
    public Client getClientByEmail(String email) throws SQLException {
        return sql.getClientByEmail(email);
    }

    @Override
    public Order getOrderByID(int id) throws SQLException {
        return sql.getOrderByID(id);
    }

    @Override
    public ArrayList<Order> getAllOrder() throws SQLException {
        return sql.getAllOrder();
    }

    @Override
    public void insertOrder(Order client) throws SQLException {
        if(sql.authorise(user).equals(Role.ADMIN)){
            sql.insertOrder(client);
        }
        else {
            System.out.println("В доступе отказано!");
        }
    }

    @Override
    public void deleteOrder(int id) throws SQLException {
        if(sql.authorise(user).equals(Role.ADMIN)) {
            sql.deleteOrder(id);
        }
        else {
            System.out.println("В доступе отказано!");
        }
    }

    @Override
    public void updateOrder(Order client) throws SQLException {
        if(sql.authorise(user).equals(Role.ADMIN)){
            sql.updateOrder(client);
        }
        else {
            System.out.println("В доступе отказано!");
        }
    }

    @Override
    public ArrayList<Order> getOrderByClient(Client client) throws SQLException {
        return sql.getOrderByClient(client);
    }

    @Override
    public ArrayList<Order> getOrderByHeadphones(Headphones headphones) throws SQLException {
        return sql.getOrderByHeadphones(headphones);
    }

    @Override
    public void registration(User user) {
    }

    @Override
    public Role authorise(User user) {
        return Role.USER;
    }
}
