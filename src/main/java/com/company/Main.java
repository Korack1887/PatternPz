package com.company;

        import com.company.DAO.*;
        import com.company.DAO.DAOFactory;
        import com.company.entities.Caretaker;
        import com.company.entities.Clients.Client;
        import com.company.entities.Headphones.Headphones;
        import com.company.entities.ObserverOrder;
        import com.company.entities.order.Order;

        import java.sql.SQLException;
        import java.util.ArrayList;

public class Main {

       public static void main(String[] args) throws SQLException {

           IMyDAO mongo = DAOFactory.getDAOInstance(TypeDAO.Mongo);

            mongo.getAllHeadphones();
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
