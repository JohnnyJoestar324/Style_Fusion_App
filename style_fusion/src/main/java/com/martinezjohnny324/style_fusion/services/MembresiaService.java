package com.martinezjohnny324.style_fusion.services;

import java.util.List;

import com.martinezjohnny324.style_fusion.models.Membresia;
import com.martinezjohnny324.style_fusion.repositories.MembresiaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembresiaService {

    @Autowired
    private MembresiaRepository membresiaRepository;

    public Membresia obtenerMembresiaPorId(Long id) {
    return membresiaRepository.findById(id).orElse(null);
}


    public List<Membresia> listarMembresias() {
        return membresiaRepository.findAll();
    }


    public Membresia guardarMembresia(Membresia membresia) {
        return membresiaRepository.save(membresia);
    }

}
