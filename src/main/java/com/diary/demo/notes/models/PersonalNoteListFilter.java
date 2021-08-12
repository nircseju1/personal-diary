package com.diary.demo.notes.models;

import lombok.Data;

@Data
public class PersonalNoteListFilter {
    private long scCategoryId;
    private String scCategoryName;
    private String scTitle;
    private String scContent;
    private String scNoteUpdatedDate;

    public long getScCategoryId() {
        return this.scCategoryId;
    }

    public String getScCategoryName() {
        return this.scCategoryName;
    }

    public String getScTitle() {
        return this.scTitle;
    }

    public String getScContent() {
        return this.scContent;
    }

    public String getScNoteUpdatedDate() {
        return this.scNoteUpdatedDate;
    }
}
