package cl.matriculas2026.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import org.apache.poi.hpsf.Date;

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

    @Column(name = "edad31Marzo_alumno")
    private Integer edad31Marzo;

    @Column(name = "establecimientoProcedencia_alumno")
    private String establecimientoProcedencia;

    @Column(name = "asistioEscuelaLenguaje_alumno")
    private Boolean asistioEscuelaLenguaje; 

    @Column(name = "recibioFono_alumno")
    private Boolean fono;    

    @Column(name = "pertenecePie_alumno")
    private Boolean pertenecePie;

    @Column(name = "enfermedad_alumno")
    private String enfermedad;

    @Column(name = "alergia_alumno")
    private String alergia;

    @Column(name = "perteneceEtnia_alumno")
    private Boolean perteneceEtnia;

    @Column(name = "chileSolidario_alumno")
    private Boolean chileSolidario;

    @Column(name = "programaPuente_alumno")
    private Boolean programaPuente;

    @Column(name = "tieneFamiliar_alumno")
    private Boolean tieneFamiliar;

    @Column(name = "retiroNombre_alumno")
    private String retiroNombre;

    @Column(name = "retiroRut_alumno")
    private String retiroRut;

    @Column(name = "retiroParentesco_alumno")
    private String retiroParentesco;

    @Column(name = "usaFurgon_alumno")
    private Boolean usaFurgon;

    @Column(name = "contactoEmergencia_alumno")
    private String contactoEmergencia;

    @Column(name = "necesidadespecial_alumno")
    private String necesidadespeciales;
}
