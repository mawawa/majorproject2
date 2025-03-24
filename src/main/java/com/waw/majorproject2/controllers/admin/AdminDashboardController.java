package com.waw.majorproject2.controllers.admin;

import com.waw.majorproject2.models.WawUser;
import com.waw.majorproject2.repositories.WawUsersRepository;
import com.waw.majorproject2.services.WawUsersService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminDashboardController {
    @Autowired
    WawUsersRepository wawUsersRepository;
    @GetMapping("/admin/users/page/{page}")
    public String userPageNation(Model model, @ModelAttribute String page){
        int pageNumber = Integer.parseInt(page) *10;

        model.addAttribute("users" ,wawUsersRepository.findAll().subList(pageNumber -10, pageNumber));
        return "admin/admin-temples.html::users-frag";
    }

    @PostMapping("/admin/users/search")
    public String search(Model model, @RequestBody String search){
          search = search.split("=")[1];
        search = search.trim();
        List<WawUser> users = wawUsersRepository.findAll();
        if(search.isEmpty()){
            model.addAttribute("users", users);
            return "admin/admin-temples::users-frag";
        }
        List<WawUser> filteredUsers = new ArrayList<>();
        for(WawUser u: users){
            if(u.getFirstName().contains(search)){
                filteredUsers.add(u);
            }
        }

        model.addAttribute("users", filteredUsers);
        return "admin/admin-temples::users-frag";
    }
}
