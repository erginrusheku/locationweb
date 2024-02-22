package com.barath.location.service;

import com.barath.location.entities.Location;

import java.util.List;

public interface LocationService {
    Location saveLocation(Location location);
    Location updateLocation(Location location);
    void deleteLocation(Location location);
    Location getLocationById(int id);
    List<Location> getAllLocations();
}
