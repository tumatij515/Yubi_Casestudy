package com.example.casestudy.controller;

import com.example.casestudy.model.Route;
import com.example.casestudy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.casestudy.repositry.UserRepositry;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepositry userRepositry;

    @GetMapping("/users")
    public Iterable<User> getAlluseres() {
        return userRepositry.findAll();
    }

    @GetMapping("/user/details/{id}")
    public Route getuserDetails(@PathVariable int id){
        return userRepositry.findById(id);
    }

    @PostMapping("/user/create")
    public User createuserEntry(@RequestBody User user){
        // creating a new user in our system
        User saveuser = userRepositry.save(user);
        return saveuser;
    }

    @PutMapping("/user/update_details")
    public ResponseEntity<Object> updateuserDetails(@RequestBody User user){
        // creating a new user in our system
        Optional<User> userRepo = Optional.ofNullable(userRepositry.findById(user.getuserId()));
        if (!userRepo.isPresent()){
            return ResponseEntity.notFound().build();
        }
        userRepositry.save(user);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteuser(@PathVariable int id){
        userRepositry.deleteById(id);
    }
}
