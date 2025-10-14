package com.martinezjohnny324.style_fusion.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/membresia")
public class MembresiaController {

    public String index() {
        return "membresia";
    }   


}
