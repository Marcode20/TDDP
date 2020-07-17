package com.tddp.controller;

import com.tddp.model.*;
import com.tddp.service.*;
import org.apache.tomcat.jni.Local;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.time.*;

@Controller
public class AppWeb {


    private final ProductoService productoService;
    private final UserService userService;
    private final SecurityService securityService;
    private final RoleService roleService;
    private final CarritoService carritoService;
    private final CarritoProductoService carritoProductoService;


    public AppWeb(UserService userService, SecurityService securityService, RoleService roleService, ProductoService productoService, CarritoService carritoService, CarritoProductoService carritoProductoService) {
        this.userService = userService;
        this.securityService = securityService;
        this.roleService = roleService;
        this.productoService = productoService;
        this.carritoService = carritoService;
        this.carritoProductoService = carritoProductoService;
    }

    @RequestMapping("/")
    public String defaultHome(Model model){
        return "page/home";
    }

    @RequestMapping("/home")
    public String home(Model model){
        return "page/home";
    }

    @RequestMapping(value = {"/admin"})

    private String defaultAdmin(){
        return "admin/adminLogin";
    }

    @RequestMapping(value = {"/admin/login"})
    private String adminLogin(){
        return "admin/adminLogin";
    }

    @RequestMapping(value = {"/login"})
    private String login(){
        return "page/userLogin";
    }




    @RequestMapping("/admin/menu")
    @PreAuthorize("hasRole('ADMIN')")
    private String menu(){
        return "admin/menu";
    }


    @RequestMapping("/admin/logout")
    private String adminLogout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/admin/login";
    }


    @RequestMapping("/logout")
    private String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/home";
    }

    // desde el link de Login
    @GetMapping("/registration")
    private String registration(Model model){
        model.addAttribute("user", new User());
        return "/page/registration";
    }

    @PostMapping("/registration")
    private String registration(User user){
        Role role = roleService.getRoleById(2);

        List<Role> roles = new ArrayList<>();
        roles.add(role);

        User userUser = new User();
        userUser.setUsername("user");
        userUser.setPassword("user");
        userUser.setPasswordConfirm("user");
        userUser.setId(2);

        userService.save(user, roles);
        //autologin
        securityService.autoLogin(user.getUsername(), user.getPasswordConfirm());

        return "redirect:/home";
    }

//  PAGE





    @RequestMapping("/shop")
    public String Shop(Model model){
        model.addAttribute("listaProducto", productoService.getProductoall());
        return "page/shop";
    }

//    @GetMapping("/admin/producto/edit/{id}")
//    public String editProducto(@PathVariable Integer id, Model model){
//        Producto currentProducto = ProductoService.getProductoById(id);
//        model.addAttribute("producto",currentProducto);
//        System.out.println("edit: " + currentProducto);
//        return "admin/producto-edit";
//    }

//    @PostMapping("/carrito/save")
//    public  String addCarritoSaveMake(Model model, Carrito carrito){
//      carritoService.createCarrito(carrito);
//      return "redirect/:carrito";
//    }


//    @PostMapping("/admin/producto/save")
//    public String productoSave(Producto producto, @RequestParam("file") MultipartFile multipartfile){
//        try {
//            String formatedUniqueImageName = fileService.setUniqueFileName(multipartfile.getOriginalFilename());
//            System.out.println("name : " + formatedUniqueImageName);
//            producto.setImageName(formatedUniqueImageName);
//            String s3ObjectName = "productos/" + formatedUniqueImageName;
//            File file = fileService.convertToFile(multipartfile, s3ObjectName);
//            System.out.println("s3ObjectName : " + s3ObjectName);
//            String imageURL = awss3Service.uploadObject(file, s3ObjectName);
//            producto.setProductImageURL(imageURL);
//            ProductoService.createProducto(producto);
//        }
//        catch (IOException ex){
//            ex.printStackTrace();
//        }
//        return "redirect:/admin/producto";
//    }





    @RequestMapping("/about")
    public String About(Model model){
        model.addAttribute("about");
        return "page/about";
    }
    @RequestMapping("/wishlist")
    public String WishList(Model model){
        model.addAttribute("wishlist");
        return "page/wishlist";
    }
    @RequestMapping("/prodsingle")
    public String ProdSingle(Model model){
        model.addAttribute("prodsingle");
        return "page/product-single";
    }

    @RequestMapping("/checkout")
    public String Checkout(Model model){
        model.addAttribute("checkout");
        return "page/checkout";
    }
    @RequestMapping("/contact")
    public String Contact(Model model){
        model.addAttribute("contact");
        return "page/contact";
    }
//    @RequestMapping("/gra")
//    public String Graficos(Model model){
//        model.addAttribute("graf");
//        return "page/graficos";
//    }

//    @PostMapping("/registration")
//    private String registration(User user){
//        userService.save(user);
//        //autologin
//        securityService.autoLogin(user.getUsername(), user.getPasswordConfirm());
//
//        return "redirect:/menu";
//    }

}
