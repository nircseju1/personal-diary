package com.diary.demo.notes.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryModel {
    private long id;
    private String name;
    private String description;
    private long userId;
    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;

    public long getId() {
        return this.id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}

