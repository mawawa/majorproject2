package com.waw.majorproject2.controllers;

import com.waw.majorproject2.controllers.admin.AdminController;
import com.waw.majorproject2.models.WawUser;
import com.waw.majorproject2.services.WawUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {
    @Autowired
    AdminController adminController;
    @Autowired
    WawUsersService wawUsersService;
    @GetMapping("/Dashboard")
    public ModelAndView dashboard(@ModelAttribute WawUser wawUser){
        wawUsersService = new WawUsersService();
        this.wawUsersService.setLoggedInUser(wawUser);
        ModelAndView modelAndView;
        switch (wawUsersService.getLoggedInUser().getRole()){
            case "Administrator":
                return adminController.adminDashboard() ;
            case "Farmer":
                modelAndView = new ModelAndView("farmer-dashboard.html");
                modelAndView.getModel().put("loggedInUser",wawUsersService.getLoggedInUser());
                return modelAndView;
            case "Researcher":
                modelAndView = new ModelAndView("researcher-dashboard.html");
                modelAndView.getModel().put("loggedInUser", wawUsersService.getLoggedInUser());
                return modelAndView;
            case "Officer":
                modelAndView = new ModelAndView("officer-dashboard.html");
                modelAndView.getModel().put("loggedInUser", wawUsersService.getLoggedInUser());
                return modelAndView;
        }
        return new HomeController().home();

    }

}
