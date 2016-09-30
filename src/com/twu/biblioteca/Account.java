package com.twu.biblioteca;

public class Account {
    private final String name;
    private final String password;
    private final String email;
    private final String phone;
    private final boolean admin;
    private Object username;

    public Account(String name, String password, String email, String phone, boolean admin) {

        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isAdmin() {
        return admin;
    }
}
