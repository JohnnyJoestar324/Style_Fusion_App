package com.martinezjohnny324.style_fusion.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ClienteDto {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private Long id_Membresia;
    private String username;
    @JsonIgnore 
    private MembresiaDto membresiaDto;
    @JsonIgnore 
    private UsersDto usersDto;


    
    
    
    public MembresiaDto getMembresiaDto() {
        return membresiaDto;
    }
    public void setMembresiaDto(MembresiaDto membresiaDto) {
        this.membresiaDto = membresiaDto;
    }
    public UsersDto getUsersDto() {
        return usersDto;
    }
    public void setUsersDto(UsersDto usersDto) {
        this.usersDto = usersDto;
    }
    public Long getId_Membresia() {
        return id_Membresia;
    }
    public void setId_Membresia(Long id_Membresia) {
        this.id_Membresia = id_Membresia;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) { 
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    } 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
      
        


}
