package com.example.FarmManagementSystem.controller.impl;

import com.example.FarmManagementSystem.model.Farm;
import com.example.FarmManagementSystem.model.FarmLocation;
import com.example.FarmManagementSystem.repository.FarmRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class FarmControllerTest {
    //========================================= Connections  ===========================================================
    @Autowired
    FarmRepository farmRepository;
    @Autowired
    WebApplicationContext webApplicationContext;

    //========================================= Test Objects  ==========================================================
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    Farm farm;
    Integer farmID;
    FarmLocation farmLocation;

    //========================================= Preparations  ==========================================================
    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // Create location
        farmLocation = new FarmLocation("main street","Ahsa","Saudi Arabia",40.7128,-74.0060);

        // Create a farm
        farm = new Farm("Alsowaiq Farm",farmLocation);
        farmRepository.save(farm);

        // Save the id to add it in the path for testing.
        farmID = farm.getId();
    }
    @AfterEach
    public void tearDown(){
        farmRepository.deleteAll();
    }

    //============================================== Test GET methods ==================================================
    @Test
    void getFarmById_validRequest_correctFarm()throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/API/Farms/"+farmID))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Alsowaiq Farm"));
    }
    @Test
    void getFarmById_inValidRequest_farmNotFound()throws Exception {

        // pass not exists id
        MvcResult mvcResult = mockMvc.perform(get("/API/Farms/10"))
                .andExpect(status().isNotFound())
                .andReturn();

        assertFalse(mvcResult.getResponse().getContentAsString().contains("Alsowaiq Farm"));
    }

    //============================================== Test POST methods =================================================
    @Test
    void createFarm_validBody_farmCreated() throws Exception{

        //create a farm
        Farm farm = new Farm("Alhasa Farm",farmLocation);

        //convert the farm to string to use is as JSON body
        String body = objectMapper.writeValueAsString(farm);

        mockMvc.perform(post("/API/Farms/Add").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        Optional<Farm> farmOptional = farmRepository.findByNameIgnoreCase("Alhasa Farm");
        assertTrue(farmOptional.isPresent());

    }
    @Test
    void createFarm_inValidBody_farmNotCreated() throws Exception{

        //create a farm with null name
        Farm farm = new Farm(null,farmLocation);

        //convert the farm to string to use is as JSON body
        String body = objectMapper.writeValueAsString(farm);

        mockMvc.perform(post("/API/Farms/Add").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        //check if the repository doesn't contain
        Optional<Farm> farmOptional = farmRepository.findByNameIgnoreCase("Alhasa Farm");
        assertFalse(farmOptional.isPresent());

    }

    //============================================== Test PUT methods ==================================================

    @Test
    void updateFarm_validBody_farmUpdated() throws Exception{
        //update the location of the farm
        farmLocation.setCity("Riyadh");
        farm.setLocation(farmLocation);

        //convert it to string to use it as JSON body
        String body = objectMapper.writeValueAsString(farm);

        mockMvc.perform(put("/API/Farms/" + farmID).content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        assertTrue(farmRepository.findAll().toString().contains("Riyadh"));
    }
    @Test
    void updateFarm_inValidFarmId_farmNotFound() throws Exception{

        //update the location of the farm
        farmLocation.setCity("Riyadh");
        farm.setLocation(farmLocation);

        //convert it to string to use it as JSON body
        String body = objectMapper.writeValueAsString(farm);

        //pass not exists id
        mockMvc.perform(put("/API/Farms/12").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        List<Farm> farmList = farmRepository.findByLocationCityIgnoreCase("Riyadh");
        assertTrue(farmList.isEmpty());
    }
    //============================================== Test DELETE methods ===============================================

    @Test
    void deleteFarm_validRequest_farmDeleted() throws Exception {

        // Create a farm
        Farm farm = new Farm("ABC Farm",farmLocation);
        Integer id = farmRepository.save(farm).getId();

        // Delete endpoint
        mockMvc.perform(delete("/API/Farms/" + id))
                .andExpect(status().isNoContent())
                .andReturn();

        assertFalse(farmRepository.findAll().toString().contains("ABC Farm"));
    }
    @Test
    void deleteFarm_notExistsFarm_farmNotFound() throws Exception {

        // Delete endpoint
        mockMvc.perform(delete("/API/Farms/10000" ))
                .andExpect(status().isNotFound())
                .andReturn();

        assertTrue(farmRepository.findAll().toString().contains("Alsowaiq Farm"));
    }

}