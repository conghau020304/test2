package com.hautc.finalproject.service;

import com.hautc.finalproject.model.Bill;

import java.util.List;

public interface IBillService {
    List<Bill> getAllBill();

    Bill findByBillId(Integer billId);

    void deleteBill(Integer billId);

    Bill insertBill(Bill bill);

    Bill updateBill(Bill bill);

    List<Bill> findByNameOrPhone(String name, String phone);
}
