package com.micro.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stores")
public class Store {

    @Id
    private String id;
    private String companyId; 
    private String storeName;
    private StoreAddress address;
    private String storePhone;
    private String storeAlternatePhone;
    private String storeEmail;
    private String storeAlternateEmail;
    private String createdBy;
    private String createdDate;
    private String modifyBy;
    private String modifyDate;

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public StoreAddress getAddress() {
        return address;
    }

    public void setAddress(StoreAddress address) {
        this.address = address;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public String getStoreAlternatePhone() {
        return storeAlternatePhone;
    }

    public void setStoreAlternatePhone(String storeAlternatePhone) {
        this.storeAlternatePhone = storeAlternatePhone;
    }

    public String getStoreEmail() {
        return storeEmail;
    }

    public void setStoreEmail(String storeEmail) {
        this.storeEmail = storeEmail;
    }

    public String getStoreAlternateEmail() {
        return storeAlternateEmail;
    }

    public void setStoreAlternateEmail(String storeAlternateEmail) {
        this.storeAlternateEmail = storeAlternateEmail;
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

    public static class StoreAddress {
        private String storeStreet;
        private String storeArea;
        private String storeCity;
        private String storePinCode;

        

        public String getStoreStreet() {
            return storeStreet;
        }

        public void setStoreStreet(String storeStreet) {
            this.storeStreet = storeStreet;
        }

        public String getStoreArea() {
            return storeArea;
        }

        public void setStoreArea(String storeArea) {
            this.storeArea = storeArea;
        }

        public String getStoreCity() {
            return storeCity;
        }

        public void setStoreCity(String storeCity) {
            this.storeCity = storeCity;
        }

        public String getStorePinCode() {
            return storePinCode;
        }

        public void setStorePinCode(String storePinCode) {
            this.storePinCode = storePinCode;
        }
    }
}
