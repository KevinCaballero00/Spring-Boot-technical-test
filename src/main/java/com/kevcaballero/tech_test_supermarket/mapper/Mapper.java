package com.kevcaballero.tech_test_supermarket.mapper;

import com.kevcaballero.tech_test_supermarket.dto.DetalleVentaDTO;
import com.kevcaballero.tech_test_supermarket.dto.ProductoDTO;
import com.kevcaballero.tech_test_supermarket.dto.SucursalDTO;
import com.kevcaballero.tech_test_supermarket.dto.VentaDTO;
import com.kevcaballero.tech_test_supermarket.model.DetalleVenta;
import com.kevcaballero.tech_test_supermarket.model.Producto;
import com.kevcaballero.tech_test_supermarket.model.Sucursal;
import com.kevcaballero.tech_test_supermarket.model.Venta;

import java.util.stream.Collectors;

public class Mapper {

    //Mapeo de producto a ProductoDTO
    public static ProductoDTO toDTO(Producto p){
        if(p == null) return null;

        return ProductoDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .precio(p.getPrecio())
                .build();
    }

    //Mapeo de venta a VentaDTO
    public static VentaDTO toDTO(Venta venta){
        if(venta == null) return null;

        var detalle = venta.getDetalle().stream().map(det ->
                DetalleVentaDTO.builder()
                        .id(det.getProd().getId())
                        .nombreProd(det.getProd().getNombre())
                        .cantProd(det.getCantidadProd())
                        .precio(det.getPrecio())
                        .subtotal(det.getPrecio() * det.getCantidadProd())
                        .build()
        ).collect(Collectors.toList());

        var total = detalle.stream()
                .map(DetalleVentaDTO::getSubtotal)
                .reduce(0.0, Double::sum);

        return VentaDTO.builder()
                .id(venta.getId())
                .fecha(venta.getFecha())
                .estado(venta.getEstado())
                .idSucursal(venta.getSucursal().getId())
                .detalle(detalle)
                .total(total)
                .build();
    }

    //Mapeo de sucursal a SucursalDTO
    public static SucursalDTO toDTO(Sucursal s){
        if(s == null) return null;

        return SucursalDTO.builder()
                .id(s.getId())
                .nombre(s.getNombre())
                .direccion(s.getDireccion())
                .build();
    }
}
