package cl.matriculas2026.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "apoderadoSuplente", schema = "matriculas2026")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ApoderadoSuplente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idapoderadoSuplente")
    private Integer id;

    @Column(name = "rut_apoderadoSuplente")
    private String rut;

    @Column(name = "nombres_apoderadoSuplente")
    private String nombres;

    @Column(name = "apellidoPaterno_apoderadoSuplente")
    private String apellidoPaterno;

    @Column(name = "apellidoMaterno_apoderadoSuplente")
    private String apellidoMaterno;

    @Column(name = "nacionalidad_apoderadoSuplente")
    private String nacionalidad;

    @Column(name = "parentesco_apoderadoSuplente")
    private String parentesco;

    @Column(name = "direccion_apoderadoSuplente")
    private String direccion;

    @Column(name = "comuna_apoderadoSuplente")
    private String comuna;

    @Column(name = "fechaNacimiento_apoderadoSuplente")
    private LocalDate fechaNacimiento;

    @Column(name = "telefono1_apoderadoSuplente")
    private String telefono1;

    @Column(name = "telefono2_apoderadoSuplente")
    private String telefono2;

    @Column(name = "correo_apoderadoSuplente")
    private String correoApoderadoSuplente;
}
