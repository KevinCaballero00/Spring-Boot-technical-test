package com.kevcaballero.tech_test_supermarket.service;

import com.kevcaballero.tech_test_supermarket.dto.SucursalDTO;

import java.util.List;

public interface SucursalImp {

    List<SucursalDTO> getSucursales();

    SucursalDTO createSucursal(SucursalDTO sucursalDto);

    SucursalDTO updateSucursal(Long id, SucursalDTO sucursalDto);

    void deleteSucursal(Long id);
}
