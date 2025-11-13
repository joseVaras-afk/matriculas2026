package cl.matriculas2026.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import cl.matriculas2026.repository.MatriculaRepository;
@Service
@RequiredArgsConstructor
public class ConsultaMatriculaService {
    private final MatriculaRepository matriculaRepository;

    public boolean existeMatriculaPorRut(String rutIngresado) {

        return matriculaRepository.findByRutAlumno(rutIngresado).isPresent();
    }

}
