package com.martinezjohnny324.style_fusion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.martinezjohnny324.style_fusion.models.Cliente;
import com.martinezjohnny324.style_fusion.repositories.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente>listarClientes(){
        return clienteRepository.findAll();

    }

    public Cliente guardarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

}
