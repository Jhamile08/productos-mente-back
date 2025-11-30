package com.mente.productosMente.repository;

import com.mente.productosMente.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Significa acceso de datos
public interface ProductRepository extends JpaRepository<Product, Long> {
}
