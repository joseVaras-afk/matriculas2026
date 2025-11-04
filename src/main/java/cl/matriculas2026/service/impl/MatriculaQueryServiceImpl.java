package cl.matriculas2026.service.impl;

import cl.matriculas2026.entity.Matricula;
import cl.matriculas2026.repository.MatriculaRepository;
import cl.matriculas2026.service.MatriculaQueryService;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class MatriculaQueryServiceImpl implements MatriculaQueryService {

    private final MatriculaRepository repo;

    public MatriculaQueryServiceImpl(MatriculaRepository repo) {
        this.repo = repo;
    }

    @Transactional
    @Override
    public void aprobar(Integer numeroMatricula) {
        Matricula m = repo.findById(numeroMatricula)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula no encontrada"));
        m.setEstadoMatricula("APROBADO");
        repo.save(m);
    }

    
}
