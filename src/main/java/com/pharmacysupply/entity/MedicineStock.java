package com.pharmacysupply.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="MedicineStock")
@Data
public class MedicineStock implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long mId;

	private String name;

	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "medicineStock")
	private List<ChemicalComposition> chemicalComposition= new ArrayList<>();

	@JsonFormat(pattern = "yyyy/MM/dd")
    private Date localDate;
	
	private String TargetAilment;

	private int NumberOfTabletsInStock;
	public MedicineStock() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MedicineStock(Long mId, String name, List<ChemicalComposition> chemicalComposition, Date localDate,
                         String targetAilment, int numberOfTabletsInStock) {
		super();
		this.mId = mId;
		this.name = name;
		this.chemicalComposition = chemicalComposition;
		this.localDate = localDate;
		TargetAilment = targetAilment;
		NumberOfTabletsInStock = numberOfTabletsInStock;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getmId() {
		return mId;
	}

	public void setmId(Long mId) {
		this.mId = mId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ChemicalComposition> getChemicalComposition() {
		return chemicalComposition;
	}

	public void setChemicalComposition(List<ChemicalComposition> chemicalComposition) {
		this.chemicalComposition = chemicalComposition;
	}

	public Date getLocalDate() {
		return localDate;
	}

	public void setLocalDate(Date localDate) {
		this.localDate = localDate;
	}

	public String getTargetAilment() {
		return TargetAilment;
	}

	public void setTargetAilment(String targetAilment) {
		TargetAilment = targetAilment;
	}

	public int getNumberOfTabletsInStock() {
		return NumberOfTabletsInStock;
	}

	public void setNumberOfTabletsInStock(int numberOfTabletsInStock) {
		NumberOfTabletsInStock = numberOfTabletsInStock;
	}
}
