package com.martinezjohnny324.style_fusion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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


    public Cliente obtenerClientePorId(Long id) {
    return clienteRepository.findById(id).orElse(null);
}


    public Optional<Cliente> borrarCliente(Long idCliente) {
    Optional<Cliente> cliente = clienteRepository.findById(idCliente);
    cliente.ifPresent(c -> clienteRepository.delete(c)); // borra solo si existe
    return cliente; // devuelve el Optional, puede estar vacío si no existe
}



     

}
