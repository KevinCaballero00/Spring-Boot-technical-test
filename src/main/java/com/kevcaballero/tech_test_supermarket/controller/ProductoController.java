package com.kevcaballero.tech_test_supermarket.controller;

import com.kevcaballero.tech_test_supermarket.dto.ProductoDTO;
import com.kevcaballero.tech_test_supermarket.service.ProductoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoImp productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> getProductos() {
        return ResponseEntity.ok(productoService.getProductos());
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO dto) {
        ProductoDTO creado = productoService.createProducto(dto);

        return ResponseEntity.created(URI.create("/api/productos/" + creado.getId())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO dto) {
        return ResponseEntity.ok(productoService.updateProducto(id, dto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
}
