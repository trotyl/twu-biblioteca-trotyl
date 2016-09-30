package com.twu.biblioteca;

public class Account {
    private final String name;
    private final String password;
    private final boolean admin;
    private Object username;

    public Account(String name, String password, boolean admin) {

        this.name = name;
        this.password = password;
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
