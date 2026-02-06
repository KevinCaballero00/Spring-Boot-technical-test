package com.kevcaballero.tech_test_supermarket.repository;

import com.kevcaballero.tech_test_supermarket.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta,Long> {
}
