package com.kevcaballero.tech_test_supermarket.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleVentaDTO {

    private Long id;
    private String nombreProd;
    private Integer cantProd;
    private Double precio;
    private Double subtotal;
}
