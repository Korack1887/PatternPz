package com.company.entities.order;

import com.company.entities.Clients.Client;
import com.company.entities.Headphones.Headphones;
import com.company.entities.ObserverOrder;
import javafx.util.Builder;

public class Order implements ObserverOrder {
    private int id;
    private Headphones headphones;
    private Client client;
    private String status;
    private int headphones_id;
    private int client_id;

    public Order(Headphones headphones, Client client, String status, int headphones_id, int client_id) {
        this.id=0;
        this.headphones = headphones;
        this.client = client;
        this.status = status;
        this.headphones_id = headphones_id;
        this.client_id = client_id;
    }

    public Order() {
        this.id=0;
        this.headphones=new Headphones();
        this.client=new Client();
        this.headphones_id=0;
        this.client_id= 0;
    }

    public int getHeadphones_id() {
        return headphones_id;
    }

    public void setHeadphones_id(int headphones_id) {
        this.headphones_id = headphones_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Headphones getHeadphones() {
        return headphones;
    }

    public void setHeadphones(Headphones headphones) {
        this.headphones = headphones;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order(int id, Headphones headphones, Client client, String status) {
        this.id = id;
        this.headphones = headphones;
        this.client = client;
        this.status = status;
    }
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", headphones=" + headphones +
                ", client=" + client +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public void updateClient(int id) {
        this.client.setId(id);
    }

    @Override
    public void updateHeadphone(int id) {
        this.headphones.setId(id);
    }


    public static class Builder {
        private Order newOrder;

        public Builder() {
            newOrder = new Order();
        }

        public Builder id(int id) {
            newOrder.id = id;
            return this;
        }

        public Builder headphones(Headphones headphones) {
            newOrder.headphones = headphones;
            return this;
        }

        public Builder client(Client client) {
            newOrder.client = client;
            return this;
        }

        public Builder status(String status) {
            newOrder.status = status;
            return this;
        }

        public Order build(){
            return newOrder;
        }
    }

}
