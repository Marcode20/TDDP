package com.tddp.controller;

import com.tddp.model.Carrito;
import com.tddp.model.OrdenCompra;
import com.tddp.model.Producto;
import com.tddp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

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

    @PostMapping("/ordenCompra/save")
    public String ordenCompraSave(OrdenCompra ordenCompra){

            String formatedUniqueOrdenName = fileService.setUniqueFileName("OrdenCompra");
            System.out.println("name : " + formatedUniqueOrdenName);
            String s3ObjectName = "ordenCompra/" + formatedUniqueOrdenName;
            //File file = fileService.createFile(ordenCompra.toString(), s3ObjectName);
            ordenCompra.setFileS3Key(s3ObjectName);
            System.out.println("s3ObjectName : " + s3ObjectName);
            //String imageURL = awss3Service.uploadObject(file, s3ObjectName);
            awssqsService.sendToQueue(ordenCompra.toString(), s3ObjectName);
            ordenCompraService.createOrdenCompra(ordenCompra);

        return "redirect:/ordenCompraRealizada";
    }
}
