package cl.matriculas2026.service;

import cl.matriculas2026.entity.Matricula;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MatriculaQueryService {
    void aprobar(Integer numeroMatricula);
}
