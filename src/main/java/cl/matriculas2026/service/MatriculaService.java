package cl.matriculas2026.service;

import cl.matriculas2026.entity.Matricula;
import cl.matriculas2026.web.dto.MatriculaRequest;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MatriculaService {
    Matricula crearMatricula(MatriculaRequest request);
    Matricula obtenerPorNumero(Integer numero);
    List<Matricula> listarTodas();
    Matricula actualizarEstado(Integer numero, String nuevoEstado);
    Page<Matricula> buscarFiltrado(String curso, String nombre, Pageable pageable);
}
