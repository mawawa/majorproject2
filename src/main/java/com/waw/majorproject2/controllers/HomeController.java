package com.waw.majorproject2.controllers;


import com.waw.majorproject2.models.Outbreak;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    @RequestMapping("/")
    public ModelAndView home(){
        return new ModelAndView("index.html");
    }
    @RequestMapping("/report")
    public ModelAndView reportOutBreak(){
        ModelAndView modelAndView = new ModelAndView("report-outbreak.html");
        List<Outbreak> outbreakList;

    }
}
