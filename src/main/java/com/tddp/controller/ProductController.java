package com.tddp.controller;


import com.tddp.model.Producto;
import com.tddp.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @Autowired
    ProductoService productoService;

    @GetMapping("/producto")
    public String getAllChofer(Model model){
        model.addAttribute("productall", productoService.getProductoall());
        model.addAttribute("productnew", new Producto());
        return null ;
    }



}
