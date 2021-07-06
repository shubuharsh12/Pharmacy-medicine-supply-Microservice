package com.pharmacysupply.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ChemicalComposition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
	public String chemicalComposition;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonBackReference
	private MedicineStock medicineStock;

    
}
