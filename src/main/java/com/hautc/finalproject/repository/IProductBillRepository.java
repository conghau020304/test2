package com.hautc.finalproject.repository;

import com.hautc.finalproject.model.PKProductBill;
import com.hautc.finalproject.model.ProductBill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductBillRepository extends JpaRepository<ProductBill, PKProductBill> {
    ProductBill findByProductIdAndBillId(String productId, Integer billId);

}
