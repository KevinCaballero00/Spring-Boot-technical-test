package com.kevcaballero.tech_test_supermarket.service;

import com.kevcaballero.tech_test_supermarket.dto.DetalleVentaDTO;
import com.kevcaballero.tech_test_supermarket.dto.VentaDTO;
import com.kevcaballero.tech_test_supermarket.exception.NotFoundException;
import com.kevcaballero.tech_test_supermarket.mapper.Mapper;
import com.kevcaballero.tech_test_supermarket.model.DetalleVenta;
import com.kevcaballero.tech_test_supermarket.model.Producto;
import com.kevcaballero.tech_test_supermarket.model.Sucursal;
import com.kevcaballero.tech_test_supermarket.model.Venta;
import com.kevcaballero.tech_test_supermarket.repository.ProductoRepository;
import com.kevcaballero.tech_test_supermarket.repository.SucursalRepository;
import com.kevcaballero.tech_test_supermarket.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements VentaImp {

    @Autowired
    private VentaRepository repoVenta;
    @Autowired
    private ProductoRepository repoProd;
    @Autowired
    private SucursalRepository repoSuc;

    @Override
    public List<VentaDTO> getVentas() {
        List<Venta> ventas = repoVenta.findAll();
        List<VentaDTO> ventasDto = new ArrayList<>();
        VentaDTO dto;

        for (Venta v : ventas) {
            dto = Mapper.toDTO(v);
            ventasDto.add(dto);
        }

        return ventasDto;
    }

    @Override
    public VentaDTO createVenta(VentaDTO ventaDto) {
        //Validaciones
        if (ventaDto == null) throw new RuntimeException("Venta no puede ser nulo.");
        if (ventaDto.getIdSucursal() == null) throw new RuntimeException("Sucursal no puede ser nulo.");
        if (ventaDto.getDetalle() == null || ventaDto.getDetalle().isEmpty())
            throw new RuntimeException("Detalle no puede ser nulo.");

        //Buscar la sucursal
        Sucursal suc = repoSuc.findById(ventaDto.getIdSucursal()).orElse(null);
        if (suc == null) {
            throw new NotFoundException("Sucursal no encontrado.");
        }

        //Crear la venta
        Venta ven = new Venta();
        ven.setFecha(ventaDto.getFecha());
        ven.setEstado(ventaDto.getEstado());
        ven.setSucursal(suc);
        ven.setTotal(ventaDto.getTotal());

        //Lista de detalles
        List<DetalleVenta> detalles = new ArrayList<>();

        for (DetalleVentaDTO detDto : ventaDto.getDetalle()) {
            Producto p = repoProd.findByNombre(detDto.getNombreProd()).orElse(null);
            if (p == null) {
                throw new NotFoundException("Producto no encontrado: " + detDto.getNombreProd());
            }

            //Crear el detalle
            DetalleVenta detVenta = new DetalleVenta();
            detVenta.setProd(p);
            detVenta.setPrecio(detDto.getPrecio());
            detVenta.setCantidadProd(p.getCantidad());
            detVenta.setVenta(ven);

            detalles.add(detVenta);
        }

        //Seteamos la lista
        ven.setDetalle(detalles);

        //Guardamos la venta
        repoVenta.save(ven);

        //Mapeo
        VentaDTO ventaSalida = Mapper.toDTO(ven);

        return ventaSalida;
    }

    @Override
    public VentaDTO updateVenta(Long id, VentaDTO ventaDto) {
        //Buscar si la venta existe
        Venta v = repoVenta.findById(id).orElse(null);
        if (v == null) throw new NotFoundException("Venta no encontrado.");

        if(ventaDto.getFecha()!=null){
            v.setFecha(ventaDto.getFecha());
        }
        if(ventaDto.getEstado()!=null){
            v.setEstado(ventaDto.getEstado());
        }
        if (ventaDto.getTotal()!=null){
            v.setTotal(ventaDto.getTotal());
        }
        if (ventaDto.getIdSucursal()!=null){
            Sucursal suc = repoSuc.findById(ventaDto.getIdSucursal()).orElse(null);
            if (suc == null) throw new NotFoundException("Sucursal no encontrado.");
            v.setSucursal(suc);
        }
        repoVenta.save(v);

        VentaDTO ventaSalida = Mapper.toDTO(v);

        return ventaSalida;
    }

    @Override
    public void deleteVenta(Long id) {

        Venta v = repoVenta.findById(id).orElse(null);
        if (v == null) throw new NotFoundException("Venta no encontrado.");

        repoVenta.delete(v);
    }
}
