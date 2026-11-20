package com.martinezjohnny324.style_fusion.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping; 
import com.martinezjohnny324.style_fusion.models.dto.MembresiaDto;
import com.martinezjohnny324.style_fusion.models.dto.ReporteDto;
import com.martinezjohnny324.style_fusion.services.MembresiaService;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/membresia")
public class MembresiaController {

    private final MembresiaService membresiaService;

    public MembresiaController(MembresiaService membresiaService){
        this.membresiaService = membresiaService;
    }


   @GetMapping("")
   public String home() {
       return "membresia/membresia";
   }
   
   @GetMapping("/membresias_disponibles")
   public String membresiasDisponibles(Model model){
        List<MembresiaDto> memb = membresiaService.listarMembresias();
        model.addAttribute("Lista_Membresia", memb);
        return "membresia/membresias_disponibles";
   }

   @GetMapping("/reportes_membresias")
   public String reportesMembresia(Model model){
    List<ReporteDto> report = membresiaService.ReporteIngresos();
    BigDecimal totalGerneral = membresiaService.calcularTotalGeneral(report);

    model.addAttribute("reporte", report);
    model.addAttribute("totalGeneral", totalGerneral);

    return "membresia/reportes_membresias";
   }

   @GetMapping("/reportes_por_fechas")
   public String reportesPorFecha(Model model, LocalDate in, LocalDate fn){
    List<ReporteDto> report = membresiaService.ReporteIngresosPorFecha(in, in);
    BigDecimal totalGerneral = membresiaService.calcularTotalGeneral(report);

    model.addAttribute("reporte", report);
    model.addAttribute("totalGeneral", totalGerneral);

    return  "membresia/reportes_por_fechas";
   }



}
