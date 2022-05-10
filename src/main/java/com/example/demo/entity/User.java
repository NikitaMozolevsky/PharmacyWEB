package com.example.demo.entity;

public class User extends AbstractEntity {

    private int user_id;
    private String user_name;
    private String login;
    private String password;
    private String email;
    private int phone;
    private AccessLevel accessLevel;
    private double money_amount;

    public User(int user_id, String user_name, String login, String password,
                String email, int phone, AccessLevel accessLevel, double money_amount) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.accessLevel = accessLevel;
        this.money_amount = money_amount;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public double getMoney_amount() {
        return money_amount;
    }

    public void setMoney_amount(double money_amount) {
        this.money_amount = money_amount;
    }
// TODO: 18.04.2022 add attributes, getter, setter ... , change DB, (elegant builder)
}
