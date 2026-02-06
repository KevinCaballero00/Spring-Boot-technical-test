package com.kevcaballero.tech_test_supermarket.service;

import com.kevcaballero.tech_test_supermarket.dto.ProductoDTO;

import java.util.List;

public interface ProductoImp {

    List<ProductoDTO> getProductos();
    ProductoDTO createProducto(ProductoDTO productoDto);
    ProductoDTO updateProducto(Long id, ProductoDTO productoDto);
    void deleteProducto(Long id);
}
