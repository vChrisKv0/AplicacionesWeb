package com.example.conexionbd.reportes.control;

import com.example.conexionbd.reportes.model.ReporteDTO;
import com.example.conexionbd.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reporte")
public class ReporteController {

    private final ReporteService reporteService;

    @Autowired
    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAllReportes() {
        return reporteService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<Message> saveReporte(@Validated(ReporteDTO.Register.class) @RequestBody ReporteDTO dto) {
        return reporteService.save(dto);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> updateReporte(@Validated(ReporteDTO.Register.class) @RequestBody ReporteDTO dto) {
        return reporteService.update(dto);
    }
}
