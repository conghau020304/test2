package com.hautc.finalproject.service;

import com.hautc.finalproject.model.ProductBill;

import java.util.List;

public interface IProductBillService {
    List<ProductBill> insertProductBill(List<ProductBill> productBills);

    ProductBill findByProductIdAndBillId(String productId, Integer billId);

}
