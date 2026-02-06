package com.kevcaballero.tech_test_supermarket.service;

import com.kevcaballero.tech_test_supermarket.dto.VentaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService implements VentaImp{
    @Override
    public List<VentaDTO> getVentas() {
        return List.of();
    }

    @Override
    public VentaDTO createVenta(VentaDTO ventaDto) {
        return null;
    }

    @Override
    public VentaDTO updateVenta(Long id, VentaDTO ventaDto) {
        return null;
    }

    @Override
    public void deleteVenta(Long id) {

    }
}
