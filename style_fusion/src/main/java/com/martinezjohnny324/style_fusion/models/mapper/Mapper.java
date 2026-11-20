package com.martinezjohnny324.style_fusion.models.mapper;

import com.martinezjohnny324.style_fusion.models.Cliente;
import com.martinezjohnny324.style_fusion.models.Membresia;
import com.martinezjohnny324.style_fusion.models.Users;
import com.martinezjohnny324.style_fusion.models.dto.ClienteDto;
import com.martinezjohnny324.style_fusion.models.dto.MembresiaDto;
import com.martinezjohnny324.style_fusion.models.dto.UsersDto;
import com.martinezjohnny324.style_fusion.repositories.MembresiaRepository;
import com.martinezjohnny324.style_fusion.repositories.UserRepository;

import java.util.stream.Collectors;


public class Mapper {
    private MembresiaRepository mRepository;
    private UserRepository uRepository;

    

        

        public MembresiaDto EntityToDto(Membresia memb){
        MembresiaDto mDto = new MembresiaDto();
        mDto.setId(memb.getId());
        mDto.setNombrePlan(memb.getNombrePlan());
        mDto.setDescripcion(memb.getDescripcion());
        mDto.setPrecio(memb.getPrecio());
        mDto.setcDto(memb.getClientes() != null 
                        ? memb.getClientes().stream().map(this::EntityToDto).collect(Collectors.toList())
                        : null
                        );
        
        return  mDto;
    }

    public Membresia DtoToEntity (MembresiaDto mDto){
        Membresia m = new Membresia();
        m.setId(mDto.getId());
        m.setNombrePlan(mDto.getNombrePlan());
        m.setDescripcion(mDto.getDescripcion());
        m.setPrecio(mDto.getPrecio());
        m.setClientes(mDto.getcDto() != null
                        ? mDto.getcDto().stream().map(this::DtoToEntity).collect(Collectors.toList())
                    : null
                    );
                    
        return m;
    }



    public ClienteDto EntityToDto(Cliente cliente){
        ClienteDto cDto = new ClienteDto();
        cDto.setId(cliente.getId());
        cDto.setNombre(cliente.getNombre());
        cDto.setApellido(cliente.getApellido());
        cDto.setEmail(cliente.getEmail());
        cDto.setFechaRegistro(cliente.getFechaRegistro());
        if(cliente.getMembresia()!=null){
            cDto.setId_Membresia(cliente.getMembresia().getId());
            cDto.setNombrePlan(cliente.getMembresia().getNombrePlan());
        }
        if(cliente.getUser()!=null){
            cDto.setId_user(cliente.getUser().getId());
        }
        return  cDto;

    }

    public Cliente DtoToEntity(ClienteDto cDto){
        Cliente c = new Cliente(); 
        c.setId(cDto.getId());
        c.setNombre(cDto.getNombre());
        c.setApellido(cDto.getApellido());
        c.setEmail(cDto.getEmail());
        c.setFechaRegistro(cDto.getFechaRegistro());
        if(cDto.getId_Membresia() != null){
            Membresia m = new Membresia();
            m.setId(cDto.getId_Membresia());
        c.setMembresia(m);
        }
        
        if(cDto.getId_user() != null){
            Users u = new Users();
            u.setId(cDto.getId_user());
        c.setUser(u);    
        } 

        return c;
    }



    public UsersDto EntityToDto(Users u){
        UsersDto uDto = new UsersDto();
        uDto.setId(u.getId());
        uDto.setName(u.getName());
        uDto.setUsername(u.getUsername());
        uDto.setEmail(u.getEmail());
        uDto.setPassword(u.getPassword());
        uDto.setRole(u.getRole());
        uDto.setCreatedAt(u.getCreatedAt());
        uDto.setcDto(u.getCliente() != null ? EntityToDto(u.getCliente()) : null);

        return uDto;
    }

    public Users DtoToEntity(UsersDto uDto){
        Users u = new Users();
        u.setId(uDto.getId());
        u.setName(uDto.getName());
        u.setUsername(uDto.getUsername());
        u.setEmail(uDto.getEmail());
        u.setPassword(uDto.getPassword());
        u.setRole(uDto.getRole());
        u.setCreatedAt(uDto.getCreatedAt());
        u.setCliente(uDto.getcDto() != null ? DtoToEntity(uDto.getcDto()) :null);

        return  u;

    }

}
