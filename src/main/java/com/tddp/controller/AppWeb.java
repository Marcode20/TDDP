package com.tddp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tddp.model.User;
import com.tddp.service.SecurityService;
import com.tddp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AppWeb {

    private final UserService userService;
    private final SecurityService securityService;

    public AppWeb(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }


    @GetMapping(value = {"/login"})
    private String index(){
        return "login";
    }

    @GetMapping("/menu")
    private String menu(){
        return "menu";
    }

    @GetMapping("/logout")
    private String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    // desde el link de Login
    @GetMapping("/registration")
    private String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }
    @RequestMapping("/indexs")
    public String index(Model model){
        model.addAttribute("indexs");
        return "index";
    }

    @RequestMapping("/shops")
    public String Shop(Model model){
        model.addAttribute("shops");
        return "shop";
    }
    @RequestMapping("/abouts")
    public String About(Model model){
        model.addAttribute("abouts");
        return "about";
    }
    @RequestMapping("/wishlists")
    public String WishList(Model model){
        model.addAttribute("wishlists");
        return "wishlist";
    }
    @RequestMapping("/prodsingles")
    public String ProdSingle(Model model){
        model.addAttribute("prodsingles");
        return "product-single";
    }
    @RequestMapping("/carts")
    public String Cart(Model model){
        model.addAttribute("carts");
        return "cart";
    }
    @RequestMapping("/checkouts")
    public String Checkout(Model model){
        model.addAttribute("checkouts");
        return "checkout";
    }
    @RequestMapping("/contacts")
    public String Contact(Model model){
        model.addAttribute("contacts");
        return "contact";
    }

    @PostMapping("/registration")
    private String registration(User user){
        userService.save(user);
        //autologin
        securityService.autoLogin(user.getUsername(), user.getPasswordConfirm());

        return "redirect:/menu";
    }

}
