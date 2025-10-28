package com.martinezjohnny324.style_fusion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martinezjohnny324.style_fusion.models.Barbero;
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

        public void eliminarBarbero(Long id){
            barberoRepository.deleteById(id);
        }
    


}
