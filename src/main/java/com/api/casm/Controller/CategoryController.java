package com.api.casm.Controller;

import com.api.casm.Model.Category;
import com.api.casm.Service.ServiceImpl.CategoryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryServiceImpl categoryService;
    @Autowired
    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping
    @Operation(summary = "Create a new category")
    @ApiResponse(responseCode = "201", description = "Category created successfully")
    @ApiResponse(responseCode = "400", description = "Request error")
    public ResponseEntity<Category> newCategory(@Valid @RequestBody Category category) {
        Category newCategory = categoryService.saveCategory(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a category by ID")
    @ApiResponse(responseCode = "200", description = "Category found successfully")
    @ApiResponse(responseCode = "404", description = "Category not found")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        Category categoryId = categoryService.getCategory(id);
        return new ResponseEntity<>(categoryId, HttpStatus.OK);
    }
    @GetMapping("/name/{name}")
    @Operation(summary = "Get a category by ID")
    @ApiResponse(responseCode = "200", description = "Category found successfully")
    @ApiResponse(responseCode = "404", description = "Category not found")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name) {
        Category categoryId = categoryService.getCategoryByName(name);
        return new ResponseEntity<>(categoryId, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get a list of categories")
    @ApiResponse(responseCode = "200", description = "List of categories found correctly")
    @ApiResponse(responseCode = "404", description = "List of categories not found")
    public ResponseEntity<List<Category>> listCategories() {
        List<Category> categoriesList = categoryService.getCategories();
        return new ResponseEntity<>(categoriesList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category by ID")
    @ApiResponse(responseCode = "200", description = "Category updated successfully")
    @ApiResponse(responseCode = "400", description = "Request error")
    @ApiResponse(responseCode = "404", description = "Category not found")
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category, @PathVariable Long id) {
        Category updatedCategory = categoryService.updateCategory(id, category);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category by ID")
    @ApiResponse(responseCode = "200", description = "Category deleted successfully")
    @ApiResponse(responseCode = "404", description = "Category not found")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>("Successful category removal", HttpStatus.OK);
    }
}
