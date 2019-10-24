package com.thoughtworks.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RecipientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String receiverFullName;

    public RecipientModel() {
    }

    public RecipientModel(long receiverID, String receiverFullName) {
        this.id = receiverID;
        this.receiverFullName = receiverFullName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReceiverFullName() {
        return receiverFullName;
    }

    public void setReceiverFullName(String receiverFullName) {
        this.receiverFullName = receiverFullName;
    }
}
