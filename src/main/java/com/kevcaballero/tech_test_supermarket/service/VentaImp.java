package com.kevcaballero.tech_test_supermarket.service;

import com.kevcaballero.tech_test_supermarket.dto.VentaDTO;

import java.util.List;

public interface VentaImp {

    List<VentaDTO> getVentas();
    VentaDTO createVenta(VentaDTO ventaDto);
    VentaDTO updateVenta(Long id, VentaDTO ventaDto);
    void deleteVenta(Long id);
}
