package com.barath.location.controller;

import com.barath.location.entities.Location;
import com.barath.location.repository.LocationRepository;
import com.barath.location.service.LocationService;
import com.barath.location.util.ReportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.barath.location.util.EmailUtil;


import javax.servlet.ServletContext;
import java.util.List;

@Controller
public class LocationController {
    @Autowired
    private LocationService locationService;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    ReportUtil reportUtil;
    @Autowired
    ServletContext sc;

    @RequestMapping("/showCreate")
    public String showCreate(){
        return "createLocation";
    }

    @RequestMapping("/saveLoc")
    public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap){
       Location locationSaved = locationService.saveLocation(location);
       String msg = "Location saved with id: " + locationSaved.getId();
       modelMap.addAttribute("msg", msg);
       emailUtil.sendEmail("rushekuergin@com","Location Saved",
               "Good");
        return "createLocation";
    }

    @RequestMapping("/displayLocations")
    public String displayLocations(ModelMap modelMap){
        List<Location> locations = locationService.getAllLocations();
        modelMap.addAttribute("locations", locations);
        return "displayLocations";
    }

    @RequestMapping("/deleteLocation")
    public String deleteLocation(@RequestParam("id") int id, ModelMap modelMap){
        Location location = new Location();
        location.setId(id);
        locationService.deleteLocation(location);
        List<Location> locations = locationService.getAllLocations();
        modelMap.addAttribute("locations", locations);

        return "displayLocations";
    }
    @RequestMapping("/showUpdate")
    public String showUpdate(@RequestParam("id") int id, ModelMap modelMap){
        Location location = locationService.getLocationById(id);
        modelMap.addAttribute("location", location);
        return "updateLocation";
    }

    @RequestMapping("/updateLoc")
    public String updateLocation(@ModelAttribute("location")Location location, ModelMap modelMap){
        locationService.updateLocation(location);

        List<Location> locations = locationService.getAllLocations();
        modelMap.addAttribute("locations", locations);

        return "displayLocations";
    }

    @RequestMapping("/generateReport")
    public String generateReport(){
        String path = sc.getRealPath("/");

        List<Object[]> data = locationRepository.findTypeAndTypeCount();
        reportUtil.generatePieChart(path,data);
        return "report";
    }
}
