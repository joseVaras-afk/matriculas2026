package cl.matriculas2026.web.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MatriculaRequest {

    // --- Alumno ---
    @NotBlank private String rutAlumno;
    @NotBlank private String nombresAlumno;
    @NotBlank private String apellidoPaternoAlumno;
    @NotBlank private String apellidoMaternoAlumno;
    @NotBlank private String cursoAlumno;
    @NotNull  private LocalDate fechaNacimientoAlumno;
    @NotBlank private String nacionalidadAlumno;
    @NotBlank private String direccionAlumno;
    @NotBlank private String comunaAlumno;
    private String  viveConAlumno;
    private String  cesfamAlumno;
    private String  saludMentalAlumno;
    private Integer edad31Marzo;
    private String  establecimientoProcedencia;
    private String necesidadesEspeciales;
    private Boolean autorizaAtraso;
private Boolean aceptaRice;
private Boolean religionAcepta;
private List<String> actividadesAlternativas; 

    // checkboxes → boolean
    private boolean asistioEscuelaLenguaje;
    private boolean fono;              // <-- antes Boolean
    private boolean perteneceEtnia;    // <-- antes String
    private boolean pertenecePie;
    private boolean chileSolidario;

    // campos de texto
    private String enfermedad;
    private String alergia;
    private String etnia;              // selección del <select>

    // --- Retiro ---
    private String retiroNombre;
    private String retiroRut;
    private String retiroParentesco;
    private boolean usaFurgon;

    // --- Apoderado Titular ---
    @NotBlank private String rutApoderadoTitular;
    @NotBlank private String nombresApoderadoTitular;
    @NotBlank private String apellidoPaternoApoderadoTitular;
    @NotBlank private String apellidoMaternoApoderadoTitular;
    @NotBlank private String parentescoApoderadoTitular;
    @Email @NotBlank private String correoApoderadoTitular;
    private String telefono1ApoderadoTitular;
    private String telefono2ApoderadoTitular;
    private String direccionApoderadoTitular;
    private String comunaApoderadoTitular;
    private String nacionalidadApoderadoTitular;
    private LocalDate fechaNacimientoApoderadoTitular;

    // --- Suplente ---
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

    // --- Madre ---
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

    // --- Padre ---
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
}
