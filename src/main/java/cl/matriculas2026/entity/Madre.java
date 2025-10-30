package cl.matriculas2026.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "madre", schema = "matriculas2026")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Madre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmadre")
    private Integer id;

    @Column(name = "rut_madre")
    private String rut;

    @Column(name = "nombres_madre")
    private String nombres;

    @Column(name = "apellidoPaterno_madre")
    private String apellidoPaterno;

    @Column(name = "apellidoMaterno_madre")
    private String apellidoMaterno;

    @Column(name = "nacionalidad_madre")
    private String nacionalidad;

    @Column(name = "fechaNacimiento_madre")
    private LocalDate fechaNacimiento;

    @Column(name = "direccion_madre")
    private String direccion;

    @Column(name = "comuna_madre")
    private String comuna;

    @Column(name = "telefono1_madre")
    private String telefono1;

    @Column(name = "telefono2_madre")
    private String telefono2;

    @Column(name = "correo_madre")
    private String correo;

    @Column(name = "nivelEducacion_madre")
    private String nivelEducacion;

    @Column(name = "ocupacion_madre")
    private String ocupacion;
}
