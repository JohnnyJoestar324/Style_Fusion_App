package com.martinezjohnny324.style_fusion.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martinezjohnny324.style_fusion.models.Barbero;
import com.martinezjohnny324.style_fusion.models.Cliente;
import com.martinezjohnny324.style_fusion.repositories.BarberoRepository;

@Service
public class BarberoService {

@Autowired
private BarberoRepository barberoRepository;




    //guardar barbero
    public Barbero guardarBarbero(Barbero barbero){
        return barberoRepository.save(barbero);
    }

    //listar barberos
    public List<Barbero> mostrarBarberoDisponible(){
        return barberoRepository.findAll();
    }


    //obtener barbero por id
     public Barbero mostrarBarberoDisponible(Long id){
        return barberoRepository.findById(id).orElse(null);
     }


     //Eliminar barbero

    public Optional<Barbero> borrarBarbero(Long id) {
    Optional<Barbero> barbero = barberoRepository.findById(id);
    barbero.ifPresent(c -> barberoRepository.delete(c)); // borra solo si existe
    return barbero; // devuelve el Optional, puede estar vacío si no existe
}
    


}
