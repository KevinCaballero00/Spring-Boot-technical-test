package com.kevcaballero.tech_test_supermarket.service;

import com.kevcaballero.tech_test_supermarket.dto.ProductoDTO;
import com.kevcaballero.tech_test_supermarket.exception.NotFoundException;
import com.kevcaballero.tech_test_supermarket.mapper.Mapper;
import com.kevcaballero.tech_test_supermarket.model.Producto;
import com.kevcaballero.tech_test_supermarket.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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
        Producto prod = Producto.builder()
                .nombre(productoDto.getNombre())
                .categoria(productoDto.getCategoria())
                .precio(productoDto.getPrecio())
                .cantidad(productoDto.getCantidad())
                .build();

        return Mapper.toDTO(repoProducto.save(prod));
    }

    @Override
    public ProductoDTO updateProducto(Long id, ProductoDTO productoDto) {
        Producto prod = repoProducto.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con id: " + id));

            prod.setNombre(productoDto.getNombre());
            prod.setCategoria(productoDto.getCategoria());
            prod.setPrecio(productoDto.getPrecio());
            prod.setCantidad(productoDto.getCantidad());

        return Mapper.toDTO(repoProducto.save(prod));
    }

    @Override
    public void deleteProducto(Long id) {
        if(!repoProducto.existsById(id)) {
            throw new NotFoundException("Producto no encontrado con id: " + id);
        }
        repoProducto.deleteById(id);

    }
}
