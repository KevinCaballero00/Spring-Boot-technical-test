package com.kevcaballero.tech_test_supermarket.service;

import com.kevcaballero.tech_test_supermarket.dto.SucursalDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService implements SucursalImp{

    @Override
    public List<SucursalDTO> getSucursales() {
        return List.of();
    }

    @Override
    public SucursalDTO createSucursal(SucursalDTO sucursalDto) {
        return null;
    }

    @Override
    public SucursalDTO updateSucursal(Long id, SucursalDTO sucursalDto) {
        return null;
    }

    @Override
    public void deleteSucursal(Long id) {

    }
}
