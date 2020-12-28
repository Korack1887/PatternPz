package com.company.DAO;

import com.company.entities.Clients.Client;
import com.company.entities.Headphones.Headphones;
import com.company.entities.Role.Role;
import com.company.entities.Role.User;
import com.company.entities.order.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IMyDAO {
    ///////////////////////////////////////////////////////////////////Наушники
    Headphones getHeadphonesByID(int id) throws SQLException;
    ArrayList<Headphones> getAllHeadphones() throws SQLException;
    void insertHeadphones(Headphones headphones) throws SQLException;
    void deleteHeadphones(int id) throws SQLException;
    void updateHeadphones(Headphones headphones) throws SQLException;
    ArrayList<Headphones> getHeadphonesByMaxCost(int cost) throws SQLException;
    ArrayList<Headphones> getHeadphonesByName(String name) throws SQLException;

    /////////////////////////////////////////////////////////////////////////////Клиенты
    Client getClientByID(int id) throws SQLException;
    ArrayList<Client> getAllClient() throws SQLException;
    void insertClient(Client client) throws SQLException;
    void deleteClient(int id) throws SQLException;
    void updateClient(Client client) throws SQLException;
    ArrayList<Client> getClientByName(String first_name, String last_name) throws SQLException;
    Client getClientByEmail(String email) throws SQLException;

    /////////////////////////////////////////////////////////////////////////////Заказы
    Order getOrderByID(int id) throws SQLException;
    ArrayList<Order> getAllOrder() throws SQLException;
    void insertOrder(Order client) throws SQLException;
    void deleteOrder(int id) throws SQLException;
    void updateOrder(Order client) throws SQLException;
    ArrayList<Order> getOrderByClient(Client client) throws SQLException;
    ArrayList<Order> getOrderByHeadphones(Headphones headphones) throws SQLException;
    ///////////////////////////////////////////////////////////////////////////////////
    void registration(User user);
    Role authorise(User user);
}
