package com.martinezjohnny324.style_fusion.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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



    //Eliminar cliente barbero

    @PostMapping("/eliminar/{id}")
    public String eliminarClienteBarbero(@PathVariable("id") Long id){
        clienteBarberoService.eliminarClienteBarbero(id);
        return "redirect:/cliente-barbero/total_clientes_barbero";
    }
    
    @GetMapping("/editar/{id}")
public String editarBarbero(@PathVariable("id") Long id, Model model) {
    ClienteBarbero clienteBarbero = clienteBarberoService.encontrarID(id);
    if (clienteBarbero == null) {
        throw new RuntimeException("Cliente no encontrado con ID: " + id);
    }
    model.addAttribute("clienteBarbero", clienteBarbero);
    return "cliente-barbero/editar";
}

@PostMapping("/editar")
public String editarCliente(@ModelAttribute("clienteBarbero") ClienteBarbero clienteBarbero) {
    if (clienteBarbero.getId() == null) {
        throw new IllegalArgumentException("El ID del cliente no puede ser nulo.");
    }

    ClienteBarbero clienteExistente = clienteBarberoService.encontrarID(clienteBarbero.getId());
    if (clienteExistente == null) {
        throw new RuntimeException("Cliente no encontrado con ID: " + clienteBarbero.getId());
    }

    clienteExistente.setNombre(clienteBarbero.getNombre());
    clienteExistente.setApellido(clienteBarbero.getApellido());
    clienteExistente.setNumero(clienteBarbero.getNumero());

    clienteBarberoService.guardar(clienteExistente);
    return "redirect:/cliente-barbero/total_clientes_barbero";
}


}
