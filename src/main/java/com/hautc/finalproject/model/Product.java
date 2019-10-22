package com.hautc.finalproject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
public class Product implements Serializable {

    @Id
    @Column(columnDefinition = "VARCHAR(50)")
    @Size(max = 50, message = "{product.ProductId.Size.max}")
    @Size(min = 2, message = "{product.ProductId.Size.min}")
    private String productId;

    @Column(columnDefinition = "VARCHAR(50)")
    @Size(max = 50, message = "{product.ProductName.Size.max}")
    @Size(min = 5, message = "{product.ProductName.Size.min}")
    private String productName;

    @Min(value = 1, message = "{product.Quantity.Min.value}")
    private int quantity;

    @Min(value = 1000, message = "{product.Price.Min.value}")
    private long price;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateAdded;

    @NotNull
    private String measureWord;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "product")
    @JsonIgnore
    List<ProductBill> productBillList;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public List<ProductBill> getProductBillList() {
        return productBillList;
    }

    public void setProductBillList(List<ProductBill> productBillList) {
        this.productBillList = productBillList;
    }

    public String getMeasureWord() {
        return measureWord;
    }

    public void setMeasureWord(String measureWord) {
        this.measureWord = measureWord;
    }

}
