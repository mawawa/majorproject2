package com.waw.majorproject2.controllers;

import com.waw.majorproject2.models.WawUser;
import com.waw.majorproject2.services.WawUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {
    @Autowired
    WawUsersService wawUsersService;


    @GetMapping("/wawusers")
    public ModelAndView manageWawUsers(){
        ModelAndView modelAndView = new ModelAndView("manage-wawusers.html");
        modelAndView.getModel().put("wawUsers", wawUsersService );
        modelAndView.getModel().put("manager", wawUsersService.getLoggedInUser());
        return modelAndView;
    }


    @PostMapping("/submit-registration")
    public ModelAndView submitRegistration(@ModelAttribute WawUser wawUser){
        wawUser = wawUsersService.SaveUpdate(wawUser);
        wawUsersService.setLoggedInUser(wawUser);


        return new DashboardController().dashboard(wawUser);
    }
}
