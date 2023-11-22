package com.example.FarmManagementSystem.controller.impl;

import com.example.FarmManagementSystem.controller.interfaces.IFarmController;
import com.example.FarmManagementSystem.model.Farm;
import com.example.FarmManagementSystem.repository.FarmRepository;
import com.example.FarmManagementSystem.services.impl.FarmService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/API/Farms")
public class FarmController implements IFarmController {
    //========================================= Connections  ===========================================================
    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private FarmService farmService;

    //============================================ GET Requests ========================================================
    @Override
    @GetMapping("/{farmId}")
    @ResponseStatus(HttpStatus.OK)
    public Farm getFarmById(@PathVariable Integer farmId) {
        return farmService.getFarmById(farmId);
    }
    @Override
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    @Override
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Farm> searchFarms(@RequestParam("q") String query) {
        return farmRepository.search(query);
    }
    //============================================ POST Requests =======================================================
    @Override
    @PostMapping("/Add")
    @ResponseStatus(HttpStatus.CREATED)
    public void createFarm(@RequestBody @Valid Farm farm) {farmService.saveFarm(farm);}

    //============================================ PUT Requests ========================================================
    @Override
    @PutMapping("/{farmId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFarm(@RequestBody @Valid Farm farm, @PathVariable Integer farmId) {
        farmService.updateFarm(farm,farmId);
    }

    //============================================ DELETE Requests =====================================================
    @Override
    @DeleteMapping("/{farmId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFarm(@PathVariable Integer farmId) {
        farmService.deleteFarm(farmId);
    }

}
