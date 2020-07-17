package com.tddp.controller;

import com.tddp.model.Carrito;
import com.tddp.model.CarritoProducto;
import com.tddp.model.CarritoProductoKey;
import com.tddp.model.Producto;
import com.tddp.service.CarritoProductoService;
import com.tddp.service.CarritoService;
import com.tddp.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CarritoController {

    @Autowired
    CarritoService carritoService;
    @Autowired
    ProductoService productoService;
    @Autowired
    CarritoProductoService carritoProductoService;

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

//    @GetMapping("/carritoProducto/producto/{id}")
//    public String saveCarritoProducto( @PathVariable Integer id){
//        //System.out.println("prd: " + producto);
//
//        LocalDateTime lt = LocalDateTime.now();
//        Carrito activeCarrito = carritoService.findActiveCarrito();
//        Producto producto = productoService.getProductoById(id);
//        System.out.println("prd: " + producto);
//        if(activeCarrito == null){
//
//            Carrito carrito = new Carrito();
//            carrito.setDate(lt);
//            carrito.setIsActive(1);
//            carritoService.createCarrito(carrito);
//            CarritoProductoKey carritoProductoKey = new CarritoProductoKey(carrito.getCarrito_id(), producto.getProducto_id());
//
//            CarritoProducto carritoProducto = new CarritoProducto(carritoProductoKey, carrito, producto, 1);
//
//            carritoProductoService.createCarritoProducto(carritoProducto);
//        }else{
//
//            CarritoProductoKey carritoProductoKey = new CarritoProductoKey(activeCarrito.getCarrito_id(), producto.getProducto_id());
//
//            CarritoProducto carritoProducto = new CarritoProducto(carritoProductoKey, activeCarrito, producto, 1);
//            carritoProductoService.createCarritoProducto(carritoProducto);
//
//        }
//     return "redirect:/cart";
//    }

    @GetMapping("/carritoProducto/producto/{id}")
    public String saveCarritoProducto(@PathVariable Integer id, Model model, HttpSession httpSession){
        //System.out.println("prd: " + producto);
        if(httpSession.getAttribute("cart") == null){
            List<CarritoProducto> cart = new ArrayList<CarritoProducto>();
            cart.add(new CarritoProducto(productoService.getProductoById(id), 1));
            httpSession.setAttribute("cart", cart);

        }else{
            List<CarritoProducto> cart = (List<CarritoProducto>) httpSession.getAttribute("cart");
            int productExistsPosition = carritoProductoService.carritoProductoExists(id, cart);
            if (productExistsPosition == -1){
                cart.add(new CarritoProducto(productoService.getProductoById(id), 1));
            }else {
                int quantity = cart.get(productExistsPosition).getCantidad() + 1;
                cart.get(productExistsPosition).setCantidad(quantity);
            }
            httpSession.setAttribute("cart", cart);
        }

        return "redirect:/cart";
    }

    @GetMapping("/carritoProducto/producto/delete/{id}")
    public String deleteCarritoProducto(@PathVariable Integer id, HttpSession httpSession){

            List<CarritoProducto> cart = (List<CarritoProducto>) httpSession.getAttribute("cart");
            int productExistsPosition = carritoProductoService.carritoProductoExists(id, cart);
            cart.remove(productExistsPosition);

            httpSession.setAttribute("cart", cart);


        return "redirect:/cart";
    }

    @GetMapping("/carritoProducto/producto/update/{id}")
    public String updateCarritoProducto(@PathVariable Integer id, HttpSession httpSession){

        List<CarritoProducto> cart = (List<CarritoProducto>) httpSession.getAttribute("cart");
        int productExistsPosition = carritoProductoService.carritoProductoExists(id, cart);
        cart.remove(productExistsPosition);

        httpSession.setAttribute("cart", cart);


        return "redirect:/cart";
    }



}
