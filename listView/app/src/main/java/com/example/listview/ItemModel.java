package com.example.listview;

public class ItemModel {
    private String title;
    private String description;

    public ItemModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}

