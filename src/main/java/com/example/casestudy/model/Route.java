package com.example.casestudy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String routeName;

    private int duration;

    public Route(int id,String routeName,int duration){
            this.id = id;
            this.routeName = routeName;
            this.duration = duration;
    }

    public String getRouteNameById(int id){
        return routeName;
    }

    public int getRouteDuration(int id){
        return duration;
    }

    public int getRouteId(){
        return id;
    }

}
