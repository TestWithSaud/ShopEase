package com.saud.ecommerce.service;

import com.saud.ecommerce.model.Product;
import com.saud.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts() {
        Product product1 = new Product(1L, "Product1", "Description1", 100.0, 10);
        Product product2 = new Product(2L, "Product2", "Description2", 200.0, 20);
        List<Product> products = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(2, result.size());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getProductById() {
        Product product = new Product(1L, "Product1", "Description1", 100.0, 10);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(1L);
        assertEquals("Product1", result.getName());
    }

    @Test
    void saveProduct() {
        Product product = new Product(1L, "Product1", "Description1", 100.0, 10);
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.saveProduct(product);
        assertEquals("Product1", result.getName());
    }

    @Test
    void deleteProduct() {
        doNothing().when(productRepository).deleteById(1L);

        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateProductStock() {
        Product product = new Product(1L, "Product1", "Description1", 100.0, 10);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.updateProductStock(1L, 20);
        assertNotNull(result);
        assertEquals(20, result.getStock());
    }
}
