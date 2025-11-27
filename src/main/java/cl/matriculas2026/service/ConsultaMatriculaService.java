package cl.matriculas2026.service;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;
import cl.matriculas2026.repository.MatriculaRepository;
import cl.matriculas2026.util.RutUtils;
import cl.matriculas2026.entity.Matricula;
@Service
@RequiredArgsConstructor
public class ConsultaMatriculaService {
    private final MatriculaRepository matriculaRepository;

    public boolean existeMatriculaPorRut(String rutIngresado) {
        String rutNorm = RutUtils.normalize(rutIngresado);
        if (rutNorm == null || rutNorm.isBlank()) {
            throw new IllegalArgumentException("Debe ingresar un RUT válido.");
        }
        int exists = matriculaRepository.existsByRutAlumnoNormalized(rutNorm);
        return exists == 1;
    }

}
