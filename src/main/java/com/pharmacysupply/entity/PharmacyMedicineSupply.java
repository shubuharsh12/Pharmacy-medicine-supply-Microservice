package com.pharmacysupply.entity;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PharmacyList")
@ResponseBody
@Data
public class PharmacyMedicineSupply implements Serializable {

    private static final long serialVersionUID = 1L;
    //@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pharmacy_id;
    private String pharmacyName;
    private String medicineName;
    private Long supplyCount;

    public PharmacyMedicineSupply(String pharmacyName, String medicineName, Long supplyCount) {
        this.pharmacyName = pharmacyName;
        this.medicineName = medicineName;
        this.supplyCount = supplyCount;
    }

    @Override
    public String toString() {
        return "PharmacyMedicineSupply{" +
                "pharmacy_id=" + pharmacy_id +
                ", pharmacyName='" + pharmacyName + '\'' +
                ", medicineName='" + medicineName + '\'' +
                ", supplyCount=" + supplyCount +
                '}';
    }

    public PharmacyMedicineSupply() {
        super();
    }


    public PharmacyMedicineSupply setSupplyCount(Long supplyCount) {
        this.supplyCount = supplyCount;
        return null;
    }
}
