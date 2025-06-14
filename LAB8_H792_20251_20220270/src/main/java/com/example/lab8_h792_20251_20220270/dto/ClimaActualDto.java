package com.example.lab8_h792_20251_20220270.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClimaActualDto {
    private double temperaturaActual;
    private String condicionClima;
    private double sensacionTermica;
    private int humedad;
}
