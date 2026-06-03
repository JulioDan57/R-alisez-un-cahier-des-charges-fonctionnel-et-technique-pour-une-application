package com.livrai.bean;

public class Delivery {

    public static final String PENDING = "En attente";
    public static final String ACCEPTED = "Acceptée";
    public static final String REJECTED = "Refusée";
    public static final String FINISHED = "Terminée";

    private int id;
    private int userId;
    private int volume;
    private int weight;
    private double price;
    private String status;

    private String clientName;

    public Delivery() {
    }

    public Delivery(int id, int userId, int volume, int weight, double price, String status) {
        super();
        this.id = id;
        this.userId = userId;
        this.volume = volume;
        this.weight = weight;
        this.price = price;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
