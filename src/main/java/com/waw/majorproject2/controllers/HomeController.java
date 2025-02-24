package com.waw.majorproject2.controllers;


import com.waw.majorproject2.models.Outbreak;
import com.waw.majorproject2.models.WawUser;
import com.waw.majorproject2.services.OutbreaksService;
import com.waw.majorproject2.services.WawUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    WawUsersService wawUsersService;
    @Autowired
    OutbreaksService outbreaksService;
    @RequestMapping("/index")
    public ModelAndView home(){
        return new ModelAndView("index.html");
    }
    @RequestMapping("/report-outbreak")
    public ModelAndView reportOutBreak(){
        ModelAndView modelAndView = new ModelAndView("report-outbreak.html");
        List<Outbreak> outbreakList = outbreaksService.getOutbreaks() ;
        modelAndView.addObject(outbreakList);
        Outbreak newOutbreak = new Outbreak();
        modelAndView.getModel().put("newOutbreak", newOutbreak);
        return modelAndView;
    }
    @RequestMapping("/about-us")
    public ModelAndView aboutUs(){
        return new ModelAndView("about-us.html");
    }
    @PostMapping("/submit-report")
    public ModelAndView submitReport(@ModelAttribute Outbreak newOutbreak, Model model){
        Outbreak reported = outbreaksService.reportOutbreak(newOutbreak);
        ModelAndView modelAndView = new ModelAndView("thank-your-report.html");
        modelAndView.getModel().put("reportedOutbreak", reported);
        modelAndView.getModel().put("outbreaks", outbreaksService.getOutbreaks());
        return modelAndView;
    }
    @GetMapping({"/submit-report","/get-outbreaks"})
    public ModelAndView submitReport( Model model){
        ModelAndView modelAndView = new ModelAndView("thank-your-report.html");
        modelAndView.getModel().put("outbreaks", outbreaksService.getOutbreaks());
        return modelAndView;
    }

    @GetMapping({"/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("login.html");
        modelAndView.getModel().put("userLogin", new WawUser());
        return modelAndView;
    }


    @GetMapping({"/register", "/get-started"})
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView("get-started.html");
        WawUser newWawUser = new WawUser();
        modelAndView.getModel().put("newWawUser",newWawUser);
        return modelAndView;
    }

    @GetMapping("/market")
    public ModelAndView market(){
        ModelAndView modelAndView  = new ModelAndView("market.html");
        return modelAndView;
    }

    @GetMapping("/research")
    public ModelAndView research(){
        ModelAndView modelAndView = new ModelAndView("research.html");
        return modelAndView;
    }

}

