package com.martinezjohnny324.style_fusion.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.martinezjohnny324.style_fusion.models.Membresia;
import com.martinezjohnny324.style_fusion.models.dto.ReporteDto;


public interface MembresiaRepository extends JpaRepository<Membresia, Long> {
                Optional<Membresia> findById(Long id);

    
    @Query("""
        SELECT new com.martinezjohnny324.style_fusion.models.dto.ReporteDto(
            m.nombrePlan,
            COUNT(c.id),
            CAST(COALESCE(COUNT(c.id) * m.precio, 0) AS BigDecimal)
            SELECT 
        )
        FROM Membresia m
        LEFT JOIN m.clientes c
        GROUP BY m.id, m.nombrePlan, m.precio
        ORDER BY COUNT(c.id) DESC
    """)
    List<ReporteDto> obtenerReportePorPlan();


    @Query("""
        SELECT new com.martinezjohnny324.style_fusion.models.dto.ReporteDto(
            m.nombrePlan,
            Count(c.id),
            CAST(COALESCE(COUNT(c.id) * m.precio, 0) AS BigDecimal)
        )
            FROM Membresia m
            LEFT JOIN m.clientes c
                WHERE c.fechaRegistro BETWEEN :fechaInicio AND :fechaFin
            GROUP BY m.id, m.nombrePlan, m.precio
            ORDER BY COUNT(c.id) DESC
        
    
    """)
    List<ReporteDto>obtenerReportePorFecha(@Param ("fechaInicio") LocalDate fechaInicio,
                                            @Param ("fechaFin") LocalDate fechaFin);
}
