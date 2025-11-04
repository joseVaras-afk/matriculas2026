package cl.matriculas2026.repository;

import cl.matriculas2026.entity.Matricula;

import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;


public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {

    @Query("""
        SELECT m FROM Matricula m
        JOIN m.alumno a
        JOIN m.apoderadoTitular t
        WHERE (:curso IS NULL OR :curso = '' OR a.curso = :curso)
          AND (:nombre IS NULL OR :nombre = '' OR
               LOWER(CONCAT(a.nombres,' ',a.apellidoPaterno,' ',COALESCE(a.apellidoMaterno,''))) LIKE LOWER(CONCAT('%', :nombre, '%')))
        """)
    Page<Matricula> buscarFiltrado(@Param("curso") String curso,
                                  @Param("nombre") String nombre,
                                  Pageable pageable);
}
