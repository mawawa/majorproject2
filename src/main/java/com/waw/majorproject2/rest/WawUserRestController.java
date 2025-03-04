package com.waw.majorproject2.rest;


import com.waw.majorproject2.models.WawUser;
import com.waw.majorproject2.repositories.WawUsersRepository;
import com.waw.majorproject2.services.WawUsersService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WawUserRestController {
    @Autowired
    WawUsersRepository wawUsersRepository;
    @Autowired
    WawUsersService wawUsersService;

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
}
