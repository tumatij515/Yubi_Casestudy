package com.example.casestudy.controller;

import com.example.casestudy.model.Bus;
import com.example.casestudy.repositry.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.casestudy.repositry.BusRepositry;

import java.util.List;
import java.util.Optional;

@RestController
public class BusController {

    @Autowired
    UserRepositry userRepositry;

    @Autowired BusRepositry busRepositry;

    @GetMapping("/buses")
    public List<Bus> getAllBuses() {
        return busRepositry.findAll();
    }

    @GetMapping("/bus/details/{id}")
    public Bus getBusDetails(@PathVariable int id){
        return busRepositry.findById(id);
    }

    @GetMapping("/bus/timing_details/{id}")
    public String  getBusTimeDetails(@PathVariable int id){
        return busRepositry.findById(id).getDepartureTime() + "to" + busRepositry.findById(id).getArrivalTime();
    }

    @PostMapping("/bus/{user_id}/create")
    public String createBusEntry(@PathVariable("user_id") int user_id,@RequestBody Bus bus){
        if (!userRepositry.findById(user_id).getRole().equals("admin")){
            return "access restricted";
        }
        // creating a new bus in our system
        busRepositry.save(bus);
        return "new bus entry created";
    }

    @PutMapping("/bus/{user_id}/update_details")
    public ResponseEntity<Object> updateBusDetails(@PathVariable("user_id") int user_id,@RequestBody Bus bus){
        // creating a new bus in our system
        if (!userRepositry.findById(user_id).getRole().equals("admin")){
             System.out.println("access restricted");
             return ResponseEntity.notFound().build();
        }
        Optional<Bus> BusRepo = Optional.ofNullable(busRepositry.findById(bus.getBusId()));
        if (!BusRepo.isPresent()){
            return ResponseEntity.notFound().build();
        }
        busRepositry.save(bus);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/bus/{user_id}/delete/{id}")
    public String deleteBus(@PathVariable("user_id") int user_id,@PathVariable("id") int id){
        if (!userRepositry.findById(user_id).getRole().equals("admin")){
            return "access restricted";
        }
        busRepositry.deleteById(id);
        return "deleted";
    }

}
