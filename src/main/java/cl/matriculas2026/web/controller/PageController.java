package cl.matriculas2026.web.controller;

import cl.matriculas2026.service.MatriculaService;
import cl.matriculas2026.web.dto.MatriculaRequest;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/matriculas")
public class PageController {

    private final MatriculaService matriculaService;

    public PageController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    // Para parsear fechas tipo yyyy-MM-dd desde <input type="date">
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class,
                new org.springframework.beans.propertyeditors.CustomDateEditor(
                        new java.text.SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("matricula", new MatriculaRequest());
        return "matricula-form";
    }

    @PostMapping("/form")
    public String submit(@Valid @ModelAttribute("matricula") MatriculaRequest matricula,
                         BindingResult binding,
                         Model model) {
        if (binding.hasErrors()) {
            model.addAttribute("msgError", "Revisa los campos marcados.");
            return "matricula-form";
        }
        try {
            var creada = matriculaService.crearMatricula(matricula);
            model.addAttribute("numero", creada.getNumeroMatricula());
            return "redirect:/matriculas/ok?num=" + creada.getNumeroMatricula();
        } catch (Exception ex) {
            model.addAttribute("msgError", "No se pudo guardar: " + ex.getMessage());
            return "matricula-form";
        }
    }

    @GetMapping("/ok")
    public String ok(@RequestParam("num") Integer num, Model model) {
        model.addAttribute("numero", num);
        return "matricula-ok";
    }
       @GetMapping("")
    public String panelAdmin() {
        return "matrcula-form";
    }
}
