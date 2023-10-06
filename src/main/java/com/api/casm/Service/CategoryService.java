package com.api.casm.Service;

import com.api.casm.Model.Category;
import com.api.casm.Model.Product;

import java.util.List;

public interface CategoryService {
    Category getCategory(Long id);
    List<Category> getCategories();
    Category saveCategory(Category category);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
}
