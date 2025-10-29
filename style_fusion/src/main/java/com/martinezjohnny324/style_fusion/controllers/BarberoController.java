package com.martinezjohnny324.style_fusion.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    //Gestion inicial de los barberos
    @GetMapping("")
    public String HomeBarber(){
        return "barbero/home_barbero";
    }
    

    //vista añadir barbero
    @GetMapping("nuevo")
    public String crearBarbero(Model model ) {
        model.addAttribute("barbero", new Barbero());

        return "barbero/nuevo";
    }
    
    @GetMapping("/total_barberos")
    public String mostrarBarberosDisponibles(Model model){
        List<Barbero> barbero = barberoService.mostrarBarberoDisponible();
        model.addAttribute("barbero", barbero);
        return "barbero/total_barberos";
        

    }



    //guardar barbero
    @PostMapping("guardar")
    public String guardarBarbero(@ModelAttribute("barbero") Barbero barbero){
         barberoService.guardarBarbero(barbero);
         return "redirect:barbero/hombe_barbero";
   }


   //borrar barbero
   @PostMapping("/eliminar/{id}")
    public String eliminarBarbero(@PathVariable("id")Long id){
        
        Optional<Barbero> barbero = barberoService.borrarBarbero(id);

        return "redirect:/barbero/total_barberos";
   }


   //editar barbero

    @GetMapping("/editar/{id}")
    public String editarBarbero(@PathVariable("id") Long id, Model model){
        Barbero barbero = barberoService.mostrarBarberoDisponible(id);
         if (barbero == null) {
        throw new RuntimeException("Barbero no encontrado con ID: " + id);
    }
        model.addAttribute("barbero", barbero);
        return "barbero/editar_barbero";
    }


    @PostMapping("/editar")
    public String editarCliente(@ModelAttribute("barbero") Barbero barbero) {

        if (barbero.getId() == null) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo.");
        }

        // Buscar cliente existente
        Barbero barberoExistente = barberoService.mostrarBarberoDisponible(barbero.getId());
        if (barberoExistente == null) {
            throw new RuntimeException("Cliente no encontrado con ID: " + barbero.getId());
        }

        // Actualizar campos
        barberoExistente.setId(barbero.getId());
        barberoExistente.setNombre(barbero.getNombre());
        barberoExistente.setApellido(barbero.getApellido());
        barberoExistente.setNumero(barbero.getNumero());

        

        barberoService.guardarBarbero(barberoExistente);
        return "redirect:/cliente/total_clientes";
    }



}
