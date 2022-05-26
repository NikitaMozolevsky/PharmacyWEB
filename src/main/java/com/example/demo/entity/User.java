package com.example.demo.entity;

public class User extends AbstractEntity {

    private int userId;
    private String userName;
    private String login;
    private String password;
    private String email;
    private String phone;
    private AccessLevel accessLevel;
    private double moneyAmount;

    public User(int userId, String userName, String login, String password,
                String email, String phone, AccessLevel accessLevel, double moneyAmount) {
        this.userId = userId;
        this.userName = userName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.accessLevel = accessLevel;
        this.moneyAmount = moneyAmount;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public double getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(double moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (Double.compare(user.moneyAmount, moneyAmount) != 0) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        return accessLevel == user.accessLevel;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = userId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (accessLevel != null ? accessLevel.hashCode() : 0);
        temp = Double.doubleToLongBits(moneyAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(userId);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone=").append(phone);
        sb.append(", accessLevel=").append(accessLevel);
        sb.append(", moneyAmount=").append(moneyAmount);
        sb.append('}');
        return sb.toString();
    }

    // TODO: 18.04.2022 add attributes, getter, setter ... , change DB, (elegant builder)
}
