package com.martinezjohnny324.style_fusion.models.dto;

import com.martinezjohnny324.style_fusion.models.Users;

public class ClienteDto {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private MembresiaDto mDto;
    private Users users;

    


    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public ClienteDto() {
    }

    public MembresiaDto getmDto() {
        return mDto;
    }
    public void setmDto(MembresiaDto mDto) {
        this.mDto = mDto;
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
