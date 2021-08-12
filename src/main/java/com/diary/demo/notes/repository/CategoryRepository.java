package com.diary.demo.notes.repository;

import com.diary.demo.notes.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Get user wise all category list
    List<Category> findAllByUserId(long userId);

}
