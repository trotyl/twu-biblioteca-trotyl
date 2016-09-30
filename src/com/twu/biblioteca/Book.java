package com.twu.biblioteca;

public class Book {

    private final String title;
    private final String author;
    private final int yearPublished;

    public Book(String title, String author, int yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d", title, author, yearPublished);
    }
}
