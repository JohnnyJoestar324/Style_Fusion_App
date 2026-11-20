package com.martinezjohnny324.style_fusion.services;
import java.math.BigDecimal;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.martinezjohnny324.style_fusion.models.Membresia;
import com.martinezjohnny324.style_fusion.models.dto.MembresiaDto;
import com.martinezjohnny324.style_fusion.models.dto.ReporteDto;
import com.martinezjohnny324.style_fusion.models.mapper.Mapper;
import com.martinezjohnny324.style_fusion.repositories.MembresiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MembresiaService {

    @Autowired
    private MembresiaRepository membresiaRepository;
    private Mapper mp;

    public MembresiaDto obtenerMembresiaPorId(Long id) {
    mp = new Mapper();
    Membresia m = membresiaRepository.findById(id).orElse(null);
    MembresiaDto mDto = mp.EntityToDto(m);

    return mDto;
    }


    public List<MembresiaDto> listarMembresias() {
        mp = new Mapper();
        List<Membresia> m =  membresiaRepository.findAll();
        
        return m.stream().map(mDto -> mp.EntityToDto(mDto)).collect(Collectors.toList());

    }


    public MembresiaDto guardarMembresia(MembresiaDto mDto){
        mp = new Mapper();
        Membresia m = mp.DtoToEntity(mDto);
        Membresia m_save = membresiaRepository.save(m);
        
        return mp.EntityToDto(m_save);
        
    }

    public  List<ReporteDto>ReporteIngresos(){
       return membresiaRepository.obtenerReportePorPlan();

    }


    public BigDecimal calcularTotalGeneral(List<ReporteDto> reporte) {
        return reporte.stream()
                .map(ReporteDto::getTotalRecaudado)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<ReporteDto> ReporteIngresosPorFecha(LocalDate fInicio, LocalDate fFinal){
        LocalDate inicio = (fInicio !=null) ? fInicio : LocalDate.now().withDayOfMonth(1);
        LocalDate fin = (fFinal !=null) ? fFinal : LocalDate.now();

        return  membresiaRepository.obtenerReportePorFecha(inicio, fin);

    }


}
