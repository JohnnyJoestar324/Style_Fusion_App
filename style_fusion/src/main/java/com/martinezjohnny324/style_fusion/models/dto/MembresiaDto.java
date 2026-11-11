package com.martinezjohnny324.style_fusion.models.dto;

import java.util.List;


public class MembresiaDto {

    private Long id;
    private String nombrePlan;
    private String descripcion;
    private double precio;
    private List<ClienteDto> cDto;

    

   
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombrePlan() {
        return nombrePlan;
    }
    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public List<ClienteDto> getcDto() {
        return cDto;
    }
    public void setcDto(List<ClienteDto> cDto) {
        this.cDto = cDto;
    }
   

    


}
