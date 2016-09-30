package com.twu.biblioteca;

public class Item {
    private final String id;
    private final String title;
    private boolean available = true;

    public Item(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void checkout() {
        this.available = false;
    }

    public void doReturn() {
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return String.format("%s %s", getId(), getTitle());
    }
}
