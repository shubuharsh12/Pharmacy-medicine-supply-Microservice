package com.pharmacysupply.controller;

import com.pharmacysupply.entity.MedicineStock;
import com.pharmacysupply.entity.PharmacyMedicineSupply;
import com.pharmacysupply.helpers.Stock_Medicine_Finder;
import com.pharmacysupply.helpers.Supply_Value_Finder;
import com.pharmacysupply.service.PharmacySupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/PharmacySupply")

/*
This controller is a collection of 2 API's which give the details about the medicineDemand and PharmacyMedicineSupply
 */
public class SupplyController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PharmacySupplyService pharmacySupplyService;

    public SupplyController() {
    }
    @RequestMapping(value = "/")
    public ResponseEntity<List<PharmacyMedicineSupply>> getPharmacy() {
        List<PharmacyMedicineSupply> list = this.pharmacySupplyService.getAllPharmacies();
        if (list.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    @GetMapping("/{count}/{demand}")

    @ResponseBody
    public ResponseEntity<List<PharmacyMedicineSupply>> supplyMedicineCount(@PathVariable("count") Long count,@PathVariable("demand") int demand) {
        try {
            /*
            Here, The microservice is interacting with the MedicineStock microservice to get the data stored in that table.
            The MedicineStock microservice would have to be in the running mode.
             */

            List<MedicineStock> list_medicine = restTemplate.getForObject("http://localhost:8082/MedicineStockInformation/", List.class);
            //StockMedicineFinder is a helper class
            Stock_Medicine_Finder dmc = new Stock_Medicine_Finder();

            //Stock_Medicine_finder will get number_of_tablets_in_stock from the medicine_stock microservice
            int numberOfTabletsStock = dmc.list_Medicine(list_medicine, count.intValue());
            //To get the value of Demand from the medicine_demand table
            //Here the microservice is interacting with the pharmacyMedicineSupply service to get list of all pharmacies.
            List<PharmacyMedicineSupply> list = pharmacySupplyService.getAllPharmacies();
            //to check the values
            //ValueFinder is a helper class that take all the pharmacy list as input and return the list of supplies as output.
            Supply_Value_Finder supplyValueFinder = new Supply_Value_Finder();
            List<Integer> supplies = supplyValueFinder.ValueFinder(list);

            //Now to compare the DemandCount and NumberOfTablets-
            //Business Logic Implemented here
            System.out.println(demand);
            System.out.println(numberOfTabletsStock);

            if (demand >= numberOfTabletsStock) {
                int divide = numberOfTabletsStock / supplies.size();
                for (int i = 0; i < supplies.size(); i++) {
                    if (supplies.get(i) > divide) {
                        list.get(i).setSupplyCount((long) divide);
                    }
                }
            }
            else{
                int divide = demand / supplies.size();
                for (int i = 0; i < supplies.size(); i++) {
                    if (supplies.get(i) > divide) {
                        list.get(i).setSupplyCount((long) divide);
                    }
                }
            }

            return ResponseEntity.status(HttpStatus.OK).body(list);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
    }


}
