package cl.matriculas2026.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "apoderado_titular", schema = "matriculas2026")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ApoderadoTitular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idapoderadoTitular")
    private Integer id;

    @Column(name = "rut_apoderadoTitular")
    private String rut;

    @Column(name = "nombres_apoderadoTitular")
    private String nombres;

    @Column(name = "apellidoPaterno_apoderadoTitular")
    private String apellidoPaterno;

    @Column(name = "apellidoMaterno_apoderadoTitular")
    private String apellidoMaterno;

    @Column(name = "parentesco_apoderadoTitular")
    private String parentesco;

    @Column(name = "nacionalidad_apoderadoTitular")
    private String nacionalidad;

    @Column(name = "fechaNacimineto_apoderadoTitular")
    private LocalDate fechaNacimiento;

    @Column(name = "direccion_apoderadoTitular")
    private String direccion;

    @Column(name = "comuna_apoderadoTitular")
    private String comuna;

    @Column(name = "telefono1_apoderadoTitular")
    private String telefono1;

    @Column(name = "telefono2_apoderadoTitular")
    private String telefono2;

    @Column(name = "correo_apoderadoTitular")
    private String correo;
}
