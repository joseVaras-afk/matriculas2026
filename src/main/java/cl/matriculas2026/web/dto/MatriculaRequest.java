package cl.matriculas2026.web.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MatriculaRequest {

    // --- Alumno ---
    @NotBlank private String rutAlumno;
    @NotBlank private String nombresAlumno;
    @NotBlank private String apellidoPaternoAlumno;
    @NotBlank private String apellidoMaternoAlumno;
    @NotBlank private String cursoAlumno;
    @NotNull private LocalDate fechaNacimientoAlumno;
    @NotBlank private String nacionalidadAlumno;
    @NotBlank private String direccionAlumno;
    @NotBlank private String comunaAlumno;
    private Integer edadAlumno;
    private String viveConAlumno;
    private String cesfamAlumno;
    private String saludMentalAlumno;

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
}
