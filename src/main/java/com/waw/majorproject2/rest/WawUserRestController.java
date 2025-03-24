package com.waw.majorproject2.rest;


import com.waw.majorproject2.models.WawUser;
import com.waw.majorproject2.repositories.WawUsersRepository;
import com.waw.majorproject2.services.WawUsersService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WawUserRestController {
    @Autowired
    WawUsersRepository wawUsersRepository;
    @Autowired
    WawUsersService wawUsersService;
    @Autowired

    @GetMapping(value = "api/wawusers")
    public List<WawUser> getWawUser(){
        return wawUsersRepository.findAll();
    }

    @PostMapping("api/wawusers")
    public ResponseEntity<WawUser> saveWawUser(@RequestBody WawUser wawUser){
        return ResponseEntity.ok(wawUsersRepository.save(wawUser));
    }

    @GetMapping("api/wawusers/get/{id}")
    public ResponseEntity<WawUser> getWawUserById(@PathVariable Long id){
        return ResponseEntity.ok(wawUsersRepository.findById(id).get());
    }


    @DeleteMapping("api/wawusers/{id}")
    public void deleteWawUser(@PathVariable Long id){
        wawUsersRepository.deleteById(id);
    }

    @PostMapping("api/wawusers/login")
    public WawUser loginWawUser(@RequestBody WawUser wawUser){
        return wawUsersService.login(wawUser.getEmailAddress(), wawUser.getPassword());
    }

    @GetMapping("api/wawuser/reset/{email}")
    public ResponseEntity<WawUser> resetPassword (@PathVariable String email){
        WawUser tempUser = new WawUser();
        tempUser.setEmailAddress(email);
        for (WawUser wawUser : wawUsersRepository.findAll()) {
            if (wawUser.getEmailAddress().equals(email)) {
                tempUser.setEmailAddress(email);
                //todo send a password reset link of password to the user.
                return ResponseEntity.ok(tempUser);

            }
        }
        return ResponseEntity.ok(null);
    }

    @GetMapping("api/wawusers/all")
    public ResponseEntity<List<String>> getEmails(){
        List<WawUser> wawUsers = wawUsersRepository.findAll();
        List<String> users = new ArrayList<>();
        for(WawUser user: wawUsers){
            users.add(user.getEmailAddress());
        }

        return ResponseEntity.ok(users);
    }
}
