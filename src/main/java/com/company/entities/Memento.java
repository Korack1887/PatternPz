package com.company.entities;

import com.company.entities.Clients.Client;

import java.util.LinkedList;

public class Memento {
    LinkedList<Client> states=new LinkedList<>();

    public Memento(Client client) {
        Client temp = new Client();
        temp.setId(client.getId());
        temp.setFirst_name(client.getFirst_name());
        temp.setLast_name(client.getLast_name());
        temp.setEmail(client.getEmail());
        states.add(temp);
    }
    public void addState(Client client){
        Client temp = new Client();
        temp.setId(client.getId());
        temp.setFirst_name(client.getFirst_name());
        temp.setLast_name(client.getLast_name());
        temp.setEmail(client.getEmail());
        states.add(temp);
    }

    public Client getState() {
        if(states.size()>1) {
            Client client = states.getLast();
            states.removeLast();
            return client;
        }
        if (states.size()==1) {
            return states.getLast();
        }
        return new Client();
    }
}
