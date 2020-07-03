package com.tddp.resource;

import com.tddp.model.Producto;
import com.tddp.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductoResource {

    @Autowired
    ProductoService productoService;

    @GetMapping("/producto")
    public ResponseEntity getAllProducto(){
        List<Producto> productos = productoService.getProductoall();
        if(productos == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(productos, HttpStatus.OK);
    }



    @GetMapping("/producto/{id}")
    public ResponseEntity updateProducto(@PathVariable Integer id, @RequestBody Producto producto){
        Producto currentProducto = productoService.getProductoById(id);
        if(currentProducto == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        producto.setProducto_id(id);
        productoService.updateProducto(producto);

        return new ResponseEntity(producto, HttpStatus.OK);
    }

//    @PostMapping("/producto/update/{id}")
//    public String updateMoto(@PathVariable Integer id, Producto producto){
//        System.out.println(producto);
//        producto.setImageName(producto.getImageMultipartFile().getOriginalFilename());
//        producto.setProducto_id(id);
//        ProductoService.updateProducto(producto);
//
//        return "redirect:/producto";
//    }
//
//    @GetMapping("producto/delete/{id}")
//    public String deleteProducto(@PathVariable Integer id){
//        this.ProductoService.deleteProducto(id);
//        return "redirect:/producto";
//    }
//
//    @GetMapping("/producto/add")
//    public String productoAdd(Model model){
//        model.addAttribute("producto", new Producto());
//        return "producto-add";
//    }
//    @PostMapping("/producto/save")
//    public String productoSave(Producto producto, @RequestParam("file") MultipartFile multipartfile){
////        new File(String.format("%s.%s", sdf.format( new Date() ),
////                random.nextInt(9)));
//        new SimpleDateFormat("File-ddMMyy-hhmmss.SSS.txt")
//        String name = String.format("%s, %s", sdf.)
//        producto.setProductImageURL(multipartfile.getOriginalFilename());
//        producto.setImageMultipartFile(multipartfile);
//        System.out.println("a");
//        ProductoService.createProducto(producto);
//        return "redirect:/producto";
//    }
}

