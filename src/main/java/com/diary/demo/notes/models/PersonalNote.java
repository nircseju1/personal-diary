package com.diary.demo.notes.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
public class PersonalNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private long categoryId;            // id -> Category

    @NotNull
    @Size(min = 4, max = 255)
    private String title;

    @Size(min = 4)
    private String content;

    private long userId;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @CreationTimestamp
    private LocalDateTime lastUpdated;

    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }
}
