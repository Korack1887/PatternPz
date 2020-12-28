package com.company.entities.Clients;


import com.company.entities.Caretaker;
import com.company.entities.Memento;
import com.company.entities.Observable;
import com.company.entities.ObserverOrder;

import java.util.LinkedList;
import java.util.List;

public class Client implements Observable {
    private List<ObserverOrder> observers =new LinkedList<>();
    private int id;
    String first_name;
    String last_name;
    String email;

    public Client() {
        this.id=0;
        this.first_name="";
        this.last_name="";
        this.email="";
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyObservers();
    }


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Client(int id, String first_name, String last_name, String email) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    @Override
    public void registerObserver(ObserverOrder o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(ObserverOrder o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (ObserverOrder observer : observers)
            observer.updateClient(id);
    }
    public Memento saveState(Caretaker caretaker) {
        if(caretaker.getMemento()==null){
           caretaker.setMemento(new Memento(this));
           return caretaker.getMemento();
        }
            caretaker.getMemento().addState(this);
            return caretaker.getMemento();
    }

    public void restoreState(Memento memento) {
        Client temp = new Client();
        temp = memento.getState();
        this.id = temp.getId();
        this.first_name = temp.getFirst_name();
        this.last_name = temp.getLast_name();
        this.email = temp.getEmail();
    }

    public static class Builder {
        private Client newClient;

        public Builder() {
            newClient = new Client();
        }

        public Client.Builder id(int id) {
            newClient.id = id;
            return this;
        }
        public Client.Builder first_name(String f_name) {
            newClient.first_name = f_name;
            return this;
        }
        public Client.Builder last_name(String l_name) {
            newClient.last_name = l_name;
            return this;
        }
        public Client.Builder email(String email) {
            newClient.email = email;
            return this;
        }
        public Client build(){
            return newClient;
        }

    }
}
