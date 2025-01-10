package com.example.conexionbd.reportes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reporte")
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion", columnDefinition = "VARCHAR(100)")
    private String descripcion;

    @Column(name = "fecha", columnDefinition = "DATE")
    private String fecha;

    @Column(name = "turno", columnDefinition = "VARCHAR(50)")
    private String turno;

    @Column(name = "responsable", columnDefinition = "VARCHAR(50)")
    private String responsable;

    public Reporte() {
    }

    public Reporte(String descripcion, String fecha, String turno, String responsable) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.turno = turno;
        this.responsable = responsable;
    }

    public Reporte(Long id, String descripcion, String fecha, String turno, String responsable) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.turno = turno;
        this.responsable = responsable;
    }

    // Getters y Setters
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
