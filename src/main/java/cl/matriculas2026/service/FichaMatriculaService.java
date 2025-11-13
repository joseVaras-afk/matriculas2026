// src/main/java/cl/matriculas2026/service/FichaMatriculaService.java
package cl.matriculas2026.service;

import cl.matriculas2026.entity.Matricula;
import cl.matriculas2026.service.dto.FichaMatriculaDTO;

public interface FichaMatriculaService {
    byte[] generarFichaDocx(Matricula matricula);
    FichaMatriculaDTO mapear(Matricula m); // separado para ordenar
}
