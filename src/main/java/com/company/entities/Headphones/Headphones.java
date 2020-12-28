package com.company.entities.Headphones;


import com.company.entities.Observable;
import com.company.entities.ObserverOrder;

import java.util.LinkedList;
import java.util.List;


public class Headphones implements Comparable<Headphones>, Observable {
    private List<ObserverOrder> observers =new LinkedList<>();
    private int cost;
    private int max_volume;
    private String name;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Headphones{" +
                "id=" + id +
                ", cost=" + cost +
                ", max_volume=" + max_volume +
                ", name='" + name + '\'' +
                '}';
    }

    public Headphones(int i, int i1) {
        this.cost=i;
        this.max_volume=i1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toSound(){
        return "default sound";
    }

    public Headphones() {
        this.cost=0;
        this.max_volume =0;
        this.name="";
    }

    public Headphones(int cost, int max_volume, String name, int id) {
        this.cost = cost;
        this.max_volume = max_volume;
        this.name = name;
        this.id = id;
    }

    public Headphones(int cost, int max_volume, String name) {
        this.id = 0;
        this.cost = cost;
        this.max_volume = max_volume;
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getMax_volume() {
        return max_volume;
    }

    public void setMax_volume(int max_volume) {
        this.max_volume = max_volume;
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
            observer.updateHeadphone(id);
    }

    public static class Builder {
        private Headphones newHeadphones;

        public Builder() {
            newHeadphones = new Headphones();
        }

        public Headphones.Builder id(int id) {
            newHeadphones.id = id;
            return this;
        }
        public Headphones.Builder cost(int cost) {
            newHeadphones.cost = cost;
            return this;
        }
        public Headphones.Builder max_volume(int max_volume) {
            newHeadphones.max_volume = max_volume;
            return this;
        }
        public Headphones.Builder name(String name) {
            newHeadphones.name = name;
            return this;
        }
        public Headphones build(){
            return newHeadphones;
        }
    }

    @Override
    public int compareTo(Headphones headphones) {
        if(this.equals(headphones)){
            return 0;
        }
        else {
            return (this.cost > headphones.getCost())? 1 : -1;
        }
    }
}
