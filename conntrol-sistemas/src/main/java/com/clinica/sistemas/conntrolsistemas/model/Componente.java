package com.clinica.sistemas.conntrolsistemas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "componentes")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Componente {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Sistema sistema;
    private String descripcion;

}
