package com.example.lab8_h792_20251_20220270.repository;

import com.example.lab8_h792_20251_20220270.entity.MonitoreoClimatico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoreoClimaticoRepository extends JpaRepository<MonitoreoClimatico, Integer> {
}
