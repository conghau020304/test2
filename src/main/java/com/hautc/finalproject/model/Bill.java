package com.hautc.finalproject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer billId;

    @Size(min = 5, message = "{bill.CustomerName.Size.min}")
    @Size(max = 50, message = "{bill.CustomerName.Size.max}")
    private String customerName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate customerDateOfBirth;

    @Size(min = 10, message = "{bill.CustomerPhone.Size.min}")
    @Size(max = 12, message = "{bill.CustomerPhone.Size.max}")
    private String customerPhone;

    @NotNull
    private String customerAddress;

    private  String note;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOfSell;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "bill")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
   // @JsonIgnore
    private List<ProductBill> productBillList = new ArrayList<>();

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getCustomerDateOfBirth() {
        return customerDateOfBirth;
    }

    public void setCustomerDateOfBirth(LocalDate customerDateOfBirth) {
        this.customerDateOfBirth = customerDateOfBirth;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getDateOfSell() {
        return dateOfSell;
    }

    public void setDateOfSell(LocalDateTime dateOfSell) {
        this.dateOfSell = dateOfSell;
    }

    public List<ProductBill> getProductBillList() {
        return productBillList;
    }

    public void setProductBillList(List<ProductBill> productBillList) {
        this.productBillList = productBillList;
    }

    public Long total(){
        long total = this.productBillList.stream().mapToLong(t -> t.getQuantity() * t.getPrice()).sum();
        return total;
    }
}
