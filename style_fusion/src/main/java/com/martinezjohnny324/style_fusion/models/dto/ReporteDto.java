package com.martinezjohnny324.style_fusion.models.dto;

import java.math.BigDecimal;

public class ReporteDto {
    private String nombrePlan;
    private Long cantidadVendida;
    private BigDecimal totalRecaudado;

    public ReporteDto(String nombrePlan, Long cantidadVendida, BigDecimal totalRecaudado) {
        this.nombrePlan = nombrePlan;
        this.cantidadVendida = cantidadVendida;
        this.totalRecaudado = totalRecaudado;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public Long getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(Long cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public BigDecimal getTotalRecaudado() {
        return totalRecaudado;
    }

    public void setTotalRecaudado(BigDecimal totalRecaudado) {
        this.totalRecaudado = totalRecaudado;
    }

    
    
}
