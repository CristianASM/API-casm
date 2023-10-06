package com.api.casm.Service;

import com.api.casm.Model.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(Long id);
    List<Product> getProducts();
    Product saveProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
