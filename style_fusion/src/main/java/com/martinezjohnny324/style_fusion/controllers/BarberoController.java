package com.martinezjohnny324.style_fusion.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.martinezjohnny324.style_fusion.models.Barbero;
import com.martinezjohnny324.style_fusion.repositories.BarberoRepository;
import com.martinezjohnny324.style_fusion.services.BarberoService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/barbero")
public class BarberoController {

private  final BarberoRepository barberoRepository;
private  final BarberoService barberoService;


    public BarberoController (BarberoRepository barberoRepository, BarberoService barberoService){
        this.barberoRepository = barberoRepository;
        this.barberoService = barberoService;


    }

    //Página principal del cliente de Style Fusion
    

    //vista añadir barbero
    @GetMapping("nuevo")
    public String crearBarbero(Model model ) {
        model.addAttribute("barbero", new Barbero());

        return "barbero/nuevo";
    }
    

    public String mostrarBarberosDisponibles(Model model){
        List<Barbero> barbero = barberoService.mostrarBarberoDisponible();
        model.addAttribute("barbero", barbero);
        return "barbero/total_barberos";
        

    }



    //guardar barbero
    @PostMapping("guardar")
    public String guardarBarbero(@ModelAttribute("barbero") Barbero barbero){
         barberoService.guardarBarbero(barbero);
         return "barbero/guardar";
   }

}
