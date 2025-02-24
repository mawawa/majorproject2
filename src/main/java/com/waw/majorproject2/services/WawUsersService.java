package com.waw.majorproject2.services;


import com.waw.majorproject2.models.WawUser;
import com.waw.majorproject2.repositories.WawUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WawUsersService {
    @Autowired
    WawUsersRepository wawUsersRepository;

    private WawUser loggedInUser;

    public WawUser getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(WawUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    //login the user
    public WawUser login(String email, String password){
        for(WawUser wawUser: wawUsersRepository.findAll()){
            if(! wawUser.isActive()){
                if(email.equals(wawUser.getEmailAddress())){
                    if(password.equals(wawUser.getPassword())){
                        this.loggedInUser = wawUser;
                        return wawUser;
                    }
                }
            }
        }
        return null;
    }

    public void logout(){
        loggedInUser = null;
    }

    //activate the user
    public WawUser activateWawUser(Long id){
        Optional<WawUser>  activateWawUser= wawUsersRepository.findById(id);
        if(activateWawUser.isPresent()){
            activateWawUser.get().setActive(true);
            return activateWawUser.get();
        }
        return null;
    }

    //find all the users
    public List<WawUser> getAllUsers(){
        return wawUsersRepository.findAll();
    }

    //delete the waw user
    public WawUser deleteWawUser(Long id){
        Optional<WawUser> deletedWawUser = wawUsersRepository.findById(id);
        if(deletedWawUser.isPresent()){
            wawUsersRepository.delete(deletedWawUser.get());
            return deletedWawUser.get();
        }
        return null;
    }

    //update save waw user
    public WawUser SaveUpdate(WawUser wawUser){
        return wawUsersRepository.save(wawUser);
    }


}
