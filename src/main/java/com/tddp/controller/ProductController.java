package com.tddp.controller;


import com.tddp.model.Producto;
import com.tddp.service.AWSS3Service;
import com.tddp.service.FileService;
import com.tddp.service.ProductoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
public class ProductController {

    final
    ProductoService ProductoService;

    final
    AWSS3Service awss3Service;

    final FileService fileService;

    public ProductController(ProductoService ProductoService, AWSS3Service awss3Service, FileService fileService) {
        this.ProductoService = ProductoService;
        this.awss3Service = awss3Service;
        this.fileService = fileService;
    }

    @GetMapping("/admin/producto")
    public String getAllProducto(Model model){
        model.addAttribute("listaProducto", ProductoService.getProductoall());
        //model.addAttribute("productnew", new Producto());
        return "admin/producto" ;
    }

    @GetMapping("/admin/producto/edit/{id}")
    public String editProducto(@PathVariable Integer id, Model model){
        Producto currentProducto = ProductoService.getProductoById(id);
        model.addAttribute("producto",currentProducto);
        System.out.println("edit: " + currentProducto);
        return "admin/producto-edit";
    }

    @PostMapping("/admin/producto/update/{id}")
    public String updateMoto(@PathVariable Integer id, Producto producto){
        System.out.println("Update: " + producto);
        //producto.setImageName(producto.getImageMultipartFile().getOriginalFilename());
        producto.setProducto_id(id);
        ProductoService.updateProducto(producto);

        return "redirect:/admin/producto";
    }

    @GetMapping("/admin/producto/delete/{id}")
    public String deleteProducto(@PathVariable Integer id){
        this.ProductoService.deleteProducto(id);
        return "redirect:/admin/producto";
    }

    @GetMapping("/admin/producto/add")
    public String productoAdd(Model model){
        model.addAttribute("producto", new Producto());
        return "admin/producto-add";
    }
    @PostMapping("/admin/producto/save")
    public String productoSave(Producto producto, @RequestParam("file") MultipartFile multipartfile){
        try {
            String formatedUniqueImageName = fileService.setUniqueFileName(multipartfile.getOriginalFilename());
            System.out.println("name : " + formatedUniqueImageName);
            producto.setImageName(formatedUniqueImageName);
            String s3ObjectName = "productos/" + formatedUniqueImageName;
            File file = fileService.convertToFile(multipartfile, s3ObjectName);
            System.out.println("s3ObjectName : " + s3ObjectName);
            String imageURL = awss3Service.uploadObject(file, s3ObjectName);
            producto.setProductImageURL(imageURL);
            ProductoService.createProducto(producto);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return "redirect:/admin/producto";
    }


}
