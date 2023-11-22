package com.example.FarmManagementSystem.services.impl;

import com.example.FarmManagementSystem.model.Farm;
import com.example.FarmManagementSystem.repository.FarmRepository;
import com.example.FarmManagementSystem.services.interfaces.IFarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class FarmService implements IFarmService {

    //============================================== Connections =======================================================
    @Autowired
    private FarmRepository farmRepository;

    //============================================== GET method ========================================================
    @Override
    public Farm getFarmById(Integer farmId) {

        // Check if the farm exists and then return it
        return farmRepository.findById(farmId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Farm not found"));
    }
    //============================================== POST method =======================================================
    @Override
    public void saveFarm(Farm farm) {
        if (farm.getId() != null) {
            Optional<Farm> farmOptional = farmRepository.findById(farm.getId());
            if (farmOptional.isPresent())
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Farm with id " + farm.getId() + " already exists");
        }
        farmRepository.save(farm);
    }
    //============================================== PUT method ========================================================
    @Override
    public void updateFarm(Farm farm, Integer farmId) {

        // Check if the farm exists
        Farm farmFromDB = farmRepository.findById(farmId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Farm not found"));

        // Update
        farm.setId(farmFromDB.getId());
        farmRepository.save(farm);
    }
    //============================================== DELETE method =====================================================
    @Override
    public void deleteFarm(Integer farmId) {

        // Check if the farm exists
        Farm farm = farmRepository.findById(farmId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Farm not found"));

        // Delete the farm itself
        farmRepository.deleteById(farmId);
    }

}
