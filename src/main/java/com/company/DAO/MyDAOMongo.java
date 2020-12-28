package com.company.DAO;

import com.company.DAO.autoInc.clientCounter;
import com.company.DAO.autoInc.orderCounter;
import com.company.entities.Clients.Client;
import com.company.entities.Headphones.Headphones;
import com.company.entities.Role.Role;
import com.company.entities.Role.User;
import com.company.entities.order.Order;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import com.company.DAO.autoInc.headphoneCounter;

import java.sql.SQLException;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class MyDAOMongo implements IMyDAO {
    @Override
    public Headphones getHeadphonesByID(int id) throws SQLException {
        MongoDatabase con = ConnectionFactory.getConnectionMongo();
        MongoCollection<Document> collection = con.getCollection("headphone");
        Document doc = collection.find(new Document("id", id)).first();

        return  new Headphones.Builder().id(doc.getInteger("id"))
                .name(doc.getString("name"))
                .cost(doc.getInteger("cost"))
                .max_volume(doc.getInteger("max_volume")).build();
    }

    @Override
    public ArrayList<Headphones> getAllHeadphones() throws SQLException {
        ArrayList<Headphones> result =new ArrayList<>();
        MongoDatabase con = ConnectionFactory.getConnectionMongo();
        MongoCollection<Document> collection = con.getCollection("headphone");
        FindIterable<Document> iterable = collection.find(new Document());
        MongoCursor<Document> cursor = iterable.iterator();
        Document doc;
        while (cursor.hasNext()){
            doc=cursor.next();

            result.add(new Headphones.Builder().id(doc.getInteger("id"))
                    .name(doc.getString("name"))
                    .cost(doc.getInteger("cost"))
                    .max_volume(doc.getInteger("max_volume")).build());
        }
        return result;
    }

    @Override
    public void insertHeadphones(Headphones headphones) throws SQLException {
        MongoDatabase con = ConnectionFactory.getConnectionMongo();
        MongoCollection collection = con.getCollection("headphone");
        Document document = new Document("_id", new ObjectId());
        if(headphones.getId()==0) {
            document.put("id", new headphoneCounter().autoInc(getAllHeadphones()));
        }
        else {
            document.put("id", headphones.getId());
        }
        document.put("cost", headphones.getCost());
        document.put("max_volume", headphones.getMax_volume());
        document.put("name", headphones.getName());
        collection.insertOne(document);
    }

    @Override
    public void deleteHeadphones(int id) throws SQLException {
        MongoDatabase con = ConnectionFactory.getConnectionMongo();
        MongoCollection collection = con.getCollection("headphone");
        Document document = new Document("id", id);
        if(!con.getCollection("order").find(document).iterator().hasNext()) {
            DeleteResult r = collection.deleteOne(document);
            System.out.println(r);
        }
    }

    @Override
    public void updateHeadphones(Headphones headphones) throws SQLException {
        MongoDatabase con = ConnectionFactory.getConnectionMongo();
        MongoCollection collection = con.getCollection("headphone");
        Bson filter = eq("id", headphones.getId());
        Bson updateOperation = set("name", headphones.getName());
        collection.updateOne(filter, updateOperation);
        updateOperation = set("cost", headphones.getCost());
        collection.updateOne(filter, updateOperation);
        updateOperation = set("max_volume", headphones.getMax_volume());
        collection.updateOne(filter, updateOperation);
    }

    @Override
    public ArrayList<Headphones> getHeadphonesByMaxCost(int cost) throws SQLException {
        ArrayList<Headphones> result =new ArrayList<>();
        MongoDatabase con = ConnectionFactory.getConnectionMongo();
        MongoCollection<Document> collection = con.getCollection("headphone");
        FindIterable<Document> iterable = collection.find(new Document("cost",cost));
        MongoCursor<Document> cursor = iterable.iterator();
        Document doc;
        while (cursor.hasNext()){
            doc=cursor.next();
        result.add(new Headphones.Builder().id(doc.getInteger("id"))
                .name(doc.getString("name"))
        .cost(doc.getInteger("cost"))
        .max_volume(doc.getInteger("volume")).build());
        }
        return result;
    }

    @Override
    public ArrayList<Headphones> getHeadphonesByName(String name) throws SQLException {
        ArrayList<Headphones> result =new ArrayList<>();
        MongoDatabase con = ConnectionFactory.getConnectionMongo();
        MongoCollection<Document> collection = con.getCollection("headphone");
        FindIterable<Document> iterable = collection.find(new Document("name",name));
        MongoCursor<Document> cursor = iterable.iterator();
        Document doc;
        while (cursor.hasNext()){
            doc=cursor.next();
            result.add(new Headphones.Builder().id(doc.getInteger("id"))
                    .name(doc.getString("name"))
                    .cost(doc.getInteger("cost"))
                    .max_volume(doc.getInteger("volume")).build());
        }
        return result;
    }

    @Override
    public Client getClientByID(int id) throws SQLException {
        MongoDatabase con = ConnectionFactory.getConnectionMongo();
        MongoCollection<Document> collection = con.getCollection("client");
        Document doc = collection.find(new Document("id", id)).first();
        return new Client.Builder().id(doc.getInteger("id"))
                .first_name(doc.getString("first_name"))
                .last_name(doc.getString("last_name"))
                .email(doc.getString("email")).build();
    }

    @Override
    public ArrayList<Client> getAllClient() throws SQLException {
        ArrayList<Client> result =new ArrayList<>();
        MongoDatabase con = ConnectionFactory.getConnectionMongo();
        MongoCollection<Document> collection = con.getCollection("client");
        FindIterable<Document> iterable = collection.find(new Document());
        MongoCursor<Document> cursor = iterable.iterator();
        Document doc;
        while (cursor.hasNext()){
            doc=cursor.next();
            result.add(new Client.Builder().id(doc.getInteger("id"))
                    .first_name(doc.getString("first_name"))
                    .last_name(doc.getString("last_name"))
                    .email(doc.getString("email")).build());
        }
        return result;
    }

    @Override
    public void insertClient(Client client) throws SQLException {
        MongoDatabase con = ConnectionFactory.getConnectionMongo();
        MongoCollection collection = con.getCollection("client");
        Document document = new Document("_id", new ObjectId());
        if(client.getId()==0) {
            document.put("id", new clientCounter().autoInc(getAllClient()));
        }
        else {
            document.put("id", client.getId());
        }
        document.put("first_name", client.getFirst_name());
        document.put("last_name", client.getLast_name());
        document.put("email", client.getEmail());
        collection.insertOne(document);
    }

    @Override
    public void deleteClient(int id) throws SQLException {
        MongoDatabase con = ConnectionFactory.getConnectionMongo();
        MongoCollection collection = con.getCollection("client");
        Document document = new Document("id", id);
        if(!con.getCollection("order").find(document).iterator().hasNext()) {
            DeleteResult r = collection.deleteOne(document);
            System.out.println(r);
        }
    }

    @Override
    public void updateClient(Client client) throws SQLException {
        MongoDatabase con = ConnectionFactory.getConnectionMongo();
        MongoCollection collection = con.getCollection("client");
        Bson filter = eq("id", client.getId());
        Bson updateOperation = set("first_name", client.getFirst_name());
        collection.updateOne(filter, updateOperation);
        updateOperation = set("last_name", client.getLast_name());
        collection.updateOne(filter, updateOperation);
        updateOperation = set("email", client.getEmail());
        collection.updateOne(filter, updateOperation);
    }

    @Override
    public ArrayList<Client> getClientByName(String first_name, String last_name) throws SQLException {
        ArrayList<Client> result =new ArrayList<>();
        MongoDatabase con = ConnectionFactory.getConnectionMongo();
        MongoCollection<Document> collection = con.getCollection("client");
        Document fn = new Document();
        fn.put("first_name",first_name);
        fn.put("last_name",last_name);
        FindIterable<Document> iterable = collection.find(fn);
        MongoCursor<Document> cursor = iterable.iterator();
        Document doc;
        while (cursor.hasNext()){
            doc=cursor.next();
            result.add(new Client.Builder().id(doc.getInteger("id"))
                    .first_name(doc.getString("first_name"))
                    .last_name(doc.getString("last_name"))
                    .email(doc.getString("email")).build());
        }
        return result;
    }

    @Override
    public Client getClientByEmail(String email) throws SQLException {
        MongoDatabase con = ConnectionFactory.getConnectionMongo();
        MongoCollection<Document> collection = con.getCollection("client");
        Document doc = collection.find(new Document("email",email)).first();
        return new Client.Builder().id(doc.getInteger("id"))
                .first_name(doc.getString("first_name"))
                .last_name(doc.getString("last_name"))
                .email(doc.getString("email")).build();
    }

    @Override
    public Order getOrderByID(int id) throws SQLException {
        MongoDatabase con = ConnectionFactory.getConnectionMongo();
        MongoCollection<Document> collection = con.getCollection("order");
        Document doc = collection.find(new Document("order",id)).first();
        return new Order.Builder().id(doc.getInteger("id"))
                .client(getClientByID(doc.getInteger("client_id")))
                .headphones(getHeadphonesByID(doc.getInteger("headphone_id")))
                .status(doc.getString("status"))
                .build();
    }

    @Override
    public ArrayList<Order> getAllOrder() throws SQLException {
        ArrayList<Order> result =new ArrayList<>();
        MongoDatabase con = ConnectionFactory.getConnectionMongo();
        MongoCollection<Document> collection = con.getCollection("order");
        Document fn = new Document();
        FindIterable<Document> iterable = collection.find(fn);
        MongoCursor<Document> cursor = iterable.iterator();
        Document doc;
        while (cursor.hasNext()){
            doc=cursor.next();
            result.add(new Order.Builder().id(doc.getInteger("id"))
                    .client(getClientByID(doc.getInteger("client_id")))
                    .headphones(getHeadphonesByID(doc.getInteger("headphone_id")))
                    .status(doc.getString("status"))
                    .build());
        }
        return result;
    }

    @Override
    public void insertOrder(Order order) throws SQLException {
        MongoDatabase con = ConnectionFactory.getConnectionMongo();
        MongoCollection collection = con.getCollection("order");
        Document document = new Document("_id", new ObjectId());
        if(order.getId()==0) {
            document.put("id", new orderCounter().autoInc(getAllOrder()));
        }
        else {
            document.put("id", order.getId());
        }
        document.put("client_id", order.getClient().getId());
        document.put("headphone_id", order.getHeadphones().getId());
        document.put("status",order.getStatus());
        collection.insertOne(document);
    }

    @Override
    public void deleteOrder(int id) throws SQLException {
        MongoDatabase con = ConnectionFactory.getConnectionMongo();
        MongoCollection collection = con.getCollection("order");
        Document document = new Document("id", id);
            DeleteResult r = collection.deleteOne(document);
            System.out.println(r);
    }

    @Override
    public void updateOrder(Order order) throws SQLException {
        MongoDatabase con = ConnectionFactory.getConnectionMongo();
        MongoCollection collection = con.getCollection("order");
        Bson filter = eq("id", order.getId());
        Bson updateOperation = set("client_id", order.getClient().getId());
        collection.updateOne(filter, updateOperation);
        updateOperation = set("headphone_id", order.getHeadphones().getId());
        collection.updateOne(filter, updateOperation);
        updateOperation = set("status",order.getStatus());
        collection.updateOne(filter, updateOperation);
    }

    @Override
    public ArrayList<Order> getOrderByClient(Client client) throws SQLException {
        ArrayList<Order> result =new ArrayList<>();
        MongoDatabase con = ConnectionFactory.getConnectionMongo();
        MongoCollection<Document> collection = con.getCollection("order");
        Document fn = new Document("client_id",client.getId());
        FindIterable<Document> iterable = collection.find(fn);
        MongoCursor<Document> cursor = iterable.iterator();
        Document doc;
        while (cursor.hasNext()){
            doc=cursor.next();
            result.add(new Order.Builder().id(doc.getInteger("id"))
                    .client(getClientByID(doc.getInteger("client_id")))
                    .headphones(getHeadphonesByID(doc.getInteger("headphone_id")))
                    .status(doc.getString("status"))
                    .build());
        }
        return result;
    }

    @Override
    public ArrayList<Order> getOrderByHeadphones(Headphones headphones) throws SQLException {
        ArrayList<Order> result =new ArrayList<>();
        MongoDatabase con = ConnectionFactory.getConnectionMongo();
        MongoCollection<Document> collection = con.getCollection("order");
        Document fn = new Document("headphone_id",headphones.getId());
        FindIterable<Document> iterable = collection.find(fn);
        MongoCursor<Document> cursor = iterable.iterator();
        Document doc;
        while (cursor.hasNext()){
            doc=cursor.next();
            result.add(new Order.Builder().id(doc.getInteger("id"))
                    .client(getClientByID(doc.getInteger("client_id")))
                    .headphones(getHeadphonesByID(doc.getInteger("headphone_id")))
                    .status(doc.getString("status"))
                    .build());
        }
        return result;
    }

    @Override
    public void registration(User user) {

    }

    @Override
    public Role authorise(User user) {
        return Role.USER;
    }
}
