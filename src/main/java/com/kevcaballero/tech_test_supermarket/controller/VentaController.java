package com.kevcaballero.tech_test_supermarket.controller;

import com.kevcaballero.tech_test_supermarket.dto.VentaDTO;
import com.kevcaballero.tech_test_supermarket.service.VentaImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaImp ventaService;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> getVentas() {
        return ResponseEntity.ok(ventaService.getVentas());
    }

    @PostMapping
    public ResponseEntity<VentaDTO> crearVenta(@RequestBody VentaDTO dto) {
        VentaDTO creado = ventaService.createVenta(dto);

        return ResponseEntity.created(URI.create("/api/ventas/" + creado.getId())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> actualizarVenta(@PathVariable Long id, @RequestBody VentaDTO dto) {
        return ResponseEntity.ok(ventaService.updateVenta(id, dto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id){
        ventaService.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }
}
