package com.martinezjohnny324.style_fusion.controllers.restcontrollers;

import com.martinezjohnny324.style_fusion.models.dto.ClienteDto;
import com.martinezjohnny324.style_fusion.repositories.ClienteRepository;
import com.martinezjohnny324.style_fusion.services.ClienteService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
public class ClienteRestController {
     
     @Autowired 
     ClienteService clservice;


    @GetMapping("/Cliente_Info/{idLong}")
    public ResponseEntity<ClienteDto>ObtenerClientePorId(@PathVariable Long idLong){
       ClienteDto cDto = clservice.obtenerClientePorId(idLong);

            if (cDto == null){
                ResponseEntity.notFound().build();
            }

        return ResponseEntity.ok(cDto);
    }
    
    
    
    



}
