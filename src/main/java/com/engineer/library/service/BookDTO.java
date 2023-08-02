package com.engineer.library.service;

public class BookDTO {
    private int id;
    private String name;
    private String author;
    private String imageS3Id;
    private boolean visible;

    public BookDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageS3Id() {
        return imageS3Id;
    }

    public void setImageS3Id(String imageS3Id) {
        this.imageS3Id = imageS3Id;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}