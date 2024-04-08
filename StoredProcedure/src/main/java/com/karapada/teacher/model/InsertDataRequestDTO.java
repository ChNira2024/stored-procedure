package com.karapada.teacher.model;


public class InsertDataRequestDTO {

    private String systemID;
    private String inventoryId;
    private String unitName;
    private String address;
    private String names;

    // Default constructor
    public InsertDataRequestDTO() {
    }

    // Constructor with fields
    public InsertDataRequestDTO(String systemID, String inventoryId, String unitName, String address, String names) {
        this.systemID = systemID;
        this.inventoryId = inventoryId;
        this.unitName = unitName;
        this.address = address;
        this.names = names;
    }

    // Getters and setters
    public String getSystemID() {
        return systemID;
    }

    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
}
