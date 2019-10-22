package com.hautc.finalproject.dto;

public class StatisticalDTO {
    private String productId;
    private String productName;
    private Integer quantity;
    private String month;
    private String year;
    private Integer billId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public StatisticalDTO(String productId, String productName, Integer quantity, String month, String year, Integer billId) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.month = month;
        this.year = year;
        this.billId = billId;
    }

    public StatisticalDTO() {
    }

}
