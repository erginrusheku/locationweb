package com.barath.location.controller;

import com.barath.location.entities.Location;
import com.barath.location.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationRESTController {
    @Autowired
    LocationRepository repository;

    @GetMapping
    public List<Location> getAllLocation(){
      return  repository.findAll();
    }

    @PostMapping
    public Location createLocation(@RequestBody Location location){
        return repository.save(location);
    }

    @PutMapping
    public Location updateLocation(@RequestBody Location location){
        return repository.save(location);
    }
    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable int id){
        repository.deleteById(id);
    }
    @GetMapping("/{id}")
    public Location getLocationByid(@PathVariable int id){
        return repository.findById(id).get();
    }
}
