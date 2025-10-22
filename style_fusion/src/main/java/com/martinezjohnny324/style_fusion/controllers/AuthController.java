package com.martinezjohnny324.style_fusion.controllers;

import com.martinezjohnny324.style_fusion.models.Users;
import com.martinezjohnny324.style_fusion.models.Cliente;
import com.martinezjohnny324.style_fusion.repositories.UserRepository;
import com.martinezjohnny324.style_fusion.repositories.ClienteRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          ClienteRepository clienteRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Mostrar formulario de registro
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("user", new Users());
        return "login/registro"; // templates/login/registro.html
    }

    // Procesar formulario de registro
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("user") Users user, Model model) {

        // 1️⃣ Verificar si el cliente existe en la base de datos
        Cliente cliente = clienteRepository.findByEmail(user.getEmail());
        if (cliente == null) {
            model.addAttribute("error", "No estás registrado como cliente. Contacta al administrador.");
            return "login/registro";
        }

        // 2️⃣ Verificar si el nombre de usuario ya existe
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("error", "El nombre de usuario ya está en uso.");
            return "login/registro";
        }

        // 3️⃣ Registrar al usuario con rol CLIENTE
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_CLIENTE");
        userRepository.save(user);

        cliente.setUser(user);
        clienteRepository.save(cliente);

        model.addAttribute("exito", "Registro exitoso. Ya puedes iniciar sesión.");
        return "login/login"; // redirige al login
    }
}
