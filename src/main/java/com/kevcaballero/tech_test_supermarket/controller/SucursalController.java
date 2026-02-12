package com.kevcaballero.tech_test_supermarket.controller;

import com.kevcaballero.tech_test_supermarket.dto.SucursalDTO;
import com.kevcaballero.tech_test_supermarket.service.SucursalImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    @Autowired
    private SucursalImp sucursalService;

    @GetMapping
    public ResponseEntity<List<SucursalDTO>> getSucursales() {
        return ResponseEntity.ok(sucursalService.getSucursales());
    }

    @PostMapping
    public ResponseEntity<SucursalDTO> crearSucursal(@RequestBody SucursalDTO dto) {
        SucursalDTO creado = sucursalService.createSucursal(dto);

        return ResponseEntity.created(URI.create("/api/sucursales/" + creado.getId())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> actualizarSucursal(@PathVariable Long id, @RequestBody SucursalDTO dto) {
        return ResponseEntity.ok(sucursalService.updateSucursal(id, dto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Long id){
        sucursalService.deleteSucursal(id);
        return ResponseEntity.noContent().build();
    }
}
