package com.company.DAO;

import com.company.entities.Clients.Client;
import com.company.entities.Headphones.Headphones;
import com.company.entities.order.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyDAOList implements IMyDAO {
    private ArrayList<Headphones> headphonesArr = new ArrayList<>();
    @Override
    public Headphones getHeadphonesByID(int id) {
        for (Headphones headphones : headphonesArr) {
            if (headphones.getId() == id) {
                return headphones;
            }
        }

        return null;
    }

    @Override
    public ArrayList<Headphones> getAllHeadphones() {
        return headphonesArr;
    }

    @Override
    public void insertHeadphones(Headphones headphones) {
        headphones.setId(headphonesArr.size());
        headphonesArr.add(headphones);
    }

    @Override
    public void deleteHeadphones(int id) {
        headphonesArr.removeIf((Headphones c) -> c.getId() == id);
    }

    @Override
    public void updateHeadphones(Headphones headphones) {
        AtomicBoolean wasUpdated = new AtomicBoolean(false);
        headphonesArr.replaceAll((Headphones c) -> {
            if (c.getId() == headphones.getId()) {
                wasUpdated.set(true);
                return headphones;}
            return c;
        });
    }

    @Override
    public ArrayList<Headphones> getHeadphonesByMaxCost(int cost) {
        ArrayList<Headphones> temp = new ArrayList<>();
        for (Headphones headphones : headphonesArr) {
            if (headphones.getCost() == cost) {
                temp.add(headphones);
            }
        }
        return temp;
    }

    @Override
    public ArrayList<Headphones> getHeadphonesByName(String name) {
        ArrayList<Headphones> temp = new ArrayList<>();
        for (Headphones headphones : headphonesArr) {
            if (headphones.getName() == name) {
                temp.add(headphones);
            }
        }
        return temp;
    }

    private ArrayList<Client> clientArr = new ArrayList<>();

    @Override
    public Client getClientByID(int id) throws SQLException {
        for (Client client : clientArr) {
            if (client.getId() == id) {
                return client;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Client> getAllClient() throws SQLException {
        return clientArr;
    }

    @Override
    public void insertClient(Client client) throws SQLException {
        client.setId(clientArr.size());
        clientArr.add(client);
    }

    @Override
    public void deleteClient(int id) throws SQLException {
        clientArr.removeIf((Client c) -> c.getId() == id);
    }

    @Override
    public void updateClient(Client client) throws SQLException {
        AtomicBoolean wasUpdated = new AtomicBoolean(false);
        clientArr.replaceAll((Client c) -> {
            if (c.getId() == client.getId()) {
                wasUpdated.set(true);
                return client;}
            return c;
        });
    }

    @Override
    public ArrayList<Client> getClientByName(String first_name, String last_name) throws SQLException {
        ArrayList<Client> temp = new ArrayList<>();
        for (Client client : clientArr) {
            if (client.getFirst_name() == first_name && client.getLast_name() == last_name) {
                temp.add(client);
            }
        }
        return temp;
    }

    @Override
    public Client getClientByEmail(String email) throws SQLException {
        for (Client client : clientArr) {
            if (client.getEmail() == email) {
                return client;
            }
        }
        return null;
    }

    private ArrayList<Order> orderArr = new ArrayList<>();

    @Override
    public Order getOrderByID(int id) throws SQLException {
        for (Order order : orderArr) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Order> getAllOrder() throws SQLException {
        return orderArr;
    }

    @Override
    public void insertOrder(Order order) throws SQLException {
        order.setId(orderArr.size());
        orderArr.add(order);
    }

    @Override
    public void deleteOrder(int id) throws SQLException {
        orderArr.removeIf((Order c) -> c.getId() == id);
    }

    @Override
    public void updateOrder(Order order) throws SQLException {
        AtomicBoolean wasUpdated = new AtomicBoolean(false);
        orderArr.replaceAll((Order c) -> {
            if (c.getId() == order.getId()) {
                wasUpdated.set(true);
                return order;}
            return c;
        });
    }

    @Override
    public ArrayList<Order> getOrderByClient(Client client) throws SQLException {
        ArrayList<Order> temp = new ArrayList<>();
        for (Order order : orderArr) {
            if (order.getClient() == client) {
                temp.add(order);
            }
        }
        return temp;
    }

    @Override
    public ArrayList<Order> getOrderByHeadphones(Headphones headphones) throws SQLException {
        ArrayList<Order> temp = new ArrayList<>();
        for (Order order : orderArr) {
            if (order.getHeadphones() == headphones) {
                temp.add(order);
            }
        }
        return temp;
    }
}
