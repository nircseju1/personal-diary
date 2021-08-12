package com.diary.demo.notes.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PersonalNoteModel {
    private long id;
    private long categoryId;            // id -> Category
    private String categoryName;        // name -> Category
    private String title;
    private String content;
    private long userId;
    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;
    private String lastUpdatedAsString;

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
