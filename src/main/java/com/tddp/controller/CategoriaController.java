package com.tddp.controller;

import com.tddp.model.Categoria;
import com.tddp.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoriaController {

    private final CategoriaService categoriaService;


    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/admin/categoria")
    public String getAllCategorias(Model model){
        model.addAttribute("listacategoria", categoriaService.getCategoriaAll());

        return "/admin/categoria";
    }



    @GetMapping("/admin/categoria/add")
    public String categoriaAdd(Model model){
        model.addAttribute("categoria", new Categoria());
        return "/admin/categoria-add";
    }

    @PostMapping("/admin/categoria/save")
    public String categoriaSave(Categoria categoria){
        categoriaService.createCategoria(categoria);
        return "redirect:/admin/categoria";
    }

    @GetMapping("/admin/categoria/edit/{id}")
    public String categoriaEdit(@PathVariable Integer id, Model model){
        Categoria currentCategoria = categoriaService.getCategoriaById(id);
        model.addAttribute("categoria", currentCategoria);
        return "/admin/categoria-edit";
    }

    @PostMapping("/admin/categoria/update/{id}")
    public String updateCategoria(@PathVariable Integer id, Categoria categoria){
        categoria.setCategoria_id(id);
        categoriaService.updateCategoria(categoria);
        return "redirect:/admin/categoria";
    }

    @GetMapping("/admin/categoria/delete/{id}")
    public String deleteCategoria(@PathVariable Integer id){
        categoriaService.deleteCategoria(id);
        return "redirect:/admin/categoria";
    }

}



