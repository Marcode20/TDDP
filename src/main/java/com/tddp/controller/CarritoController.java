package com.tddp.controller;

import com.tddp.model.Carrito;
import com.tddp.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarritoController {

    @Autowired
    CarritoService carritoService;

    @GetMapping("/carrito")
    public String listCarrito(Model model){
        model.addAttribute("carritos",carritoService.GetCarritoALL());
        return "carrito";
    }

    @GetMapping("/carrito/add")
    public  String addCarrito(Model model){
        model.addAttribute("addcarrito",new Carrito());
        return "add-carrito";
    }

    @PostMapping("/carrito/save")
    public  String addCarritoSaveMake(Model model, Carrito carrito){
      carritoService.createCarrito(carrito);
      return "redirect/:carrito";
    }


}
