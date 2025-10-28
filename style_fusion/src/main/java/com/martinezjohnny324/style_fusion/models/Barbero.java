package com.martinezjohnny324.style_fusion.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "barbero")

public class Barbero {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Column(nullable = false)
private String Nombre;
@Column(nullable = false)
private String Apellido;   
@Column(nullable = false, unique = true)
private Long Numero;


    public Barbero(String nombre, String apellido, Long numero) {
    Nombre = nombre;
    Apellido = apellido;
    Numero = numero;
    }

    public Barbero(){

    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public Long getNumero() {
        return Numero;
    }

    public void setNumero(Long numero) {
        Numero = numero;
    }

    public Long getId() {
        return id;
    }


    





}
