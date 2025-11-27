// src/main/java/cl/matriculas2026/service/impl/FichaMatriculaServiceImpl.java
package cl.matriculas2026.service.impl;

import cl.matriculas2026.entity.Matricula;
import cl.matriculas2026.service.FichaMatriculaService;
import cl.matriculas2026.service.dto.FichaMatriculaDTO;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FichaMatriculaServiceImpl implements FichaMatriculaService {

    @Override
    public byte[] generarFichaDocx(Matricula m) {
        try (InputStream in = new ClassPathResource("fichas/documento.docx").getInputStream();
                ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            if (in == null) {
                throw new IllegalStateException("Plantilla no encontrada: fichas/BASE2026_VELOCITY.docx");
            }
            byte[] bytes = in.readAllBytes();
            log.info("Plantilla bytes: {}", bytes.length);
            IXDocReport report = XDocReportRegistry.getRegistry()
                    .loadReport(new ByteArrayInputStream(bytes), TemplateEngineKind.Velocity);
            FichaMatriculaDTO dto = mapear(m);
            IContext ctx = report.createContext();
            ctx.put("fechaMatricula", dto.getFechaMatricula());
            ctx.put("numeroMatricula", dto.getNumeroMatricula());

            // alumno
            ctx.put("alumnoNombres", dto.getNombresAlumno());
            ctx.put("alumnoApellidoPaterno", dto.getApellidoPaternoAlumno());
            ctx.put("alumnoApellidoMaterno", dto.getApellidoMaternoAlumno());
            ctx.put("alumnoRun", dto.getRutAlumno());
            ctx.put("alumnoNacionalidad", dto.getNacionalidadAlumno());
            ctx.put("alumnoFechaNacimiento", dto.getFechaNacimientoAlumno());
            ctx.put("alumnoEdad31Mar", dto.getEdad31Marzo());
            ctx.put("alumnoDireccion", dto.getDireccionAlumno());
            ctx.put("alumnoComuna", dto.getComunaAlumno());
            ctx.put("viveCon", dto.getViveConAlumno());
            ctx.put("curso", cursoProx(dto.getCurso()));
            // retiro / transporte
            ctx.put("retiraNombre", dto.getRetiroNombre());
            ctx.put("retiraRut", dto.getRetiroRut());
            ctx.put("retiraParentesco", dto.getRetiroParentesco());
            if(dto.isUsaFurgon()) {
                ctx.put("usaFurgon", "Sí");
            } else {
                ctx.put("usaFurgon", "No");
            }

            // apoderado titular
            ctx.put("apoTitNombres", dto.getNombresApoderadoTitular());
            ctx.put("apoTitApellidoPaterno", dto.getApellidoPaternoApoderadoTitular());
            ctx.put("apoTitApellidoMaterno", dto.getApellidoMaternoApoderadoTitular());
            ctx.put("apoTitRun", dto.getRutApoderadoTitular());
            ctx.put("apoTitNacionalidad", dto.getNacionalidadApoderadoTitular());
            ctx.put("apoTitFechaNacimiento", dto.getFechaNacimientoApoderadoTitular());
            ctx.put("apoTitParentesco", dto.getParentescoApoderadoTitular());
            ctx.put("apoTitDireccion", dto.getDireccionApoderadoTitular());
            ctx.put("apoTitComuna", dto.getComunaApoderadoTitular());
            ctx.put("apoTitTelefono", dto.getTelefono1ApoderadoTitular());
            ctx.put("apoTitEmail", dto.getCorreoApoderadoTitular());

            // --- apoderado suplente ---
            ctx.put("apoSupNombres", dto.getNombresApoderadoSuplente());
            ctx.put("apoSupApellidoPaterno", dto.getApellidoPaternoApoderadoSuplente());
            ctx.put("apoSupApellidoMaterno", dto.getApellidoMaternoApoderadoSuplente());
            ctx.put("apoSupParentesco", dto.getParentescoApoderadoSuplente());
            ctx.put("apoSupRun", dto.getRutApoderadoSuplente());
            ctx.put("apoSupNacionalidad", dto.getNacionalidadApoderadoSuplente());
            ctx.put("apoSupTel1", dto.getTelefono1ApoderadoSuplente());
            ctx.put("apoSupTel2", dto.getTelefono2ApoderadoSuplente());
            ctx.put("apoSupDireccion", dto.getDireccionApoderadoSuplente());
            ctx.put("apoSupComuna", dto.getComunaApoderadoSuplente());
            ctx.put("apoSupEmail", dto.getCorreoApoderadoSuplente());

            // --- madre ---
            ctx.put("madreNombres", dto.getNombresMadre());
            ctx.put("madreApellidoPaterno", dto.getApellidoPaternoMadre());
            ctx.put("madreApellidoMaterno", dto.getApellidoMaternoMadre());
            ctx.put("madreRun", dto.getRutMadre());
            ctx.put("madreNacionalidad", dto.getNacionalidadMadre());
            ctx.put("madreTel1", dto.getTelefono1Madre());
            ctx.put("madreTel2", dto.getTelefono2Madre());
            ctx.put("madreDireccion", dto.getDireccionMadre());
            ctx.put("madreComuna", dto.getComunaMadre());
            ctx.put("madreEmail", dto.getCorreoMadre());
            ctx.put("madreNivelEduc", dto.getNivelEducacionMadre());
            ctx.put("madreOcupacion", dto.getOcupacionMadre());

            // padre

            ctx.put("padreNombres", dto.getNombresPadre());
            ctx.put("padreApellidoPaterno", dto.getApellidoPaternoPadre());
            ctx.put("padreApellidoMaterno", dto.getApellidoMaternoPadre());
            ctx.put("padreRun", dto.getRutPadre());
            ctx.put("padreNacionalidad", dto.getNacionalidadPadre());
            ctx.put("padreTel1", dto.getTelefono1Padre());
            ctx.put("padreTel2", dto.getTelefono2Padre());
            ctx.put("padreDireccion", dto.getDireccionPadre());
            ctx.put("padreComuna", dto.getComunaPadre());
            ctx.put("padreEmail", dto.getCorreoPadre());
            ctx.put("padreNivelEduc", dto.getNivelEducacionPadre());
            ctx.put("padreOcupacion", dto.getOcupacionPadre());

            // otros boolean/texto
        /*     ctx.put("establecimientoProcedencia", dto.getEstablecimientoProcedencia());
        
            ctx.put("asistioLenguaje", siNo(dto.isAsistioEscuelaLenguaje()));
        
            ctx.put("fono", siNo(dto.isFono()));

            ctx.put("pie", siNo(dto.isPertenecePie()));
            
            ctx.put("enfermedad", dto.getEnfermedad());

            ctx.put("alergia", dto.getAlergiaMedicamentos());

            ctx.put("etnia", siNo(dto.isPerteneceEtnia()));
            
            ctx.put("chileSolidario", siNo(dto.isChileSolidario()));
            
            ctx.put("programaPuente", siNo(dto.isProgramaPuente()));

            ctx.put("tieneFamiliar", siNo(dto.isTieneFamiliarEnEscuela()));

            ctx.put("alumnoCesfam", dto.getCesfamAlumno());

            ctx.put("enfermedad", dto.getEnfermedad()); */

            report.process(ctx, out);
            return out.toByteArray();
        } catch (Exception e) {
            log.error("Error generando ficha DOCX", e);
            throw new RuntimeException("Error generando ficha DOCX", e);
        }
    }

    public String siNo(Boolean valor){
        return Boolean.TRUE.equals(valor) ? "Sí" : "No";
    }

    @Override
    public FichaMatriculaDTO mapear(Matricula m) {
        FichaMatriculaDTO dto = new FichaMatriculaDTO();

        // Encabezado
        dto.setFechaMatricula(m.getFechaMatricula() != null
                ? m.getFechaMatricula().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                : "");
        dto.setCurso(m.getAlumno().getCurso());
        dto.setNumeroMatricula(String.valueOf(m.getNumeroMatricula()));

        // Alumno
        dto.setNombresAlumno(m.getAlumno().getNombres());
        dto.setApellidoPaternoAlumno(m.getAlumno().getApellidoPaterno());
        dto.setApellidoMaternoAlumno(m.getAlumno().getApellidoMaterno());
        dto.setRutAlumno(m.getAlumno().getRut());
        dto.setNacionalidadAlumno(m.getAlumno().getNacionalidad());
        dto.setFechaNacimientoAlumno(m.getAlumno().getFechaNacimiento());
        dto.setEdad31Marzo(m.getAlumno().getEdad31Marzo());
        dto.setDireccionAlumno(m.getAlumno().getDireccion());
        dto.setComunaAlumno(m.getAlumno().getComuna());
        dto.setViveConAlumno(m.getAlumno().getViveCon());
        dto.setCurso(m.getAlumno().getCurso());

        // Retiro/Furgón (ajusta según tu modelo)
        dto.setRetiroNombre(m.getAlumno().getRetiroNombre());
        dto.setRetiroRut(m.getAlumno().getRetiroRut());
        dto.setRetiroParentesco(m.getAlumno().getRetiroParentesco());
        dto.setUsaFurgon(Boolean.TRUE.equals(m.getAlumno().getUsaFurgon()));

        // Apoderado Titular
        var at = m.getApoderadoTitular();
        dto.setNombresApoderadoTitular(at.getNombres());
        dto.setApellidoPaternoApoderadoTitular(at.getApellidoPaterno());
        dto.setApellidoMaternoApoderadoTitular(at.getApellidoMaterno());
        dto.setRutApoderadoTitular(at.getRut());
        dto.setNacionalidadApoderadoTitular(at.getNacionalidad());
        dto.setFechaNacimientoApoderadoTitular(at.getFechaNacimiento());
        dto.setParentescoApoderadoTitular(at.getParentesco());
        dto.setDireccionApoderadoTitular(at.getDireccion());
        dto.setComunaApoderadoTitular(at.getComuna());
        dto.setTelefono1ApoderadoTitular(at.getTelefono1());
        dto.setTelefono2ApoderadoTitular(at.getTelefono2());
        dto.setCorreoApoderadoTitular(at.getCorreo());

        // Suplente (si existe)
        // Suplente (si existe)
        if (m.getApoderadoSuplente() != null) {
            var as = m.getApoderadoSuplente();
            dto.setNombresApoderadoSuplente(as.getNombres());
            // FALTABAN ESTAS DOS:
            dto.setApellidoPaternoApoderadoSuplente(as.getApellidoPaterno());
            dto.setApellidoMaternoApoderadoSuplente(as.getApellidoMaterno());

            dto.setParentescoApoderadoSuplente(as.getParentesco());
            dto.setRutApoderadoSuplente(as.getRut());
            dto.setNacionalidadApoderadoSuplente(as.getNacionalidad());
            dto.setTelefono1ApoderadoSuplente(as.getTelefono1());
            dto.setTelefono2ApoderadoSuplente(as.getTelefono2());
            dto.setDireccionApoderadoSuplente(as.getDireccion());
            dto.setComunaApoderadoSuplente(as.getComuna());
            dto.setCorreoApoderadoSuplente(as.getCorreoApoderadoSuplente());
        }

        // Madre

        if (m.getMadre() != null) {
            var madre = m.getMadre();
            dto.setNombresMadre(madre.getNombres());
            dto.setApellidoPaternoMadre(madre.getApellidoPaterno());
            dto.setApellidoMaternoMadre(madre.getApellidoMaterno());
            dto.setRutMadre(madre.getRut());
            dto.setNacionalidadMadre(madre.getNacionalidad());
            dto.setTelefono1Madre(madre.getTelefono1());
            dto.setTelefono2Madre(madre.getTelefono2());
            dto.setDireccionMadre(madre.getDireccion());
            dto.setComunaMadre(madre.getComuna());
            dto.setCorreoMadre(madre.getCorreo());
            dto.setNivelEducacionMadre(madre.getNivelEducacion());
            dto.setOcupacionMadre(madre.getOcupacion());
        }

        // Padre
        if (m.getPadre() != null) {
            var padre = m.getPadre();
            dto.setNombresPadre(padre.getNombres());
            dto.setApellidoPaternoPadre(padre.getApellidoPaterno());
            // ANTES: dto.setApellidoMaternoPadre(padre.getApellidoPaterno());
            dto.setApellidoMaternoPadre(padre.getApellidoMaterno());
            dto.setRutPadre(padre.getRut());
            dto.setNacionalidadPadre(padre.getNacionalidad());
            dto.setTelefono1Padre(padre.getTelefono1());
            dto.setTelefono2Padre(padre.getTelefono2());
            dto.setDireccionPadre(padre.getDireccion());
            dto.setComunaPadre(padre.getComuna());
            dto.setCorreoPadre(padre.getCorreo());
            dto.setNivelEducacionPadre(padre.getNivelEducacion());
            dto.setOcupacionPadre(padre.getOcupacion());
        }

        // Otros
        /* dto.setEstablecimientoProcedencia(m.getAlumno().getEstablecimientoProcedencia());
        dto.setAsistioEscuelaLenguaje(Boolean.TRUE.equals(m.getAlumno().getAsistioEscuelaLenguaje()));
        dto.setFono(Boolean.TRUE.equals(m.getAlumno().getFono()));
        dto.setPertenecePie(Boolean.TRUE.equals(m.getAlumno().getPertenecePie()));
        dto.setEnfermedad(m.getAlumno().getEnfermedad());
        dto.setAlergiaMedicamentos(m.getAlumno().getAlergia());
        dto.setPerteneceEtnia(m.getAlumno().getPerteneceEtnia());
        dto.setChileSolidario(Boolean.TRUE.equals(m.getAlumno().getChileSolidario()));
        dto.setProgramaPuente(Boolean.TRUE.equals(m.getAlumno().getProgramaPuente()));
        dto.setTieneFamiliarEnEscuela(Boolean.TRUE.equals(m.getAlumno().getTieneFamiliar()));
        dto.setCesfamAlumno(m.getAlumno().getCesfam()); */
        return dto;
    }

    public String cursoProx(String cursoActual){
        switch (cursoActual) {
        case "1°A": return "2°A";
        case "1°B": return "2°B";
        case "1°C": return "2°C";

        case "2°A": return "3°A";
        case "2°B": return "3°B";
        case "2°C": return "3°C";

        case "3°A": return "4°A";
        case "3°B": return "4°B";
        case "3°C": return "4°C";

        case "4°A": return "5°A";
        case "4°B": return "5°B";
        case "4°C": return "5°C";

        case "5°A": return "6°A";
        case "5°B": return "6°B";
        case "5°C": return "6°C";

        case "6°A": return "7°A";
        case "6°B": return "7°B";
        case "6°C": return "7°C";

        case "7°A": return "8°A";
        case "7°B": return "8°B";
        case "7°C": return "8°C";

        default:    return ""; // si no calza, deja el valor tal cual
    }
    }
}
