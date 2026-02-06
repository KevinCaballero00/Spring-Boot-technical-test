package com.kevcaballero.tech_test_supermarket.service;

import com.kevcaballero.tech_test_supermarket.dto.ProductoDTO;
import com.kevcaballero.tech_test_supermarket.mapper.Mapper;
import com.kevcaballero.tech_test_supermarket.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements ProductoImp{

    @Autowired
    private ProductoRepository repoProducto;

    @Override
    public List<ProductoDTO> getProductos() {
        return repoProducto.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDTO createProducto(ProductoDTO productoDto) {
        return null;
    }

    @Override
    public ProductoDTO updateProducto(Long id, ProductoDTO productoDto) {
        return null;
    }

    @Override
    public void deleteProducto(Long id) {

    }
}
