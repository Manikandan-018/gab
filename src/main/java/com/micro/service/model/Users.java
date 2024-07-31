package com.micro.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class Users {

    @Id
    private String id;
    private String companyId; 
    private String storeId;
    private String userName;
    private String userPassword;
    private String userRole;
    private UserAddress address;
    private String userPhone;
    private String userAlternatePhone;
    private String userEmail;
    private String userAlternateEmail;
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

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public UserAddress getAddress() {
        return address;
    }

    public void setAddress(UserAddress address) {
        this.address = address;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAlternatePhone() {
        return userAlternatePhone;
    }

    public void setUserAlternatePhone(String userAlternatePhone) {
        this.userAlternatePhone = userAlternatePhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAlternateEmail() {
        return userAlternateEmail;
    }

    public void setUserAlternateEmail(String userAlternateEmail) {
        this.userAlternateEmail = userAlternateEmail;
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

   
    public static class UserAddress {
        private String userStreet;
        private String userArea;
        private String userCity;
        private String userPinCode;

        
        public String getUserStreet() {
            return userStreet;
        }

        public void setUserStreet(String userStreet) {
            this.userStreet = userStreet;
        }

        public String getUserArea() {
            return userArea;
        }

        public void setUserArea(String userArea) {
            this.userArea = userArea;
        }

        public String getUserCity() {
            return userCity;
        }

        public void setUserCity(String userCity) {
            this.userCity = userCity;
        }

        public String getUserPinCode() {
            return userPinCode;
        }

        public void setUserPinCode(String userPinCode) {
            this.userPinCode = userPinCode;
        }
    }
}
