package com.company.DAO;

import com.company.entities.Clients.Client;
import com.company.entities.Headphones.Headphones;
import com.company.entities.Role.Role;
import com.company.entities.Role.User;
import com.company.entities.order.Order;

import java.sql.*;
import java.util.ArrayList;

public class MyDAOSQL implements IMyDAO {
    Connection con;
    {
        try {
            con = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Headphones getHeadphonesByID(int id) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM Headphones WHERE headphones_id = ?");
            statement.setInt(1, id);
            statement.execute();
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return new Headphones(result.getInt("cost"), result.getInt("max_volume"), result.getString("name"),
                        result.getInt("headphones_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Headphones> getAllHeadphones() {
        try {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM headphones");
            ArrayList<Headphones> temp = new ArrayList<>();
            while (result.next()) {
                temp.add(new Headphones(result.getInt("cost"), result.getInt("max_volume"), result.getString("name"),
                        result.getInt("headphones_id")));
            }
            return temp;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insertHeadphones(Headphones headphones) {
        try {
            PreparedStatement statement =con.prepareStatement("INSERT INTO headphones(headphones_id, cost, max_volume, `name`) values(?, ?, ?, ?) ");
            statement.setInt(1,headphones.getId());
            statement.setInt(2, headphones.getCost());
            statement.setInt(3, headphones.getMax_volume());
            statement.setString(4, headphones.getName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteHeadphones(int id) {
        try {
            PreparedStatement statement = con.prepareStatement("DELETE FROM headphones WHERE headphones_id = ?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateHeadphones(Headphones headphones) {
        try {
            PreparedStatement statement = con.prepareStatement("UPDATE headphones SET cost=?, max_volume=?, `name`=? WHERE headphones_id=?");
            statement.setInt(1, headphones.getCost());
            statement.setInt(2, headphones.getMax_volume());
            statement.setString(3, headphones.getName());
            statement.setInt(4, headphones.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Headphones> getHeadphonesByMaxCost(int cost) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM headphones WHERE cost < ?");
            statement.setInt(1, cost);
            ArrayList<Headphones> temp = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                temp.add(new Headphones(resultSet.getInt("cost"), resultSet.getInt("max_volume"),
                        resultSet.getString("name"), resultSet.getInt("headphones_id")));
            }
            return temp;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Headphones> getHeadphonesByName(String name) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM headphones WHERE `name` = ?");
            statement.setString(1, name);
            ArrayList<Headphones> temp = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                temp.add(new Headphones(resultSet.getInt("cost"), resultSet.getInt("max_volume"),
                        resultSet.getString("name"), resultSet.getInt("headphones_id")));
            }
            return temp;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Client getClientByID(int id) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM client WHERE client_id = ?");
            statement.setInt(1, id);
            statement.execute();
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return new Client(result.getInt("client_id"),
                        result.getString("first_name"), result.getString("last_name")
                        , result.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Client> getAllClient() {
        try {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM client");
            ArrayList<Client> temp = new ArrayList<>();
            while (result.next()) {
                temp.add(new Client(result.getInt("client_id"),
                        result.getString("first_name"), result.getString("last_name")
                        , result.getString("email")));
            }
            return temp;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insertClient(Client client) {
        try{
            PreparedStatement statement =con.prepareStatement("INSERT INTO client(client_id, first_name, last_name, email) values(?, ?, ?, ?) ");
            statement.setInt(1,client.getId());
            statement.setString(2, client.getFirst_name());
            statement.setString(3, client.getLast_name());
            statement.setString(4, client.getEmail());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClient(int id) {
        try {
            PreparedStatement statement = con.prepareStatement("DELETE FROM client WHERE client_id = ?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateClient(Client client) {
        try {
            PreparedStatement statement = con.prepareStatement
                    ("UPDATE client SET first_name=?, last_name=?, email=? WHERE client_id=?");
            statement.setString(1, client.getFirst_name());
            statement.setString(2, client.getLast_name());
            statement.setString(3, client.getEmail());
            statement.setInt(4, client.getId());
            statement.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Client> getClientByName(String first_name, String last_name) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM client WHERE first_name = ? AND last_name = ?");
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            ArrayList<Client> temp = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                temp.add(new Client(resultSet.getInt("client_id"),
                        resultSet.getString("first_name"), resultSet.getString("last_name")
                        , resultSet.getString("email")));
            }
            return temp;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Client getClientByEmail(String email) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM client WHERE email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return new Client(resultSet.getInt("client_id"),
                        resultSet.getString("first_name"), resultSet.getString("last_name")
                        , resultSet.getString("email"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Order getOrderByID(int id) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM `order` WHERE order_id = ?");
            statement.setInt(1, id);
            statement.execute();
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return new Order(result.getInt("order_id"), getHeadphonesByID(result.getInt("headphones_id")),
                        getClientByID(result.getInt("client_id")), result.getString("status"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Order> getAllOrder() {
        try {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM `order`");
            ArrayList<Order> temp = new ArrayList<>();
            while (result.next()) {
                temp.add(new Order(result.getInt("order_id"), getHeadphonesByID(result.getInt("headphones_id")),
                        getClientByID(result.getInt("client_id")), result.getString("status")));
            }
            return temp;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insertOrder(Order order) {
        try {
            PreparedStatement statement =con.prepareStatement("INSERT INTO `order`(order_id, headphones_id, client_id, status) values(?, ?, ?, ?) ");
            statement.setInt(1,order.getId());
            statement.setInt(2, order.getHeadphones().getId());
            statement.setInt(3, order.getClient().getId());
            statement.setString(4, order.getStatus());
            statement.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int id) {
        try {
            PreparedStatement statement = con.prepareStatement("DELETE FROM `order` WHERE order_id = ?");
            statement.setInt(1, id);
            statement.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrder(Order order) {
        try {
            PreparedStatement statement = con.prepareStatement
                    ("UPDATE `order` SET headphones_id=?, client_id=?, status=? WHERE order_id=?");
            statement.setInt(4,order.getId());
            statement.setInt(1, order.getHeadphones().getId());
            statement.setInt(2, order.getClient().getId());
            statement.setString(3, order.getStatus());
            statement.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Order> getOrderByClient(Client client) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM `order` WHERE client_id = ?");
            statement.setInt(1, client.getId());
            ArrayList<Order> temp = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                temp.add(new Order(resultSet.getInt("order_id"), getHeadphonesByID(resultSet.getInt("headphones_id")),
                        getClientByID(resultSet.getInt("client_id")), resultSet.getString("status")));
            }
            return temp;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Order> getOrderByHeadphones(Headphones headphones) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM `order` WHERE headphones_id = ?");
            statement.setInt(1, headphones.getId());
            ArrayList<Order> temp = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                temp.add(new Order(resultSet.getInt("order_id"), getHeadphonesByID(resultSet.getInt("headphones_id")),
                        getClientByID(resultSet.getInt("client_id")), resultSet.getString("status")));
            }
            return temp;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void registration(User user){
        Statement check;
        int count=0;
        try {
            check = con.createStatement();
            ResultSet res  = check.executeQuery("Select count(*)as count from user where id="+user.getId());
            res.next();
            count=res.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(count==0) {
                PreparedStatement statement = con.prepareStatement("INSERT INTO user(id, login, pass, role_id) values(?, ?, ?, ?) ");
                statement.setInt(1, user.getId());
                statement.setString(2, user.getLogin());
                statement.setString(3, user.getPass());
                statement.setInt(4, user.getRole().ordinal());
                statement.execute();
            }
            else {
                System.out.println("Пользователь с таким именем уже существует");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Role authorise(User user){
        Statement check;
        int count=0;
        try {
            check = con.createStatement();
            ResultSet res  = check.executeQuery("Select count(*)as count from user where id="+user.getId());
            res.next();
            count=res.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(count>=1){
            return user.getRole();
        }
        System.out.println("Пользователя с такими данными не найдено!");
        return user.getRole();
    }
}
