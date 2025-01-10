package com.example.conexionbd.reportes.control;

import com.example.conexionbd.reportes.model.Reporte;
import com.example.conexionbd.reportes.model.ReporteDTO;
import com.example.conexionbd.reportes.model.ReporteRepository;
import com.example.conexionbd.utils.Message;
import com.example.conexionbd.utils.TypesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ReporteService {

    private static final Logger logger = LoggerFactory.getLogger(ReporteService.class);

    private final ReporteRepository reporteRepository;

    @Autowired
    public ReporteService(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Reporte> reportes = reporteRepository.findAll();
        logger.info("La búsqueda de reportes ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(reportes, "Listado de reportes", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(ReporteDTO dto) {
        if (dto.getDescripcion().length() > 100) {
            return new ResponseEntity<>(new Message("La descripción excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Reporte reporte = new Reporte(dto.getDescripcion(), dto.getFecha(), dto.getTurno(), dto.getResponsable());
        reporte = reporteRepository.saveAndFlush(reporte);
        if (reporte == null) {
            return new ResponseEntity<>(new Message("El reporte no se registró", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        logger.info("El registro del reporte ha sido realizado correctamente");
        return new ResponseEntity<>(new Message(reporte, "El reporte se registró correctamente", TypesResponse.SUCCESS), HttpStatus.CREATED);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(ReporteDTO dto) {
        Optional<Reporte> reporteOptional = reporteRepository.findById(dto.getId());
        if (!reporteOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El reporte no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        if (dto.getDescripcion().length() > 100) {
            return new ResponseEntity<>(new Message("La descripción excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Reporte reporte = reporteOptional.get();
        reporte.setDescripcion(dto.getDescripcion());
        reporte.setFecha(dto.getFecha());
        reporte.setTurno(dto.getTurno());
        reporte.setResponsable(dto.getResponsable());
        reporte = reporteRepository.saveAndFlush(reporte);
        if (reporte == null) {
            return new ResponseEntity<>(new Message("El reporte no se actualizó", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        logger.info("La actualización del reporte ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(reporte, "El reporte se actualizó correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }
}
