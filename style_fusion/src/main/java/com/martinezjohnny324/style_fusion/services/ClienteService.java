package com.martinezjohnny324.style_fusion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.martinezjohnny324.style_fusion.models.Cliente;
import com.martinezjohnny324.style_fusion.models.dto.ClienteDto;
import com.martinezjohnny324.style_fusion.models.mapper.Mapper;
import com.martinezjohnny324.style_fusion.repositories.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente>listarClientes(){
        return clienteRepository.findAll();

    }



    public ClienteDto guardarCliente(ClienteDto cDto){
        Mapper mp = new Mapper();
        Cliente c = mp.DtoToEntity(cDto);
        Cliente c_save = clienteRepository.save(c);
        return mp.EntityToDto(c_save);
    }


    public ClienteDto obtenerClientePorId(Long id) {
     Mapper mp = new Mapper();
     Cliente c = clienteRepository.findById(id).orElse(null);
     ClienteDto cdDto = mp.EntityToDto(c);
     return cdDto;

}


    public Optional<Cliente> borrarCliente(Long idCliente) {
    Optional<Cliente> cliente = clienteRepository.findById(idCliente);
    cliente.ifPresent(c -> clienteRepository.delete(c)); // borra solo si existe
    return cliente; // devuelve el Optional, puede estar vacío si no existe
}



     

}
