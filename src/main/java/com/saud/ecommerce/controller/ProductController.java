package com.saud.ecommerce.controller;

import com.saud.ecommerce.model.Product;
import com.saud.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(product));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/{id}/stock")
    public Product updateProductStock(@PathVariable Long id, @RequestBody int newStock) {
        return productService.updateProductStock(id, newStock);
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String name) {
        return productService.searchProductsByName(name);
    }

    @GetMapping("/filter/price")
    public List<Product> filterProductsByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return productService.filterProductsByPriceRange(minPrice, maxPrice);
    }

    @GetMapping("/filter/stock")
    public List<Product> filterProductsByStock(@RequestParam int minStock) {
        return productService.filterProductsByStock(minStock);
    }
}
