package com.tddp.controller;

import com.tddp.model.Carrito;
import com.tddp.model.CarritoProducto;
import com.tddp.model.Producto;
import com.tddp.service.CarritoService;
import com.tddp.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarritoController {

    @Autowired
    CarritoService carritoService;

//    @GetMapping("/carrito")
//    public String listCarrito(Model model){
//        model.addAttribute("carritos",carritoService.GetCarritoALL());
//        return "carrito";
//    }
//
    @GetMapping("/carrito/add")
    public  String addCarrito(Model model){
        model.addAttribute("addcarrito",new Carrito());
        return "add-carrito";
    }

//    @PostMapping("/carrito/save")
//    public  String addCarritoSaveMake(Model model, Carrito carrito){
//      carritoService.createCarrito(carrito);
//      return "redirect/:carrito";
//    }

//    @PostMapping("/cart/ordenCompra")
//    public String cartOrdenCompra(@PathVariable Integer id, Producto producto){
//        System.out.println("Update: " + producto);
//        //producto.setImageName(producto.getImageMultipartFile().getOriginalFilename());
//        producto.setProducto_id(id);
//        ProductoService.updateProducto(producto);
//
//        return "redirect:/admin/producto";
//    }


//    @RequestMapping("/shop")
//    public String Shop(Model model){
//        model.addAttribute("listaProducto", productoService.getProductoall());
//        return "page/shop";
//    }

    @RequestMapping("/cart")
    public String Cart(Model model){
        Carrito carrito = carritoService.findActiveCarrito();
        Double subtotal = 0.0;

        if(carrito == null){
            return "page/cart";
        }else {
            List<CarritoProducto> carritoProductoList =  carrito.getCarritoProducto();
            List<Producto> productoList = new ArrayList<Producto>();
            for(CarritoProducto carritoProducto: carritoProductoList){
                productoList.add(carritoProducto.getProducto());
                subtotal = subtotal + carritoProducto.getProducto().getPrecio();
            }
            model.addAttribute("productList", productoList);
            model.addAttribute("subtotal", subtotal );
            Double total = subtotal-3.0;
            model.addAttribute("total", total);
            return "page/cart";
        }


    }


}
