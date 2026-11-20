package com.martinezjohnny324.style_fusion.controllers;


import com.martinezjohnny324.style_fusion.models.dto.ClienteDto;
import com.martinezjohnny324.style_fusion.repositories.ClienteRepository;
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

    private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;
    private final MembresiaService membresiaService;
    private final UserRepository userRepository;

    // Constructor con inyección
     public  ClienteController(ClienteService clienteService, MembresiaService membresiaService, UserRepository userRepository, ClienteRepository clienteRepository) {
        this.clienteService = clienteService;
        this.membresiaService = membresiaService;
        this.userRepository = userRepository;
        this.clienteRepository = clienteRepository;
    }


    // Mostrar el home de Clientes
    @GetMapping("")
    public String HomeView(){

        return "cliente/home_cliente";
    }


    // 1. Mostrar lista de clientes (Endpoint /clientes/total_clientes)
    @GetMapping("/total_clientes")
    public String obtenerTotalClientes(Model model) { // Debe devolver String para la vista
        List<ClienteDto> clientes = clienteService.listarClientes();
        model.addAttribute("clientes", clientes);
        return "cliente/total_clientes"; // Nombre de la vista (ruta templates/cliente/total_clientes.html)
    }

    // 2. Mostrar formulario de nuevo cliente (Endpoint /clientes/nuevo)
    @GetMapping("/nuevo")
    public String nuevoCliente(Model model){
        model.addAttribute("cliente", new ClienteDto());
        model.addAttribute("membresias", membresiaService.listarMembresias());
        return "cliente/nuevo"; 
    }
    @PostMapping("/guardar") 
    public String guardarClientes(@ModelAttribute("cliente") ClienteDto cDto){

    System.out.println("====== GUARDANDO CLIENTE ======");
    System.out.println("ID: " + cDto.getId());
    System.out.println("EMAIL: " + cDto.getEmail());
    System.out.println("MEMBRESIA: " + cDto.getId_Membresia());
    System.out.println("USER: " + cDto.getId_user());

    clienteService.guardarCliente(cDto);
    return "redirect:/cliente/total_clientes"; // Redirecciona después de guardar
    }
 // Eliminar directamente desde la tabla
    @PostMapping("/eliminar/{id}")
    public String eliminarClienteDirecto(@PathVariable("id") Long id) {
       clienteService.borrarCliente(id);

        return "redirect:/cliente/total_clientes";
    }



@GetMapping("/editar/{id}")
public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
    ClienteDto cDto = clienteService.obtenerClientePorId(id);
    if (cDto == null) {
        throw new RuntimeException("Cliente no encontrado con ID: " + id);
    }

    model.addAttribute("cliente", cDto);
    model.addAttribute("membresias", membresiaService.listarMembresias());
    return "cliente/editar"; // nombre del HTML del formulario de edición
}
    

@PostMapping("/editar")
    public String editarCliente(@ModelAttribute("cliente") ClienteDto cliente) {

        if (cliente.getId() == null) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo.");
        }

        // Buscar cliente existente
        ClienteDto clienteExistente = clienteService.obtenerClientePorId(cliente.getId());
        if (clienteExistente == null) {
            throw new RuntimeException("Cliente no encontrado con ID: " + cliente.getId());
        }

        // Actualizar campos
        clienteExistente.setId(cliente.getId());
        clienteExistente.setNombre(cliente.getNombre());
        clienteExistente.setApellido(cliente.getApellido());
        clienteExistente.setEmail(cliente.getEmail());

        // Actualizar membresía si se envió
        if (cliente.getId_Membresia() != null) {
            // var membresia = membresiaService.obtenerMembresiaPorId(membresiaId);
            clienteExistente.setId_Membresia(cliente.getId_Membresia()); 
        }

        clienteService.guardarCliente(clienteExistente);
        return "redirect:/cliente/total_clientes";
    }


    
}   