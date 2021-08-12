package com.diary.demo.notes.controllers;

import com.diary.demo.notes.models.Category;
import com.diary.demo.notes.models.CategoryModel;
import com.diary.demo.notes.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@Slf4j
@RequiredArgsConstructor
public class CategoryApi {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(categoryService.findAllByUser());
    }

    @PostMapping
    public ResponseEntity<CategoryModel> create(@RequestBody CategoryModel categoryModel) {
        return ResponseEntity.ok(categoryService.save(categoryModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryModel> update(@PathVariable Long id, @Valid @RequestBody CategoryModel categoryModel) {
        if (!categoryService.findById(id).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(categoryService.update(categoryModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!categoryService.findById(id).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        categoryService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
