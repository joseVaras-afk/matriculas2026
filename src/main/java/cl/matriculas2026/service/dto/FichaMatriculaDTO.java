
package cl.matriculas2026.service.dto;

import java.time.LocalDate;

import org.apache.poi.hpsf.Date;

import lombok.Data;

@Data
public class FichaMatriculaDTO {
    // encabezado
    private String fechaMatricula;
    private String curso;
    private String numeroMatricula;

    private String rutAlumno;
    private String nombresAlumno;
    private String apellidoPaternoAlumno;
    private String apellidoMaternoAlumno;
    private String cursoAlumno;
    private LocalDate fechaNacimientoAlumno;
    private String nacionalidadAlumno;
    private String direccionAlumno;
    private String comunaAlumno;
    private Integer edadAlumno;
    private String viveConAlumno;
    private String cesfamAlumno;
    private String saludMentalAlumno;
    private Integer edad31Marzo;
    private String establecimientoProcedencia;
    private boolean asistioEscuelaLenguaje;
    private boolean fono;
    private boolean pertenecePie;
    private String enfermedad;
    private String alergiaMedicamentos;
    private boolean perteneceEtnia;
    private boolean chileSolidario;
    private boolean programaPuente;
    private boolean tieneFamiliarEnEscuela;
    private String necesidadesEspeciales;

    // --- Retiro ---
    private String retiroNombre;
    private String retiroRut;
    private String retiroParentesco;
    private boolean usaFurgon;

    // --- Apoderado Titular ---
    private String rutApoderadoTitular;
    private String nombresApoderadoTitular;
    private String apellidoPaternoApoderadoTitular;
    private String apellidoMaternoApoderadoTitular;
    private String parentescoApoderadoTitular;
    private String correoApoderadoTitular;
    private String telefono1ApoderadoTitular;
    private String telefono2ApoderadoTitular;
    private String direccionApoderadoTitular;
    private String comunaApoderadoTitular;
    private String nacionalidadApoderadoTitular;
    private LocalDate fechaNacimientoApoderadoTitular;

    // --- Apoderado Suplente (opcional) ---
    private String rutApoderadoSuplente;
    private String nombresApoderadoSuplente;
    private String apellidoPaternoApoderadoSuplente;
    private String apellidoMaternoApoderadoSuplente;
    private String parentescoApoderadoSuplente;
    private String telefono1ApoderadoSuplente;
    private String telefono2ApoderadoSuplente;
    private String direccionApoderadoSuplente;
    private String comunaApoderadoSuplente;
    private String nacionalidadApoderadoSuplente;
    private LocalDate fechaNacimientoApoderadoSuplente;
    private String correoApoderadoSuplente;

    // --- Madre (opcional) ---
    private String rutMadre;
    private String nombresMadre;
    private String apellidoPaternoMadre;
    private String apellidoMaternoMadre;
    private String nacionalidadMadre;
    private LocalDate fechaNacimientoMadre;
    private String direccionMadre;
    private String comunaMadre;
    private String telefono1Madre;
    private String telefono2Madre;
    private String correoMadre;
    private String nivelEducacionMadre;
    private String ocupacionMadre;

    // --- Padre (opcional) ---
    private String rutPadre;
    private String nombresPadre;
    private String apellidoPaternoPadre;
    private String apellidoMaternoPadre;
    private String nacionalidadPadre;
    private LocalDate fechaNacimientoPadre;
    private String direccionPadre;
    private String comunaPadre;
    private String telefono1Padre;
    private String telefono2Padre;
    private String correoPadre;
    private String nivelEducacionPadre;
    private String ocupacionPadre;

    // otros campos pueden ir aquí
}
