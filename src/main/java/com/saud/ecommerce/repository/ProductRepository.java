
package com.saud.ecommerce.repository;

import com.saud.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String name);

    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    List<Product> findByStockGreaterThanEqual(int stock);
}
