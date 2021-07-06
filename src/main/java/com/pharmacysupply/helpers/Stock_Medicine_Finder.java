package com.pharmacysupply.helpers;

import aj.org.objectweb.asm.TypeReference;
import com.pharmacysupply.entity.MedicineStock;

import java.util.*;

public class Stock_Medicine_Finder {
    public int list_Medicine(List<MedicineStock> list_medicine,int stock_medicine_id) {
        try {
            //FIND THE NUMBER OF TABLETS IN STOCK AND RETURNS.
            if(stock_medicine_id==0){
              return 0;
            }
            stock_medicine_id--;
            LinkedHashMap<String, MedicineStock> map
                    = new LinkedHashMap((Map) list_medicine.get(stock_medicine_id));
            int p = Integer.parseInt(String.valueOf(map.get("numberOfTabletsInStock")));
            return p;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }
}