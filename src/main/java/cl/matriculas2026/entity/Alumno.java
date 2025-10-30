package cl.matriculas2026.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "alumno", schema = "matriculas2026")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idalumno")
    private Integer id;

    @Column(name = "rut_alumno")
    private String rut;

    @Column(name = "nombres_alumno")
    private String nombres;

    @Column(name = "apelidoPaterno_alumno") // así está en la BD
    private String apellidoPaterno;

    @Column(name = "apellidoMaterno_alumno")
    private String apellidoMaterno;

    @Column(name = "curso_alumno")
    private String curso;

    @Column(name = "fechaNacimineto_alumno") // así está en la BD
    private LocalDate fechaNacimiento;

    @Column(name = "nacionalidad_alumno")
    private String nacionalidad;

    @Column(name = "edad_alumno")
    private Integer edad;

    @Column(name = "direccion_alumno", length = 100)
    private String direccion;

    @Column(name = "comuna_alumno")
    private String comuna;

    @Column(name = "viveCon_alumno")
    private String viveCon;

    @Column(name = "cesfam_alumno")
    private String cesfam;

    @Column(name = "saludMental_alumno")
    private String saludMental;
}
