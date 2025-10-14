package com.martinezjohnny324.style_fusion.models;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "membresias")
public class Membresia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombrePlan;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private double precio;

    // Relación inversa con Cliente
    @OneToMany(mappedBy = "membresia")
    private List<Cliente> clientes;

    public Membresia() {}

    public Membresia(String nombrePlan, String descripcion, double precio) {
        this.nombrePlan = nombrePlan;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Long getId() {
        return id;
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

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
