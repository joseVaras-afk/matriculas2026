package cl.matriculas2026.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "matricula", schema = "matriculas2026")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numeroMatricula")
    private Integer numeroMatricula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idalumno", referencedColumnName = "idalumno")
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idapoderadoTitular", referencedColumnName = "idapoderadoTitular")
    private ApoderadoTitular apoderadoTitular;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idapoderadoSuplente", referencedColumnName = "idapoderadoSuplente")
    private ApoderadoSuplente apoderadoSuplente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idmadre", referencedColumnName = "idmadre")
    private Madre madre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idpadre", referencedColumnName = "idpadre")
    private Padre padre;

    @Column(name = "fecha_matricula")
    private LocalDate fechaMatricula;

    @Column(name = "estado_matricula")
    private String estadoMatricula;

    @Column(name = "rut_alumno")
    private String rutAlumno;
}
