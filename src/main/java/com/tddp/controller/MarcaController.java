package com.tddp.controller;

import com.tddp.model.Marca;
import com.tddp.service.MarcaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MarcaController {
    private final MarcaService marcaService;


    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping("/admin/marca")
    public String getAllMarca(Model model){
        model.addAttribute("listamarca", marcaService.GetMarcaAll());

        return "admin/marca";
    }

    @GetMapping("/admin/marca/add")
    public String marcaAdd(Model model){
        model.addAttribute("marca", new Marca());
        return "admin/marca-add";
    }

    @PostMapping("/admin/marca/save")
    public String marcaSave(Marca marca){
        marcaService.createMarca(marca);
        return "redirect:/admin/marca";
    }

    @GetMapping("/admin/marca/edit/{id}")
    public String marcaEdit(@PathVariable Integer id, Model model){
        Marca currentMarca = marcaService.GetMarcaById(id);
        model.addAttribute("marca", currentMarca);
        return "/admin/marca-edit";
    }

    @PostMapping("/admin/marca/update/{id}")
    public String updateMarca(@PathVariable Integer id, Marca marca){
        marca.setMarca_id(id);
        marcaService.updateMarca(marca);
        return "redirect:/admin/marca";
    }

    @GetMapping("/admin/marca/delete/{id}")
    public String deleteMarca(@PathVariable Integer id){
        marcaService.deleteMarca(id);
        return "redirect:/admin/marca";
    }
}
