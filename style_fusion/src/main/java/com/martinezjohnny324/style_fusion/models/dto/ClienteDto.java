package com.martinezjohnny324.style_fusion.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ClienteDto {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private Long id_Membresia;
    private String username;
    private Long id_user;
    private String nombrePlan;
     
    
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
    public Long getId_user() {
        return id_user;
    }
    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }
    public String getNombrePlan() {
        return nombrePlan;
    }
    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }
    
    

    
    
    
    
    

    
   
        


}
