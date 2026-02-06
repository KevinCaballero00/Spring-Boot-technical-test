package com.kevcaballero.tech_test_supermarket.repository;

import com.kevcaballero.tech_test_supermarket.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
