package com.example.lab8_h792_20251_20220270.controller;

import com.example.lab8_h792_20251_20220270.dto.ClimaActualDto;
import com.example.lab8_h792_20251_20220270.dto.PronosticoHoraDto;
import com.example.lab8_h792_20251_20220270.entity.MonitoreoClimatico;
import com.example.lab8_h792_20251_20220270.repository.MonitoreoClimaticoRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/clima")
public class MonitoreoClimaticoController {

    @Autowired
    MonitoreoClimaticoRepository monitoreoClimaticoRepository;

    @GetMapping("/actual")
    public ResponseEntity<ClimaActualDto> obtenerClimaActual(@RequestParam String ciudad) {
        try {
            String url = "https://api.weatherapi.com/v1/current.json?key=88e12060abad41ab97212738250906&q=" + ciudad;
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            JsonNode current = root.get("current");
            JsonNode condition = current.get("condition");

            ClimaActualDto dto = new ClimaActualDto();
            dto.setTemperaturaActual(current.get("temp_c").asDouble());
            dto.setCondicionClima(condition.get("text").asText());
            dto.setSensacionTermica(current.get("feelslike_c").asDouble());
            dto.setHumedad(current.get("humidity").asInt());

            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/pronostico-horas")
    public ResponseEntity<List<PronosticoHoraDto>> obtenerPronosticoHoras(@RequestParam String ciudad) {
        try {
            String url = "https://api.weatherapi.com/v1/forecast.json?key=88e12060abad41ab97212738250906&q=" + ciudad + "&days=1";
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            JsonNode forecastday = root.path("forecast").path("forecastday");
            if (forecastday.isArray() && forecastday.size() > 0) {
                JsonNode today = forecastday.get(0);
                JsonNode hours = today.path("hour");
                List<PronosticoHoraDto> lista = new ArrayList<>();
                for (JsonNode hour : hours) {
                    PronosticoHoraDto dto = new PronosticoHoraDto();
                    dto.setHora(hour.get("time").asText());
                    dto.setTemperatura(hour.get("temp_c").asDouble());
                    dto.setCondicionClima(hour.get("condition").get("text").asText());
                    lista.add(dto);
                }
                return ResponseEntity.ok(lista);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/monitoreo")
    public ResponseEntity<MonitoreoClimatico> registrarMonitoreo(@RequestBody Map<String, Object> datos) {
        try {
            String ciudad = (String) datos.get("ciudad");
            LocalDate fecha = LocalDate.parse((String) datos.get("fecha"));
            Double tempPromedio = Double.valueOf(datos.get("temperaturaPromedio").toString());
            String condicionFrecuente = (String) datos.get("condicionFrecuente");
            Double tempMax = Double.valueOf(datos.get("temperaturaMaxima").toString());
            Double tempMin = Double.valueOf(datos.get("temperaturaMinima").toString());

            MonitoreoClimatico monitoreo = new MonitoreoClimatico();
            monitoreo.setCiudad(ciudad);
            monitoreo.setFecha(fecha);
            monitoreo.setTemperaturaPromedioDia(tempPromedio);
            monitoreo.setCondicionFrecuente(condicionFrecuente);
            monitoreo.setTemperaturaMax(tempMax);
            monitoreo.setTemperaturaMin(tempMin);

            monitoreoClimaticoRepository.save(monitoreo);
            return ResponseEntity.ok(monitoreo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

