package com.api.casm.Controller;

import com.api.casm.Model.Product;
import com.api.casm.Service.ServiceImpl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductServiceImpl productService;
    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }
    @PostMapping
    @Operation(summary = "Create a new product")
    @ApiResponse(responseCode = "201", description = "Product created successfully")
    @ApiResponse(responseCode = "400", description = "Request error")
    public ResponseEntity<Product> newProduct(@Valid @RequestBody Product product){
        Product newProduct = productService.saveProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }
    @GetMapping("/name/{name}")
    @Operation(summary = "Get a product by ID")
    @ApiResponse(responseCode = "200", description = "Product found successfully")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<Product> getProductByName(@PathVariable String name){
        Product productByName = productService.getProductByName(name);
        return new ResponseEntity<>(productByName, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a product by ID")
    @ApiResponse(responseCode = "200", description = "Product found successfully")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        Product productId = productService.getProduct(id);
        return new ResponseEntity<>(productId, HttpStatus.OK);
    }
    @GetMapping
    @Operation(summary = "Get a list of products")
    @ApiResponse(responseCode = "200", description = "List of products found correctly")
    @ApiResponse(responseCode = "404", description = "List of products not found")
    public ResponseEntity<List<Product>> listProducts(){
        List<Product> productsList = productService.getProducts();
        return new ResponseEntity<>(productsList, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a product by ID")
    @ApiResponse(responseCode = "200", description = "Product updated successfully")
    @ApiResponse(responseCode = "400", description = "Request error")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product, Long id){
        Product updatedProduct = productService.updateProduct(id, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product by ID")
    @ApiResponse(responseCode = "200", description = "Product deleted successfully")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("Successful product removal",HttpStatus.OK);
    }
}
