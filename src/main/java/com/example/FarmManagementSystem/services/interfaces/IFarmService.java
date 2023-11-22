package com.example.FarmManagementSystem.services.interfaces;

import com.example.FarmManagementSystem.model.Farm;

public interface IFarmService {
    //============================================== GET method ========================================================
    public Farm getFarmById(Integer farmId);
    //============================================== POST method =======================================================
    public void saveFarm(Farm farm);
    //============================================== PUT method ========================================================
    public void updateFarm(Farm farm, Integer farmId);
    //============================================== DELETE method =====================================================
    public void deleteFarm(Integer farmId);

}
