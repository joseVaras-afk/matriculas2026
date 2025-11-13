package cl.matriculas2026.controller;

import cl.matriculas2026.entity.Matricula;
import cl.matriculas2026.service.FichaMatriculaService;
import cl.matriculas2026.service.MatriculaService;
import cl.matriculas2026.repository.MatriculaRepository;

import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioMatriculaController {

    private final MatriculaService matriculaService;
    private final MatriculaRepository matriculaRepository;
    private final FichaMatriculaService fichaMatriculaService;

    public FuncionarioMatriculaController(MatriculaService matriculaService,
                                         MatriculaRepository matriculaRepository,
                                         FichaMatriculaService fichaMatriculaService) {
        this.matriculaService = matriculaService;
        this.matriculaRepository = matriculaRepository;
        this.fichaMatriculaService = fichaMatriculaService;
    }


     @GetMapping("/matriculas-list")
public String listaMatriculas(
        @RequestParam(required = false) String curso,
        @RequestParam(required = false) String nombre,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size,
        Model model) {

    // Lista de cursos (puedes moverla a un servicio o BD)
    List<String> cursos = List.of(
        "PRE-KINDER A","PRE-KINDER B",
        "KINDER A","KINDER B","KINDER C","KINDER D",
        "1°A","1°B","1°C",
        "2°A","2°B","2°C",
        "3°A","3°B","3°C",
        "4°A","4°B","4°C",
        "5°A","5°B","5°C",
        "6°A","6°B","6°C",
        "7°A","7°B","7°C",
        "8°A","8°B","8°C"
    );

    // Orden por numeroMatricula DESC
    Pageable pageable = PageRequest.of(page, size, Sort.by("numeroMatricula").descending());

    Page<Matricula> pageResult = matriculaService.buscarFiltrado(curso, nombre, pageable); // o usa pageable si tu service lo recibe

    model.addAttribute("cursos", cursos);
    model.addAttribute("curso", curso);
    model.addAttribute("nombre", nombre);
    model.addAttribute("matriculas", pageResult.getContent());
    model.addAttribute("page", pageResult); // por si paginas en la vista

    return "funcionarios/matriculas-list";
}

@GetMapping("/matriculas/{id}/ficha")
public ResponseEntity<byte[]> generarFicha(@PathVariable("id") int id) {
    Matricula m = matriculaService.obtenerPorNumero(id);

    byte[] bytes = fichaMatriculaService.generarFichaDocx(m);

    String fileName = String.format("Ficha_%s_%s_%s_%s.docx",
            m.getAlumno().getCurso(),
            m.getNumeroMatricula(),
            m.getAlumno().getNombres().replaceAll(" ", "_"),
            m.getAlumno().getApellidoPaterno().replaceAll(" ", "_"));

    return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
            .header("Content-Type", "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
            .body(bytes);
}
}
