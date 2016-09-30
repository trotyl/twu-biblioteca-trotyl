package com.twu.biblioteca;

public class Book extends Item {

    private final String author;
    private final int yearPublished;

    public Book(String id, String title, String author, int yearPublished) {
        super(id, title);
        this.author = author;
        this.yearPublished = yearPublished;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %d", getId(), getTitle(), author, yearPublished);
    }

}
