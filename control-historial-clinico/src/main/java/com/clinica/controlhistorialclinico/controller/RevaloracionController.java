package com.clinica.controlhistorialclinico.controller;

import com.clinica.controlhistorialclinico.error.CHCError;
import com.clinica.controlhistorialclinico.model.Revaloracion;
import com.clinica.controlhistorialclinico.service.RevaloracionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/revaloracion")
@Log4j2
public class RevaloracionController {

    private RevaloracionService service;

    @Autowired
    private void setService(RevaloracionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllRevaloraciones() {
        try {
            log.info("Consulta de todos las revaloraciones.");
            return ResponseEntity.ok().body(service.getAllRevaloraciones());
        } catch (CHCError e) {
            log.warn("No se encontraron revaloraciones.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRevaloracionesById(@PathVariable("id") Long id) {
        try {
            log.info("Consulta de todos las revaloraciones por id.");
            return ResponseEntity.ok().body(service.getRevaloracionesByPacienteId(id));
        } catch (CHCError e) {
            log.warn("No se encontraron revaloraciones.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createRevaloracion(@RequestBody @Validated Revaloracion exploracionFisica) {
        try {
            log.info("Revaloracion insertado.");
            Revaloracion response = service.createRevaloracion(exploracionFisica);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (HttpClientErrorException.NotFound | HttpClientErrorException.BadRequest e) {
            log.error("Paciente no encontrado.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Paciente no encontrado.");
        } catch (DataIntegrityViolationException e) {
            log.error("Datos inválidos.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos inválidos.");
        } catch (Exception e) {
            log.error("Error al registrar la revaloracion: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al registrar la revaloracion.");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteRevaloracion(@PathVariable("id") Long id) {
        try {
            log.info("Revaloracion con id %s eliminada".formatted(id));
            service.deleteRevaloracionByPacienteId(id);
        }catch (Exception e) {
            log.error("Error al eliminar la revaloracion: ", e);
        }
    }
    
}
