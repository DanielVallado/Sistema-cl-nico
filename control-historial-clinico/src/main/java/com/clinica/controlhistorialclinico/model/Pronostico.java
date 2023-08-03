package com.clinica.controlhistorialclinico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.Date;

@Entity
@Table(name = "pronostico")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pronostico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Long pacienteId;
    private String descripcion;
    private Date fechaPronostico;

}
