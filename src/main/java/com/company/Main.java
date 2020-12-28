package com.company;

        import com.company.DAO.*;
        import com.company.DAO.DAOFactory;
        import com.company.entities.Caretaker;
        import com.company.entities.Clients.Client;
        import com.company.entities.Headphones.Headphones;
        import com.company.entities.ObserverOrder;
        import com.company.entities.Role.Role;
        import com.company.entities.Role.User;
        import com.company.entities.order.Order;

        import java.sql.SQLException;
        import java.util.ArrayList;

public class Main {

       public static void main(String[] args) throws SQLException {
           User test = new User.Builder().id(1).login("test").pass("test").role(Role.ADMIN).build();
           User test2 = new User.Builder().id(2).login("test2").pass("test2").role(Role.USER).build();
           DAOFactory fact = new DAOFactory();
           fact.setUser(test2);
           IMyDAO sql = fact.getDAOInstance(TypeDAO.MySQL);
           sql.deleteHeadphones(20);
           fact.setUser(test);
           sql = fact.getDAOInstance(TypeDAO.MySQL);
           sql.deleteHeadphones(20);
        }
    public static void sqlToMongo(IMyDAO mongo,IMyDAO sql) throws SQLException {
        for (Headphones c: sql.getAllHeadphones()
        ) {
            try {
                mongo.insertHeadphones(c);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for (Client c: sql.getAllClient()
        ) {
            try {
                mongo.insertClient(c);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for (Order p : sql.getAllOrder()
        ) {
            try {
                mongo.insertOrder(p);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void mongoToSql(IMyDAO mongo,IMyDAO sql) throws SQLException {
        for (Headphones c: mongo.getAllHeadphones()
        ) {
            try {
                sql.insertHeadphones(c);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for (Client c: mongo.getAllClient()
        ) {
            try {
                sql.insertClient(c);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for (Order o: mongo.getAllOrder()
        ) {
            try {
                sql.insertOrder(o);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
