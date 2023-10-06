package com.api.casm.Service.ServiceImpl;

import com.api.casm.Exceptions.CategoryNotFoundException;
import com.api.casm.Exceptions.EmptyListException;
import com.api.casm.Model.Category;
import com.api.casm.Model.Product;
import com.api.casm.Repository.CategoryRepository;
import com.api.casm.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }

    @Override
    public List<Category> getCategories() {
        List<Category> allCategories = categoryRepository.findAll();
        if (allCategories.isEmpty()){
            throw new EmptyListException("Category list is empty");
        }
        return categoryRepository.findAll();
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        categoryRepository.deleteById(id);
    }
}
