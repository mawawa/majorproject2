package com.waw.majorproject2.controllers;


import com.waw.majorproject2.models.WawUser;
import com.waw.majorproject2.services.WawUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @Autowired
    HomeController homeController;
    @Autowired
    WawUsersService wawUsersService;

    @GetMapping("/admin-home")
    public ModelAndView adminHome(){
        if(wawUsersService.getLoggedInUser() == null)
            return homeController.login();
        ModelAndView modelAndView = new ModelAndView("/admin/admin-home.html");
        modelAndView.getModel().put("loggedInUser",wawUsersService.getLoggedInUser());
        return modelAndView;
    }
    @GetMapping("/admin-dashboard")
    public ModelAndView adminDashboard(){
        ModelAndView modelAndView = new ModelAndView("/admin/admin-dashboard.html");
        modelAndView.getModel().put("loggedInUser",wawUsersService.getLoggedInUser());
        return modelAndView;
    }
}
