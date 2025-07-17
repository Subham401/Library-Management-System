package com.example.library_management_system;

public class Book {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private int availableCopies;

    public Book(int id, String title, String author, String publisher, int availableCopies){
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.availableCopies = availableCopies;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }
}
