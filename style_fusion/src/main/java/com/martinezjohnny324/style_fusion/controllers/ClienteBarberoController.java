package com.martinezjohnny324.style_fusion.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.martinezjohnny324.style_fusion.models.Barbero;

import com.martinezjohnny324.style_fusion.models.ClienteBarbero;
import com.martinezjohnny324.style_fusion.services.ClienteBarberoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequestMapping("/cliente-barbero")
public class ClienteBarberoController {

    private final ClienteBarberoService clienteBarberoService;

    public ClienteBarberoController( ClienteBarberoService clienteBarberoService) {
        this.clienteBarberoService = clienteBarberoService;
    }

    @GetMapping("")
    public String clienteBarberoMain() {

        return "cliente-barbero/home_cliente_barbero";
    }


    //nuevo cliente 

    @GetMapping("/nuevo")
    public String nuevoClienteBarbero(Model model){
        model.addAttribute("clienteBarbero", new ClienteBarbero());
        return "cliente-barbero/nuevo";
    }


    @PostMapping("/guardar")
    public String postMethodName(@ModelAttribute("clienteBarbero") ClienteBarbero clienteBarbero) {
        clienteBarberoService.guardar(clienteBarbero);
        return "redirect:/cliente-barbero";
    }
    


    //Mostrar clientes
    @GetMapping("/total_clientes_barbero")
    public String mostrarClientesBarbero(Model model){
        List<ClienteBarbero> clienteBarbero = clienteBarberoService.mostrarClientes();
        model.addAttribute("clienteBarbero", clienteBarbero);
        return "cliente-barbero/total_clientes_barbero";

    }

}
