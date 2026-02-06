package com.kevcaballero.tech_test_supermarket.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SucursalDTO {

    private Long id;
    private String nombre;
    private String direccion;
}
