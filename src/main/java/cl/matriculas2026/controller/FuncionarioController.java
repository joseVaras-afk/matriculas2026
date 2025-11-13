package cl.matriculas2026.controller;

import cl.matriculas2026.service.MatriculaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final MatriculaService matriculaService;

    public FuncionarioController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    // Página de login (la usa Spring Security como loginPage)
    @GetMapping("/login")
    public String login() {
        return "funcionarios/login"; // templates/funcionarios/login.html
    }
    @PostMapping("/logout")
    public String logout() {
        
        return "redirect:/funcionarios/login";
    }
    
    


}
