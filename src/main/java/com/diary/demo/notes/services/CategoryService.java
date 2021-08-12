package com.diary.demo.notes.services;

import com.diary.demo.authentication.service.UserPrinciple;
import com.diary.demo.notes.models.Category;
import com.diary.demo.notes.models.CategoryModel;
import com.diary.demo.notes.repository.CategoryRepository;
import com.diary.demo.util.DateUtils;
import com.diary.demo.util.UserUtil;
import com.diary.demo.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    CategoryRepository categoryRepository;

    // Get user wise all category list
    public List<Category> findAllByUser() {
        UserPrinciple userPrinciple = UserUtil.getUser();
        return categoryRepository.findAllByUserId(userPrinciple.getId());
    }

    // Get category info by category id
    public Optional<Category> findById(Long categoryId){
        return categoryRepository.findById(categoryId);
    }

    // Add category info by category details info
    public CategoryModel save(CategoryModel categoryModel) {
        UserPrinciple userPrinciple = UserUtil.getUser();

        categoryModel.setUserId(userPrinciple.getId());
        categoryModel.setDateCreated(DateUtils.getCurrentDateTime());

        Category category = Util.toEntity(categoryModel, Category.class);
        categoryRepository.saveAndFlush(category);

        return categoryModel;
    }

    // Update category info by category details info
    public CategoryModel update(CategoryModel categoryModel) {
        UserPrinciple userPrinciple = UserUtil.getUser();

        Category existingCategory = categoryRepository.getById(categoryModel.getId());

        categoryModel.setDateCreated(existingCategory.getDateCreated());
        categoryModel.setUserId(userPrinciple.getId());
        categoryModel.setLastUpdated(DateUtils.getCurrentDateTime());

        Category category = Util.toEntity(categoryModel, Category.class);
        categoryRepository.saveAndFlush(category);

        return categoryModel;
    }

    // Delete category info by using category id
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
