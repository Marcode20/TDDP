package com.tddp.controller;


import com.tddp.model.Producto;
import com.tddp.service.AWSS3Service;
import com.tddp.service.ProductoService;
import com.tddp.service.ProductoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Slf4j
@Controller
public class ProductController {

    final
    ProductoService ProductoService;

    final
    AWSS3Service awss3Service;

    public ProductController(ProductoService ProductoService, AWSS3Service awss3Service) {
        this.ProductoService = ProductoService;
        this.awss3Service = awss3Service;
    }

    @GetMapping("/producto")
    public String getAllProducto(Model model){
        model.addAttribute("listaProducto", ProductoService.getProductoall());
        //model.addAttribute("productnew", new Producto());
        return "producto" ;
    }

    @GetMapping("/producto/edit/{id}")
    public String editProducto(@PathVariable Integer id, Model model){
        Producto currentProducto = ProductoService.getProductoById(id);
        model.addAttribute("producto",currentProducto);
        return "producto-edit";
    }

    @PostMapping("/producto/update/{id}")
    public String updateMoto(@PathVariable Integer id, Producto producto){
        System.out.println(producto);
        //producto.setImageName(producto.getImageMultipartFile().getOriginalFilename());
        producto.setProducto_id(id);
        ProductoService.updateProducto(producto);

        return "redirect:/producto";
    }

    @GetMapping("producto/delete/{id}")
    public String deleteProducto(@PathVariable Integer id){
        this.ProductoService.deleteProducto(id);
        return "redirect:/producto";
    }

    @GetMapping("/producto/add")
    public String productoAdd(Model model){
        model.addAttribute("producto", new Producto());
        return "producto-add";
    }
    @PostMapping("/producto/save")
    public String productoSave(Producto producto, @RequestParam("file") MultipartFile multipartfile){
        try {
            String formatedUniqueImageName = awss3Service.setUniqueFileName(multipartfile.getOriginalFilename());
            System.out.println("name : " + formatedUniqueImageName);
            producto.setImageName(formatedUniqueImageName);
            String s3ObjectName = "productos/" + formatedUniqueImageName;
            File file = awss3Service.convertToFile(multipartfile, s3ObjectName);
            System.out.println("s3ObjectName : " + s3ObjectName);
            String imageURL = awss3Service.uploadObject(file, s3ObjectName);
            producto.setProductImageURL(imageURL);
            ProductoService.createProducto(producto);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return "redirect:/producto";
    }


}
