package com.api.casm.Service.ServiceImpl;

import com.api.casm.Exceptions.EmptyListException;
import com.api.casm.Exceptions.ProductNotFoundException;
import com.api.casm.Model.Product;
import com.api.casm.Repository.ProductRepository;
import com.api.casm.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProducts() {
        List<Product> allProducts = productRepository.findAll();
        if (allProducts.isEmpty()){
            throw new EmptyListException("Product list is empty");
        }
        return allProducts;
    }
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    @Override
    public Product updateProduct(Long id, Product product) {
        product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return productRepository.save(product);
    }
    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        productRepository.deleteById(id);
    }
}
