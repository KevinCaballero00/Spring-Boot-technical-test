package com.kevcaballero.tech_test_supermarket.repository;

import com.kevcaballero.tech_test_supermarket.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SucursalRepository extends JpaRepository <Sucursal, Long> {
}
