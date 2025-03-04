package com.waw.majorproject2.rest;

import com.waw.majorproject2.models.Plot;
import com.waw.majorproject2.models.WawUser;
import com.waw.majorproject2.repositories.PlotRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlotRestController {
    @Autowired
    PlotRepository plotRepository;
    @PostMapping("/api/plots")
    public ResponseEntity<Plot> savePlot(@RequestBody Plot plot){
        return ResponseEntity.ok( plotRepository.save(plot));
    }
    @GetMapping("/api/plots/get/{id}")
    public ResponseEntity<Plot> getPlot(@PathVariable Long id){
        return ResponseEntity.ok(plotRepository.findById(id).get());
    }

    @GetMapping("/api/plots/owners/{id}")
    public ResponseEntity<List<Plot>> ownersPlots(@PathVariable Long id){
        List<Plot> allPlots = plotRepository.findAll();
        List<Plot> ownersPlots = new ArrayList<>();
        for(Plot p: allPlots){
            for(WawUser wawUser: p.getOwners()){
                if(wawUser.getId().equals(id))
                    ownersPlots.add(p);
                break;
            }
        }
        return ResponseEntity.ok(ownersPlots);
    }
}
