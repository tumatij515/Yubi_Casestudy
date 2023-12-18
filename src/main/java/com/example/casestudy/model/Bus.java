package com.example.casestudy.model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import com.example.casestudy.repositry.RouteRepositry;

@Entity
@Table(name = "buses")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String registrationNumber;
    private String type;
    private int routeId;
    @OneToOne(mappedBy = "routes")
    private Route route;

    private String  departureTime;
    private String  arrivalTime;


    public Bus(int id,String registrationNumber,String type,int routeId,String departureTime,String arrivalTime){
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.routeId = routeId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    @Autowired
    private RouteRepositry routeRepositry;

    public int getBusId(){
        return id;
    }

    public String getBusRegistrationNumber(){
        return registrationNumber;
    }
    public String getBusType(){
        return type;
    }

    public void setRegistrationNumber(String registrationNumber){
        this.registrationNumber = registrationNumber;
    }
    public void setBusType(String type){
         this.type = type;
    }

    public Route getRoute(int id){
        return routeRepositry.findById(id);
    }

    public void updateRoute(Route r){
        route = r;
    }

    public String  getDepartureTime(){
        return departureTime;
    }

    public String  getArrivalTime(){
        return arrivalTime;
    }

}
