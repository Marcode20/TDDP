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

    @GetMapping("marca")
    public String getAllMarca(Model model){
        model.addAttribute("marcas", marcaService.GetMarcaAll());

        return "marca";
    }

    @GetMapping("marca/add")
    public String marcaAdd(Model model){
        model.addAttribute("marca", new Marca());
        return "marca-add.html";
    }

    @PostMapping("marca/save")
    public String marcaSave(Marca marca){
        marcaService.createMarca(marca);
        return "redirect:/marca";
    }

    @GetMapping("marca/edit/{id}")
    public String marcaEdit(@PathVariable Integer id, Model model){
        Marca currentMarca = marcaService.GetMarcaById(id);
        model.addAttribute("marca", currentMarca);
        return "marca-edit.html";
    }

    @PostMapping("marca/update/{id}")
    public String updateMarca(@PathVariable Integer id, Marca marca){
        marca.setMarca_id(id);
        marcaService.updateMarca(marca);
        return "redirect:/marca";
    }

    @GetMapping("marca/delete/{id}")
    public String deleteMarca(@PathVariable Integer id){
        marcaService.deleteMarca(id);
        return "redirect:/marca";
    }
}
