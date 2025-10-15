package com.martinezjohnny324.style_fusion.models;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "membresia_id", nullable = false)
    private Membresia membresia;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user; 


    // 🔹 Constructor vacío (requerido por JPA)
    public Cliente() {
    }

    // 🔹 Constructor con parámetros
    public Cliente(String nombre, String apellido, String email, Membresia membresia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.membresia = membresia;
    }

    // 🔹 Getters y Setters
    public Long getId() {
        return id;
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

    public Membresia getMembresia() {
        return membresia;
    }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }

    public Users getUser() {
    return user;
    }

    public void setUser(Users user) {
    this.user = user;
    }
}
