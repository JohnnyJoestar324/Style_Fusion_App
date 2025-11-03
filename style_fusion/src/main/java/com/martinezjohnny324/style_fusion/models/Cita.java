package com.martinezjohnny324.style_fusion.models;

import jakarta.persistence.*;

@Entity
@Table(name = "cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    



}
