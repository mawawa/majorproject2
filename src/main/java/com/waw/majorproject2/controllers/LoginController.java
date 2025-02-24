package com.waw.majorproject2.controllers;


import com.waw.majorproject2.models.WawUser;
import com.waw.majorproject2.services.WawUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @Autowired
    WawUsersService wawUsersService;
    @Autowired
    HomeController homeController;
    @Autowired
    DashboardController dashboardController;

    @PostMapping("/authenticate")
    public ModelAndView authenticate(@ModelAttribute WawUser userLogin){
        String email = userLogin.getEmailAddress();
        String password = userLogin.getPassword();
        wawUsersService.setLoggedInUser(wawUsersService.login(email, password));
        ModelAndView modelAndView;
        if(wawUsersService.getLoggedInUser() == null)
            return homeController.login();
        return dashboardController.dashboard(wawUsersService.getLoggedInUser());
    }

    @GetMapping("/logout")
    public ModelAndView logOut(){
        wawUsersService.logout();
        return new HomeController().home();
    }

}
