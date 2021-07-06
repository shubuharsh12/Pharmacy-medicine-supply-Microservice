package com.pharmacysupply.service;

import com.pharmacysupply.dao.PharmacyRepository;
import com.pharmacysupply.entity.PharmacyMedicineSupply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class PharmacySupplyService {

    @Autowired
    private PharmacyRepository pharmacyRepository;
    public List<PharmacyMedicineSupply> getAllPharmacies() {
        //returns the data fetched from pharmacy table
        List<PharmacyMedicineSupply> list = (List<PharmacyMedicineSupply>) this.pharmacyRepository.findAll();

        return list;
    }
}
