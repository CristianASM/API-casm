package com.api.casm.Controller;

import com.api.casm.Model.Category;
import com.api.casm.Model.Product;
import com.api.casm.Service.ServiceImpl.ProductServiceImpl;
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
    public ResponseEntity<Product> newProduct(@Valid @RequestBody Product product){
        Product newProduct = productService.saveProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        Product productId = productService.getProduct(id);
        return new ResponseEntity<>(productId, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Product>> listProducts(){
        List<Product> productsList = productService.getProducts();
        return new ResponseEntity<>(productsList, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product, Long id){
        Product updatedProduct = productService.updateProduct(id, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("Successful product removal",HttpStatus.OK);
    }
}
