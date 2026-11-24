package com.martinezjohnny324.style_fusion.controllers.errors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class WebExceptions {

@GetMapping("/Acceso_Denegado")
public String AccesoDenegado(Model model) {
    model.addAttribute("mensajeError", "Tu usuario no tiene permisos para administrar este modulo.");
    model.addAttribute("detalleError", "Solicita el rol correspondiente al administrador del sistema.");
    
    return "Excepciones/403_Forbidden";
}


}
