package com.kevcaballero.tech_test_supermarket.service;

import com.kevcaballero.tech_test_supermarket.dto.SucursalDTO;
import com.kevcaballero.tech_test_supermarket.exception.NotFoundException;
import com.kevcaballero.tech_test_supermarket.mapper.Mapper;
import com.kevcaballero.tech_test_supermarket.model.Sucursal;
import com.kevcaballero.tech_test_supermarket.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService implements SucursalImp{

    @Autowired
    private SucursalRepository repo;

    @Override
    public List<SucursalDTO> getSucursales() {
        return repo.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public SucursalDTO createSucursal(SucursalDTO sucursalDto) {
        Sucursal suc = Sucursal.builder()
                .nombre(sucursalDto.getNombre())
                .direccion(sucursalDto.getDireccion())
                .build();

        return Mapper.toDTO(repo.save(suc));
    }

    @Override
    public SucursalDTO updateSucursal(Long id, SucursalDTO sucursalDto) {
        Sucursal suc = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Sucursal no encontrada"));

        suc.setNombre(sucursalDto.getNombre());
        suc.setDireccion(sucursalDto.getDireccion());

        return Mapper.toDTO(repo.save(suc));
    }

    @Override
    public void deleteSucursal(Long id) {

        if (!repo.existsById(id)) {
            throw new NotFoundException("Sucursal no encontrada");
        }
        repo.deleteById(id);

    }
}
