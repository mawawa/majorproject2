package com.waw.majorproject2.controllers;

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
    WawUsersService wawUsersService;
    @GetMapping("/Dashboard")
    public ModelAndView dashboard(@ModelAttribute WawUser wawUser){
        wawUsersService = new WawUsersService();
        this.wawUsersService.setLoggedInUser(wawUser);
        ModelAndView modelAndView;
        switch (wawUsersService.getLoggedInUser().getRole()){
            case "administrator":
                modelAndView = new ModelAndView("admin/admin-dashboard");
                modelAndView.getModel().put("loggedInUser",wawUsersService.getLoggedInUser());
                return modelAndView;
            case "farmer":
                modelAndView = new ModelAndView("farmer-dashboard.html");
                modelAndView.getModel().put("loggedInUser",wawUsersService.getLoggedInUser());
                return modelAndView;
            case "researcher":
                modelAndView = new ModelAndView("researcher-dashboard.html");
                modelAndView.getModel().put("loggedInUser", wawUsersService.getLoggedInUser());
                return modelAndView;
            case "officer":
                modelAndView = new ModelAndView("officer-dashboard.html");
                modelAndView.getModel().put("loggedInUser", wawUsersService.getLoggedInUser());
                return modelAndView;
        }
        return new HomeController().home();

    }

}
