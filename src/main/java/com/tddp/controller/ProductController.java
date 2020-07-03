package com.tddp.controller;


import com.tddp.model.Producto;
import com.tddp.service.ProductoService;
import com.tddp.service.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;

@Controller
public class ProductController {

    @Autowired
    ProductoService ProductoService;

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
//        new File(String.format("%s.%s", sdf.format( new Date() ),
//                random.nextInt(9)));
        new SimpleDateFormat("File-ddMMyy-hhmmss.SSS.txt");
     //   String name = String.format("%s, %s", sdf.)
        producto.setProductImageURL(multipartfile.getOriginalFilename());
        producto.setImageMultipartFile(multipartfile);
        System.out.println("a");
        ProductoService.createProducto(producto);
        return "redirect:/producto";
    }


}
