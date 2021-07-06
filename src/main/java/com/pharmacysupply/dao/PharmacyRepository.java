package com.pharmacysupply.dao;


import com.pharmacysupply.entity.PharmacyMedicineSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyRepository extends CrudRepository<PharmacyMedicineSupply,Long> {

}
