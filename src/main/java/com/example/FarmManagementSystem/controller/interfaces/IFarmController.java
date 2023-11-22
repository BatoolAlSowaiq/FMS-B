package com.example.FarmManagementSystem.controller.interfaces;

import com.example.FarmManagementSystem.model.Farm;

import java.util.List;

public interface IFarmController {

    //============================================ GET Requests ========================================================
    Farm getFarmById(Integer farmId);
    List<Farm> getAllFarms();
    List<Farm> searchFarms(String query);
    //============================================ POST Requests =======================================================
    void createFarm(Farm farm);
    //============================================ PUT Requests ========================================================
    void updateFarm(Farm farm, Integer farmId);
    //============================================ DELETE Requests =====================================================
    void deleteFarm(Integer farmId);

}
