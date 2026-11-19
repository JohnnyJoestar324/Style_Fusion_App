package com.martinezjohnny324.style_fusion.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping; 
import com.martinezjohnny324.style_fusion.models.dto.MembresiaDto;
import com.martinezjohnny324.style_fusion.services.MembresiaService;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/membresia")
public class MembresiaController {

    private final MembresiaService membresiaService;

    public MembresiaController(MembresiaService membresiaService){
        this.membresiaService = membresiaService;
    }


   @GetMapping("")
   public String home() {
       return "membresia/membresia";
   }
   
   @GetMapping("/membresias_disponibles")
   public String membresiasDisponibles(Model model){
        List<MembresiaDto> memb = membresiaService.listarMembresias();
        model.addAttribute("Lista_Membresia", memb);
        return "membresia/membresias_disponibles";
    


   }




}
