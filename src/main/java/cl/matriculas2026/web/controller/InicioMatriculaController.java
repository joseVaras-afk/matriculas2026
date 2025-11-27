// src/main/java/cl/matriculas2026/web/controller/InicioMatriculaController.java
package cl.matriculas2026.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import cl.matriculas2026.service.ConsultaMatriculaService;

@Controller
@RequiredArgsConstructor
public class InicioMatriculaController {

    private final ConsultaMatriculaService consultaMatriculaService;

    @GetMapping({"/", "/inicio"})
    public String mostrarInicio(Model model,
                                @RequestParam(value = "mensaje", required = false) String mensaje,
                                @RequestParam(value = "tipo", required = false) String tipo) {
        if (mensaje != null) {
            model.addAttribute("mensaje", mensaje);
            model.addAttribute("tipo", (tipo == null ? "info" : tipo));
        }
        return "inicio-matricula";
    }

    @PostMapping("/inicio")
    public String procesarRut(@RequestParam("rutAlumno") String rutIngresado,
                              RedirectAttributes redirect) {
        try {
            boolean existe = consultaMatriculaService.existeMatriculaPorRut(rutIngresado);
            if (existe) {
                redirect.addAttribute("mensaje", "La alumna ya está matriculada.");
                redirect.addAttribute("tipo", "info");
                return "redirect:/inicio";
            } else {
                // Pasa el rut al formulario para prellenar el input
                redirect.addAttribute("rutAlumno", rutIngresado);
                return "redirect:/matriculas/form";
            }
        } catch (IllegalArgumentException ex) {
            redirect.addAttribute("mensaje", ex.getMessage());
            redirect.addAttribute("tipo", "error");
            return "redirect:/inicio";
        }
    }
}
