package com.tddp.controller;

import com.tddp.model.Carrito;
import com.tddp.model.CarritoProducto;
import com.tddp.model.OrdenCompra;
import com.tddp.model.Producto;
import com.tddp.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrdenCompraController {

    final OrdenCompraService ordenCompraService;

    final CarritoService carritoService;

    final AWSS3Service awss3Service;

    final AWSSQSService awssqsService;

    final FileService fileService;

    public OrdenCompraController(OrdenCompraService ordenCompraService, AWSS3Service awss3Service, FileService fileService, AWSSQSService awssqsService, CarritoService carritoService) {
        this.ordenCompraService = ordenCompraService;
        this.awss3Service = awss3Service;
        this.fileService = fileService;
        this.awssqsService = awssqsService;
        this.carritoService = carritoService;
    }

    @GetMapping("/ordenCompra")
    public String getAllOrdenCompra(Model model){
        model.addAttribute("listaOrdenCompra", ordenCompraService.GetOrdenCompra());

        //model.addAttribute("productnew", new Producto());
        return "ordenCompra" ;
    }

    @GetMapping("/ordenCompra/add")
    public String ordenCompraAdd(Model model){
        model.addAttribute("ordenCompra", new OrdenCompra());
        model.addAttribute("listaCarritos", carritoService.GetCarritoALL());
        return "ordenCompra-add";
    }

    @GetMapping("/cart/ordenCompra/save")
    public String ordenCompraSave( ){
        LocalDateTime ldt = LocalDateTime.now();


        Carrito activeCarrito = carritoService.findActiveCarrito();
        Double subtotal = 0.0;
        if(activeCarrito == null){
            return "page/cart";
        }else {

//            OrdenCompra ordenCompra = new OrdenCompra();
//            ordenCompra.setFechaOrden(ldt);
//            ordenCompra.setCarrito(activeCarrito);


            List<CarritoProducto> carritoProductoList =  activeCarrito.getCarritoProducto();
            List<Producto> productoList = new ArrayList<Producto>();
            for(CarritoProducto carritoProducto: carritoProductoList){
                productoList.add(carritoProducto.getProducto());
                subtotal = subtotal + carritoProducto.getProducto().getPrecio();
            }
            Double total = subtotal-3.0;
           // ordenCompra.setTotal(total);

            String formatedUniqueOrdenName = fileService.setUniqueFileName("OrdenCompra");
            System.out.println("name : " + formatedUniqueOrdenName);
            String s3ObjectName = "ordenCompra/" + formatedUniqueOrdenName;
            //File file = fileService.createFile(ordenCompra.toString(), s3ObjectName);
         //   ordenCompra.setFileS3Key(s3ObjectName);

            OrdenCompra ordenCompra = new OrdenCompra(ldt, s3ObjectName, total, activeCarrito);

            OrdenCompra ordenCompra1 = ordenCompraService.createOrdenCompra(ordenCompra);

            System.out.println("s3ObjectName : " + s3ObjectName);
            //String imageURL = awss3Service.uploadObject(file, s3ObjectName);
            awssqsService.sendToQueue(ordenCompra1.toString(), s3ObjectName);

            LocalDateTime localDateTime = LocalDateTime.now();

            ordenCompra.setFechaOrden(localDateTime);

            ordenCompraService.createOrdenCompra(ordenCompra);

        return "redirect:/ordenCompraRealizada";
        }
    }

}
