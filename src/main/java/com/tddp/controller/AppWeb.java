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

    @PostMapping("/registration")
    private String registration(User user){
        userService.save(user);
        //autologin
        securityService.autoLogin(user.getUsername(), user.getPasswordConfirm());

        return "redirect:/menu";
    }
    @RequestMapping("/para")
    public String paradero(Model model){
        model.addAttribute("para");
        return "paradero";
    }

    @RequestMapping("/cont")
    public String contactanos(Model model){
        model.addAttribute("cont");
        return "contactanos";
    }
    @RequestMapping("/consult")
    public String consultas(Model model){
        model.addAttribute("consult");
        return "consultas";
    }
    @RequestMapping("/menuadmins")
    public String menuadmin(Model model){
        model.addAttribute("menuadmins");
        return "menuadmin";
    }
    @RequestMapping("/mans")
    public String menPrincipal(Model model){
        model.addAttribute("mans");
        return "man_principal";
    }

}
