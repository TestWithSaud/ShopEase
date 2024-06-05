package com.saud.ecommerce.service;

import com.saud.ecommerce.exception.ProductNotFoundException;
import com.saud.ecommerce.model.Product;
import com.saud.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    // Add a logger instance

    // Use the logger in methods
    public Product getProductById(Long id) {
        logger.info("Fetching product with id: {}", id);
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProductStock(Long id, int newStock) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setStock(newStock);
            return productRepository.save(product);
        }
        return null;
    }

    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContaining(name);
    }

    public List<Product> filterProductsByPriceRange(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> filterProductsByStock(int minStock) {
        return productRepository.findByStockGreaterThanEqual(minStock);
    }

}
