package com.martinezjohnny324.style_fusion.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.martinezjohnny324.style_fusion.models.dto.MembresiaDto;
import com.martinezjohnny324.style_fusion.models.dto.ReporteDto;
import com.martinezjohnny324.style_fusion.services.MembresiaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



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
   public String reportesPorFecha(Model model, @RequestParam(required = false) @DateTimeFormat (iso = DateTimeFormat.ISO.DATE)LocalDate fechaInicio,
                                    @RequestParam(required = false) @DateTimeFormat (iso = DateTimeFormat.ISO.DATE)LocalDate fechaFinal){
    List<ReporteDto> report = membresiaService.ReporteIngresosPorFecha(fechaInicio, fechaFinal);
    BigDecimal totalGerneral = membresiaService.calcularTotalGeneral(report);

    model.addAttribute("reporte", report);
    model.addAttribute("fechaInicio", fechaInicio);
    model.addAttribute("fechaFinal", fechaFinal);
    model.addAttribute("totalGeneral", totalGerneral);

    return  "membresia/reportes_por_fechas";
   }

   @GetMapping("/editar_membresia/{id}")
   public String formularioMembresia(Model model, @PathVariable("id") Long id){
    MembresiaDto mDto = membresiaService.obtenerMembresiaPorId(id);
    if (mDto == null) {
        throw new RuntimeException("Membresia no encontrada: " + id);
    }
    model.addAttribute("membresia", mDto);
    return "membresia/editar_membresia";
   }

   @PostMapping("/editar_membresia")
   public String editarCliente (@ModelAttribute ("membresia") MembresiaDto mDto){

    if (mDto != null) {
        
        MembresiaDto existente = membresiaService.obtenerMembresiaPorId(mDto.getId());

        if (existente == null) {
            throw new RuntimeException("Cliente no encontrado con ID: " + mDto.getId());
        }

        existente.setId(mDto.getId());
        existente.setDescripcion(mDto.getDescripcion());
        existente.setPrecio(mDto.getPrecio());

        membresiaService.guardarMembresia(existente);

    }
    
    return "redirect:/membresia/membresias_disponibles";

   }


}
