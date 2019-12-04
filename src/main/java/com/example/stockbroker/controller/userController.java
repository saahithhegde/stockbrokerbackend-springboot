package com.example.stockbroker.controller;

import com.example.stockbroker.dao.userRepository;
import com.example.stockbroker.dao.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class userController {


    @Autowired
    private userRepository newUser;

    @RequestMapping(value = "signUp", method = RequestMethod.POST,produces = {"text/plain"})
    public String addUser(@RequestBody user newUserRegistration) {
        try {
            List<user> existingUsers = newUser.findUsersByEmail(newUserRegistration.getEmail());
            if (existingUsers == null || existingUsers.size() == 0) {
                newUser.save(newUserRegistration);
                return "created user";
            }
            else{
                return "user already exists";
            }

            }
            catch (Exception e)
            {
            return e.toString();
            }
    }
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = {"application/json"})
    public HashMap<String,String> getUser(@RequestBody user findUser) {
        HashMap<String,String> map=new HashMap<String,String>();
        String email=findUser.getEmail();
        String password=findUser.getPassword();
        if(newUser.findUsersByEmail(findUser.getEmail()).size()>0) {
            for (user existingUsers : newUser.findUsersByEmail(findUser.getEmail())) {
                if (email.equals(existingUsers.getEmail()) && password.equals(existingUsers.getPassword())) {
                    map.put("token", existingUsers.getEmail());
                    map.put("fname", existingUsers.getFirstname());
                    map.put("error", "");
                } else {
                        map.put("fname", "");
                        map.put("token", "");
                        map.put("error", "Wrong Password");
                }
                }
            }
            else
            {
                map.put("fname", "");
                map.put("token", "");
                map.put("error", "No User Found");
    }
    return map;
    }
    @RequestMapping(value = "getUserProfile", method = RequestMethod.POST, produces = {"application/json"})
    public List<user> getProfile(@RequestBody user userProfile){
        List<user> userProf = newUser.findUsersByEmail(userProfile.getEmail());
        return userProf;
    }

    @RequestMapping(value = "updateUserProfile", method = RequestMethod.POST, produces = {"application/json"})
    public String updateProfile(@RequestBody user userProfile){
        int existinguserid=0;
        for(user  existingUser :newUser.findUsersByEmail(userProfile.getEmail()))
        {
            existinguserid=existingUser.getId();
        }
        try {
            user userToUpdate=newUser.getOne(existinguserid);
            userToUpdate.setPassword(userProfile.getPassword());
            userToUpdate.setLastname(userProfile.getLastname());
            userToUpdate.setFirstname(userProfile.getFirstname());
            newUser.save(userToUpdate);
            return "Updated";
        }
        catch (Exception e) {
            return "Update Unsucessful";
        }
    }

    @RequestMapping(value = "forgotPassword", method = RequestMethod.POST, produces = {"application/json"})
    public String forgotPassword(@RequestBody user forgotPassword){
        try {
            String password="";
            String qus="";
            String ans="";
            String usedQuestion=forgotPassword.getQuestion();
            String usedAnswer=forgotPassword.getAnswer();
            for(user existingUsers : newUser.findUsersByEmail(forgotPassword.getEmail()))
            {
                 password=existingUsers.getPassword();
                 qus=existingUsers.getQuestion();
                 ans=existingUsers.getAnswer();
            }
            if(password!=null && password.length()>0 && usedQuestion.equals(qus) && usedAnswer.equals(ans))
            {
                return password;
            }
            else
            {
                return "user not found/wrong answer provided";
            }
        }
        catch (Exception e) {
            return "Failed to find user";
        }
    }

}
