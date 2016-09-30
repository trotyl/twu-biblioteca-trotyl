package com.twu.biblioteca;

public class Book {

    private final String id;
    private final String title;
    private final String author;
    private final int yearPublished;
    private boolean available = true;

    public Book(String id, String title, String author, int yearPublished) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
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

    @Override
    public String toString() {
        return String.format("%s %s %s %d", id, title, author, yearPublished);
    }

    public String getTitle() {
        return title;
    }

    public void doReturn() {

    }
}
