package com.martinezjohnny324.style_fusion.controllers;

import com.martinezjohnny324.style_fusion.models.Cliente;
import com.martinezjohnny324.style_fusion.models.Users;
import com.martinezjohnny324.style_fusion.repositories.UserRepository;
import com.martinezjohnny324.style_fusion.services.ClienteService;
import com.martinezjohnny324.style_fusion.services.MembresiaService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller // Usar @Controller porque devuelve vistas (HTML)
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;
    private final MembresiaService membresiaService;
    private final UserRepository userRepository;

    // Constructor con inyección
     public  ClienteController(ClienteService clienteService, MembresiaService membresiaService, UserRepository userRepository) {
        this.clienteService = clienteService;
        this.membresiaService = membresiaService;
        this.userRepository = userRepository;
    }


    // Mostrar el home de Clientes
    @GetMapping("")
    public String HomeView(){

        return "cliente/home_cliente";
    }


    // 1. Mostrar lista de clientes (Endpoint /clientes/total_clientes)
    @GetMapping("/total_clientes")
    public String obtenerTotalClientes(Model model) { // Debe devolver String para la vista
        List<Cliente> clientes = clienteService.listarClientes();
        model.addAttribute("cliente", clientes);
        return "cliente/total_clientes"; // Nombre de la vista (ruta templates/cliente/total_clientes.html)
    }

    // 2. Mostrar formulario de nuevo cliente (Endpoint /clientes/nuevo)
    @GetMapping("/nuevo")
    public String nuevoCliente(Model model){
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("membresias", membresiaService.listarMembresias());
        return "cliente/nuevo"; 
    }
    @PostMapping("/guardar") 
    public String guardarClientes(@ModelAttribute("cliente") Cliente cliente){
        clienteService.guardarCliente(cliente);
        return "redirect:/cliente/total_clientes"; // Redirecciona después de guardar
    }
 // ✅ Eliminar directamente desde la tabla
    @PostMapping("/eliminar/{id}")
    public String eliminarClienteDirecto(@PathVariable("id") Long id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);

        if (cliente != null) {
            Users user = cliente.getUser();
            if (user != null) {
                userRepository.delete(user);
            }
            clienteService.borrarCliente(id);
        }

        return "redirect:/cliente/total_clientes";
    }



    @GetMapping("/editar/{id}")
public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
    Cliente cliente = clienteService.obtenerClientePorId(id);
    if (cliente == null) {
        throw new RuntimeException("Cliente no encontrado con ID: " + id);
    }

    model.addAttribute("cliente", cliente);
    model.addAttribute("membresias", membresiaService.listarMembresias());
    return "cliente/editar"; // nombre del HTML del formulario de edición
}
    

@PostMapping("/editar")
    public String editarCliente(@ModelAttribute("cliente") Cliente cliente,
                                @RequestParam(value = "membresiaId", required = false) Long membresiaId) {

        if (cliente.getId() == null) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo.");
        }

        // Buscar cliente existente
        Cliente clienteExistente = clienteService.obtenerClientePorId(cliente.getId());
        if (clienteExistente == null) {
            throw new RuntimeException("Cliente no encontrado con ID: " + cliente.getId());
        }

        // Actualizar campos
        clienteExistente.setId(cliente.getId());
        clienteExistente.setNombre(cliente.getNombre());
        clienteExistente.setApellido(cliente.getApellido());
        clienteExistente.setEmail(cliente.getEmail());

        // Actualizar membresía si se envió
        if (membresiaId != null) {
            var membresia = membresiaService.obtenerMembresiaPorId(membresiaId);
            clienteExistente.setMembresia(membresia);
        }

        clienteService.guardarCliente(clienteExistente);
        return "redirect:/cliente/total_clientes";
    }


    
}   