package com.example.conexionbd.reportes.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ReporteDTO {

    public interface Register {}

    private Long id;

    @NotEmpty(message = "La descripción es obligatoria", groups = {Register.class})
    @Size(max = 100, message = "La descripción no debe exceder los 100 caracteres", groups = {Register.class})
    private String descripcion;

    @NotEmpty(message = "La fecha es obligatoria", groups = {Register.class})
    private String fecha;

    @NotEmpty(message = "El turno es obligatorio", groups = {Register.class})
    @Size(max = 50, message = "El turno no debe exceder los 50 caracteres", groups = {Register.class})
    private String turno;

    @NotEmpty(message = "El responsable es obligatorio", groups = {Register.class})
    @Size(max = 50, message = "El nombre del responsable no debe exceder los 50 caracteres", groups = {Register.class})
    private String responsable;

    public ReporteDTO() {}

    public ReporteDTO(Long id, String descripcion, String fecha, String turno, String responsable) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.turno = turno;
        this.responsable = responsable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
}
