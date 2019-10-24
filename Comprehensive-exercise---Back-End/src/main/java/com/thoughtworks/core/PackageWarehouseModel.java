package com.thoughtworks.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PackageWarehouseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long wayBillNumber;
    private String weight;
    private long receiverID;
    private String receiverFullName;
    private String phone;
    private String status;
    private String pickupTime;

    public PackageWarehouseModel(){

    }

    public PackageWarehouseModel(long wayBillNumber, String weight, String receiverName, String phone, String status, String pickupTime) {
        this.wayBillNumber = wayBillNumber;
        this.weight = weight;
        this.receiverFullName = receiverName;
        this.phone = phone;
        this.status = status;
        this.pickupTime = pickupTime;
    }

    public long getWayBillNumber() {
        return wayBillNumber;
    }

    public void setWayBillNumber(long wayBillNumber) {
        this.wayBillNumber = wayBillNumber;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getReceiverFullName() {
        return receiverFullName;
    }

    public void setReceiverFullName(String receiverFullName) {
        this.receiverFullName = receiverFullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public long getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(long receiverID) {
        this.receiverID = receiverID;
    }
}
