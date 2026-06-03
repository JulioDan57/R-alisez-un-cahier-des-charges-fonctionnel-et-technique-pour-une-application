package com.livrai.bean;

public class User {
    private int id;
    private String email;
    private String name;
    private String password;
    private boolean isAdmin;

    public User() {
    }

    public User(int id, String email, String name, String password, boolean isAdmin) {
        super();
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}