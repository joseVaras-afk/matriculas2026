package cl.matriculas2026.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "padre", schema = "matriculas2026")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Padre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpadre")
    private Integer id;

    @Column(name = "rut_padre")
    private String rut;

    @Column(name = "nombres_padre")
    private String nombres;

    @Column(name = "apellidoPaterno_padre")
    private String apellidoPaterno;

    @Column(name = "apellidoMaterno_padre")
    private String apellidoMaterno;

    @Column(name = "nacionalidad_padre")
    private String nacionalidad;

    @Column(name = "fechaNacimiento_padre")
    private LocalDate fechaNacimiento;

    @Column(name = "direccion_padre")
    private String direccion;

    @Column(name = "comuna_padre")
    private String comuna;

    @Column(name = "telefono1_padre")
    private String telefono1;

    @Column(name = "telefono2_padre")
    private String telefono2;

    @Column(name = "correo_padre")
    private String correo;

    @Column(name = "nivelEducacion_padre")
    private String nivelEducacion;

    @Column(name = "ocupacion_padre")
    private String ocupacion;
}
