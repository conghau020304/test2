package com.hautc.finalproject.model;

import java.io.Serializable;

public class PKProductBill implements Serializable {
    private String productId;
    private Integer billId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public PKProductBill(String productId, Integer billId) {
        this.productId = productId;
        this.billId = billId;
    }

    public PKProductBill() {
    }
}
