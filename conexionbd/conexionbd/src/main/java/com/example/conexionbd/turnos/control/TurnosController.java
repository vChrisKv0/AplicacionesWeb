package com.example.conexionbd.turnos.control;

import com.example.conexionbd.turnos.model.Turnos;
import com.example.conexionbd.turnos.control.TurnosService;
import com.example.conexionbd.personal.model.Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnosController {

    @Autowired
    private TurnosService turnosService;

    // Obtener todos los turnos
    @GetMapping
    public ResponseEntity<List<Turnos>> getAllTurnos() {
        return ResponseEntity.ok(turnosService.findAll());
    }

    // Obtener turnos por ID de personal
    @GetMapping("/personal/{personalId}")
    public ResponseEntity<List<Turnos>> getTurnosByPersonal(@PathVariable Long personalId) {
        return ResponseEntity.ok(turnosService.findByPersonalId(personalId));
    }

    // Obtener un turno por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Turnos> getTurnoById(@PathVariable Long id) {
        return ResponseEntity.ok(turnosService.findById(id));
    }

    // Crear un nuevo turno
    @PostMapping
    public ResponseEntity<Turnos> createTurno(@RequestBody Turnos turno) {
        return ResponseEntity.ok(turnosService.save(turno));
    }

    // Actualizar un turno existente
    @PutMapping("/{id}")
    public ResponseEntity<Turnos> updateTurno(@PathVariable Long id, @RequestBody Turnos turno) {
        return ResponseEntity.ok(turnosService.update(id, turno));
    }

    // Cambiar asistencia de un turno
    @PatchMapping("/{id}/asistencia")
    public ResponseEntity<Turnos> updateAsistencia(@PathVariable Long id, @RequestBody boolean asistencia) {
        Turnos updatedTurno = turnosService.updateAsistencia(id, asistencia);
        return ResponseEntity.ok(updatedTurno);
    }

    // Eliminar un turno
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurno(@PathVariable Long id) {
        turnosService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para que un miembro del personal registre su asistencia
    @PatchMapping("/asistencia")
    public ResponseEntity<Turnos> marcarAsistencia(@RequestParam Long personalId) {
        Turnos turnoActualizado = turnosService.marcarAsistencia(personalId);
        return ResponseEntity.ok(turnoActualizado);
    }
}
