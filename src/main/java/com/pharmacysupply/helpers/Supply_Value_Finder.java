package com.pharmacysupply.helpers;

import com.pharmacysupply.entity.PharmacyMedicineSupply;

import java.util.*;
public class Supply_Value_Finder {
    public List<Integer> ValueFinder(List<PharmacyMedicineSupply> lists){
        try {
            //FIND THE VALUES OF SUPPLIES BY ALL OF THE PHARMACIES AND RETURN THEM
            int size = lists.size();
            List<Integer> supplies = new ArrayList<>();
            for (PharmacyMedicineSupply list : lists) {
                Integer l = Math.toIntExact(list.getSupplyCount());
                supplies.add(l);
            }
            return supplies;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        }
}
