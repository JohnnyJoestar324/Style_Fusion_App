package com.martinezjohnny324.style_fusion.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "redirect:/login"; 
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login/login"; 
    }

    
    
// ... dentro de HomeController ...

    // 3. Mapea la ruta de éxito tras el login (por ejemplo, /home o /dashboard)
    @GetMapping("/home") 
    public String showMainHome() {
        // Esto resuelve a src/main/resources/templates/main/home.html
        return "main/home"; 
    }

    //home principal de los clientes de Style Fusion
    @GetMapping("/home_barbero")
    public String homeBarbero() {  
        return "main/home_barbero";
    }

}



