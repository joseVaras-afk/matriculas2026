package cl.matriculas2026.repository;

import cl.matriculas2026.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {}
