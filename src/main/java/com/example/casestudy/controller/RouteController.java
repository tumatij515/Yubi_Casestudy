package com.example.casestudy.controller;


import com.example.casestudy.model.Route;
import com.example.casestudy.repositry.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.casestudy.repositry.RouteRepositry;

import java.util.Optional;

@RestController
public class RouteController {

    @Autowired
    UserRepositry userRepositry;
    // -----
    @Autowired
    private RouteRepositry routeRepositry;

    @GetMapping("/routes")
    public Iterable<Route> getAllRoutees() {
        return routeRepositry.findAll();
    }

    @GetMapping("/route/details/{id}")
    public Route getRouteDetails(@PathVariable int id){
        return routeRepositry.findById(id);
    }

    @PostMapping("/route/create")
    public Route createRouteEntry(@RequestBody Route route){
        // creating a new route in our system
        Route saveRoute = routeRepositry.save(route);
        return saveRoute;
    }

    @PutMapping("/route/{user_id}/update_details")
    public ResponseEntity<Object> updateRouteDetails(@PathVariable("user_id") int user_id,@RequestBody Route route){
        // creating a new route in our system
        if (!userRepositry.findById(user_id).getRole().equals("admin")){
            System.out.println("access restricted");
            return ResponseEntity.notFound().build();
        }
        Optional<Route> routeRepo = Optional.ofNullable(routeRepositry.findById(route.getRouteId()));
        if (!routeRepo.isPresent()){
            return ResponseEntity.notFound().build();
        }
        routeRepositry.save(route);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/route/{user_id}/delete/{id}")
    public void deleteRoute(@PathVariable("user_id") int user_id,@PathVariable("id") int id){
        if (!userRepositry.findById(user_id).getRole().equals("admin")){
            System.out.println("access restricted");
            return;
        }
        routeRepositry.deleteById(id);
    }
}
