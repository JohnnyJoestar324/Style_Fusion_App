package com.martinezjohnny324.style_fusion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martinezjohnny324.style_fusion.models.ClienteBarbero;
import com.martinezjohnny324.style_fusion.repositories.ClienteBarberoRepository;

@Service
public class ClienteBarberoService {

    @Autowired 
    private ClienteBarberoRepository clienteBarberoRepository;

    //guardar cliente barbero

    public ClienteBarbero guardar(ClienteBarbero clienteBarbero){
        return clienteBarberoRepository.save(clienteBarbero);   
    }


    //mostrar lista de clientes 

    public List<ClienteBarbero> mostrarClientes(){
        return clienteBarberoRepository.findAll();
    }

    //obteber por id

    public ClienteBarbero encontrarID(Long id){
        return clienteBarberoRepository.findById(id).orElse(null);
    }


    //eliminar cliente barbero
    public void eliminarClienteBarbero(Long id){
        clienteBarberoRepository.deleteById(id);
    }




}
