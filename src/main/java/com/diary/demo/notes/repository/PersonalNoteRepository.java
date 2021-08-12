package com.diary.demo.notes.repository;

import com.diary.demo.notes.models.PersonalNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalNoteRepository extends JpaRepository<PersonalNote, Long> {

    // Get user wise all personal note list
    List<PersonalNote> findAllByUserId(long userId);
}
