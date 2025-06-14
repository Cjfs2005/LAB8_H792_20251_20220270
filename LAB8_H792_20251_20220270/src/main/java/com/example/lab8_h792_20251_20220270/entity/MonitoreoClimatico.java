package com.example.lab8_h792_20251_20220270.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "monitoreo_climatico")
public class MonitoreoClimatico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMonitoreo")
    private Integer idMonitoreo;

    @Column(name = "ciudad", nullable = false, length = 100)
    private String ciudad;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "temperaturaPromedioDia", nullable = false)
    private Double temperaturaPromedioDia;

    @Column(name = "condicionFrecuente", nullable = false, length = 100)
    private String condicionFrecuente;

    @Column(name = "temperaturaMax", nullable = false)
    private Double temperaturaMax;

    @Column(name = "temperaturaMin", nullable = false)
    private Double temperaturaMin;
}