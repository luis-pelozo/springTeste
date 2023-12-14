package br.com.sitepsico.psicopartilhar.controllers;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.sitepsico.psicopartilhar.models.Profissionais;
import br.com.sitepsico.psicopartilhar.repository.ProfissionaisRepository;

@Controller
public class ProfissionaisController {
    private final ProfissionaisRepository profissionaisRepository;

    public ProfissionaisController(ProfissionaisRepository profissionaisRepository) {
        this.profissionaisRepository = profissionaisRepository;
    }

    @GetMapping("/equipe")
    public ModelAndView list(){
        return new ModelAndView(
            "profissional/list", 
            Map.of("profissionais",
            profissionaisRepository.findAll())
        );
    }

    @GetMapping("/createEquipe")
    public ModelAndView create(){
        return new ModelAndView( 
            "profissional/form", 
            Map.of("profissional", 
            new Profissionais())
        );
    }

    @PostMapping("/createEquipe")
    public String create(Profissionais profissionais){
        profissionaisRepository.save(profissionais);
        return "redirect:/equipe";
    }      
}
