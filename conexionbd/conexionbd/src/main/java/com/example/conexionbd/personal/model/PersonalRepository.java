package com.example.conexionbd.personal.model;

import java.util.List;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {
    // Método para obtener todos los registros de personal
    List<Personal> findAll();
}
