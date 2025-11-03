package cl.matriculas2026.web.controller;

import cl.matriculas2026.entity.Matricula;
import cl.matriculas2026.service.MatriculaService;
import cl.matriculas2026.service.EmailService;
import cl.matriculas2026.web.dto.MatriculaRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
@CrossOrigin // CORS básico
public class MatriculaController {

    private final MatriculaService matriculaService;
    @Autowired
    EmailService emailService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping
    public String crear(@Valid @RequestBody MatriculaRequest request, RedirectAttributes ra) {
        Matricula creada = matriculaService.crearMatricula(request);
        emailService.enviarComprobanteMatricula(creada);
        ra.addFlashAttribute("ok", "Matrícula registrada. Se envió el comprobante por correo.");
        return "redirect:/matriculas/form";

    }

    @GetMapping("/{numero}") 
    public ResponseEntity<Matricula> obtener(@PathVariable Integer numero) {
        return ResponseEntity.ok(matriculaService.obtenerPorNumero(numero));
    }

    @GetMapping
    public ResponseEntity<List<Matricula>> listar() {
        return ResponseEntity.ok(matriculaService.listarTodas());
    }

    @PutMapping("/{numero}/estado")
    public ResponseEntity<Matricula> actualizarEstado(@PathVariable Integer numero,
                                                      @RequestParam String estado) {
        return ResponseEntity.ok(matriculaService.actualizarEstado(numero, estado));
    }
}
