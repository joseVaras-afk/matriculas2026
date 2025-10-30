package cl.matriculas2026.service.impl;

import cl.matriculas2026.entity.*;
import cl.matriculas2026.repository.*;
import cl.matriculas2026.service.MatriculaService;
import cl.matriculas2026.web.dto.MatriculaRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MatriculaServiceImpl implements MatriculaService {

    private final AlumnoRepository alumnoRepo;
    private final ApoderadoTitularRepository titularRepo;
    private final ApoderadoSuplenteRepository suplenteRepo;
    private final MadreRepository madreRepo;
    private final PadreRepository padreRepo;
    private final MatriculaRepository matriculaRepo;

    public MatriculaServiceImpl(AlumnoRepository alumnoRepo,
                                ApoderadoTitularRepository titularRepo,
                                ApoderadoSuplenteRepository suplenteRepo,
                                MadreRepository madreRepo,
                                PadreRepository padreRepo,
                                MatriculaRepository matriculaRepo) {
        this.alumnoRepo = alumnoRepo;
        this.titularRepo = titularRepo;
        this.suplenteRepo = suplenteRepo;
        this.madreRepo = madreRepo;
        this.padreRepo = padreRepo;
        this.matriculaRepo = matriculaRepo;
    }

    @Override
    @Transactional
    public Matricula crearMatricula(MatriculaRequest r) {
        // Alumno
        Alumno alumno = Alumno.builder()
                .rut(r.getRutAlumno())
                .nombres(r.getNombresAlumno())
                .apellidoPaterno(r.getApellidoPaternoAlumno())
                .apellidoMaterno(r.getApellidoMaternoAlumno())
                .curso(r.getCursoAlumno())
                .fechaNacimiento(r.getFechaNacimientoAlumno())
                .nacionalidad(r.getNacionalidadAlumno())
                .edad(r.getEdadAlumno())
                .direccion(r.getDireccionAlumno())
                .comuna(r.getComunaAlumno())
                .viveCon(r.getViveConAlumno())
                .cesfam(r.getCesfamAlumno())
                .saludMental(r.getSaludMentalAlumno())
                .build();
        alumno = alumnoRepo.save(alumno);

        // Apoderado Titular (obligatorio)
        ApoderadoTitular titular = ApoderadoTitular.builder()
                .rut(r.getRutApoderadoTitular())
                .nombres(r.getNombresApoderadoTitular())
                .apellidoPaterno(r.getApellidoPaternoApoderadoTitular())
                .apellidoMaterno(r.getApellidoMaternoApoderadoTitular())
                .parentesco(r.getParentescoApoderadoTitular())
                .correo(r.getCorreoApoderadoTitular())
                .telefono1(r.getTelefono1ApoderadoTitular())
                .telefono2(r.getTelefono2ApoderadoTitular())
                .direccion(r.getDireccionApoderadoTitular())
                .comuna(r.getComunaApoderadoTitular())
                .nacionalidad(r.getNacionalidadApoderadoTitular())
                .fechaNacimiento(r.getFechaNacimientoApoderadoTitular())
                .build();
        titular = titularRepo.save(titular);

        // Apoderado Suplente (si trae datos mínimos)
        ApoderadoSuplente suplente = null;
        if (r.getRutApoderadoSuplente() != null && !r.getRutApoderadoSuplente().isBlank()) {
            suplente = ApoderadoSuplente.builder()
                    .rut(r.getRutApoderadoSuplente())
                    .nombres(r.getNombresApoderadoSuplente())
                    .apellidoPaterno(r.getApellidoPaternoApoderadoSuplente())
                    .apellidoMaterno(r.getApellidoMaternoApoderadoSuplente()) // ojo, si te equivocas de nombre compílalo
                    .parentesco(r.getParentescoApoderadoSuplente())
                    .telefono1(r.getTelefono1ApoderadoSuplente())
                    .telefono2(r.getTelefono2ApoderadoSuplente())
                    .direccion(r.getDireccionApoderadoSuplente())
                    .comuna(r.getComunaApoderadoSuplente())
                    .nacionalidad(r.getNacionalidadApoderadoSuplente())
                    .fechaNacimiento(r.getFechaNacimientoApoderadoSuplente())
                    .build();
            suplente = suplenteRepo.save(suplente);
        }

        // Madre (opcional)
        Madre madre = null;
        if (r.getRutMadre() != null && !r.getRutMadre().isBlank()) {
            madre = Madre.builder()
                    .rut(r.getRutMadre())
                    .nombres(r.getNombresMadre())
                    .apellidoPaterno(r.getApellidoPaternoMadre())
                    .apellidoMaterno(r.getApellidoMaternoMadre())
                    .nacionalidad(r.getNacionalidadMadre())
                    .fechaNacimiento(r.getFechaNacimientoMadre())
                    .direccion(r.getDireccionMadre())
                    .comuna(r.getComunaMadre())
                    .telefono1(r.getTelefono1Madre())
                    .telefono2(r.getTelefono2Madre())
                    .correo(r.getCorreoMadre())
                    .nivelEducacion(r.getNivelEducacionMadre())
                    .ocupacion(r.getOcupacionMadre())
                    .build();
            madre = madreRepo.save(madre);
        }

        // Padre (opcional)
        Padre padre = null;
        if (r.getRutPadre() != null && !r.getRutPadre().isBlank()) {
            padre = Padre.builder()
                    .rut(r.getRutPadre())
                    .nombres(r.getNombresPadre())
                    .apellidoPaterno(r.getApellidoPaternoPadre())
                    .apellidoMaterno(r.getApellidoMaternoPadre())
                    .nacionalidad(r.getNacionalidadPadre())
                    .fechaNacimiento(r.getFechaNacimientoPadre())
                    .direccion(r.getDireccionPadre())
                    .comuna(r.getComunaPadre())
                    .telefono1(r.getTelefono1Padre())
                    .telefono2(r.getTelefono2Padre())
                    .correo(r.getCorreoPadre())
                    .nivelEducacion(r.getNivelEducacionPadre())
                    .ocupacion(r.getOcupacionPadre())
                    .build();
            padre = padreRepo.save(padre);
        }

        // Matrícula
        
        Matricula m = Matricula.builder()
                .alumno(alumno)
                .apoderadoTitular(titular)
                .apoderadoSuplente(suplente)
                .madre(madre)
                .padre(padre)
                .fechaMatricula(LocalDate.now())
                .estadoMatricula("PENDIENTE")
                .build();

        return matriculaRepo.save(m);
    }

    @Override
    public Matricula obtenerPorNumero(Integer numero) {
        return matriculaRepo.findById(numero)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula no encontrada: " + numero));
    }

    @Override
    public List<Matricula> listarTodas() {
        return matriculaRepo.findAll();
    }

    @Override
    @Transactional
    public Matricula actualizarEstado(Integer numero, String nuevoEstado) {
        Matricula m = obtenerPorNumero(numero);
        m.setEstadoMatricula(nuevoEstado);
        return matriculaRepo.save(m);
    }
}
