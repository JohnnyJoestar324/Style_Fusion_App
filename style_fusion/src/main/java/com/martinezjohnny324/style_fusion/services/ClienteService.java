package com.martinezjohnny324.style_fusion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.martinezjohnny324.style_fusion.models.Cliente;
import com.martinezjohnny324.style_fusion.models.Users;
import com.martinezjohnny324.style_fusion.models.dto.ClienteDto;
import com.martinezjohnny324.style_fusion.models.mapper.Mapper;
import com.martinezjohnny324.style_fusion.repositories.ClienteRepository;
import com.martinezjohnny324.style_fusion.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
    
    private final  ClienteRepository clienteRepository;
    private final UserRepository userRepository;

    

    public ClienteService(ClienteRepository clienteRepository, UserRepository userRepository) {
        this.clienteRepository = clienteRepository;
        this.userRepository = userRepository;
    }



    public List<ClienteDto>listarClientes(){

        Mapper mp = new Mapper();
        List<Cliente> c = clienteRepository.findAll();
        return c.stream().map(cDto -> mp.EntityToDto(cDto)).collect(Collectors.toList());

    }



    public ClienteDto guardarCliente(ClienteDto cDto){
        Mapper mp = new Mapper();
        Cliente c = mp.DtoToEntity(cDto);
        if(c.getFechaRegistro()==null){
            c.setFechaRegistro(LocalDate.now());
        }
        Cliente c_save = clienteRepository.save(c);
        return mp.EntityToDto(c_save);
    }


    public ClienteDto obtenerClientePorId(Long id) {
     Mapper mp = new Mapper();
     Cliente c = clienteRepository.findById(id).orElse(null);
     ClienteDto cdDto = mp.EntityToDto(c);
     return cdDto;

}

    @Transactional 
    public Optional<Cliente> borrarCliente(Long idCliente) {
    Optional<Cliente> cliente = clienteRepository.findById(idCliente);
    cliente.ifPresent(cl -> {
        Users usuario = cl.getUser();

        if(usuario!=null){
            usuario.setCliente(null);
            cl.setUser(null);
        }

        clienteRepository.delete(cl);
        clienteRepository.flush();

        if (usuario!=null) {
            userRepository.delete(usuario);
            
        }
    });

    return  cliente;
}



     

}
