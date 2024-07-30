package com.micro.service.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", columnDefinition = "UNIQUEIDENTIFIER")
	private UUID id; 
	private String companyName;
	@Embedded
	private CompanyAddress address;
    private String companyPhone;
    private String companyAlternatePhone;
    private String companyEmail;
    private String companyAlternateEmail;
    private Integer storeCount; 
    private String createdBy;
    private String createdDate;
    private String modifyBy;
    private String modifyDate;


   
    public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public CompanyAddress getAddress() {
		return address;
	}


	public void setAddress(CompanyAddress address) {
		this.address = address;
	}


	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}


	public String getCompanyAlternatePhone() {
		return companyAlternatePhone;
	}


	public void setCompanyAlternatePhone(String companyAlternatePhone) {
		this.companyAlternatePhone = companyAlternatePhone;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyAlternateEmail() {
		return companyAlternateEmail;
	}

	public void setCompanyAlternateEmail(String companyAlternateEmail) {
		this.companyAlternateEmail = companyAlternateEmail;
	}

	public Integer getStoreCount() {
		return storeCount;
	}


	public void setStoreCount(Integer storeCount) {
		this.storeCount = storeCount;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifyBy() {
		return modifyBy;
	}


	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}


	public String getModifyDate() {
		return modifyDate;
	}


	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}


	@Embeddable
    public static class CompanyAddress{
    	private String companyStreet;
        private String companyArea;
        private String companyCity;
        private String companyPinCode;
		public String getCompanyStreet() {
			return companyStreet;
		}
		public void setCompanyStreet(String companyStreet) {
			this.companyStreet = companyStreet;
		}
		public String getCompanyArea() {
			return companyArea;
		}
		public void setCompanyArea(String companyArea) {
			this.companyArea = companyArea;
		}
		public String getCompanyCity() {
			return companyCity;
		}
		public void setCompanyCity(String companyCity) {
			this.companyCity = companyCity;
		}
		public String getCompanyPinCode() {
			return companyPinCode;
		}
		public void setCompanyPinCode(String companyPinCode) {
			this.companyPinCode = companyPinCode;
		}
        
        
    }
}
