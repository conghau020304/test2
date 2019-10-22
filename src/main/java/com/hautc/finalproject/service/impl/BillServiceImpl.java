package com.hautc.finalproject.service.impl;

import com.hautc.finalproject.model.Bill;
import com.hautc.finalproject.repository.IBillRepository;
import com.hautc.finalproject.service.IBillService;
import com.hautc.finalproject.service.IProductBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("billServiceImpl")
public class BillServiceImpl implements IBillService {

    @Autowired
    private IBillRepository billRepository;

    @Autowired
    private IProductBillService productBillService;

    @Override
    public List<Bill> getAllBill() {
        return billRepository.findAll();
    }

    @Override
    public Bill findByBillId(Integer billId) {
        return billRepository.getOne(billId);
    }

    @Override
    public void deleteBill(Integer billId) {
        billRepository.deleteById(billId);
    }

    @Override
    public Bill insertBill(Bill bill) {
        LocalDateTime now = LocalDateTime.now();
        bill.setDateOfSell(now);
        Bill b = billRepository.save(bill);
        b.getProductBillList().forEach(d -> d.setBillId(b.getBillId()));
        productBillService.insertProductBill(b.getProductBillList());
        return b;
    }

    @Override
    public Bill updateBill(Bill bill) {
        productBillService.insertProductBill(bill.getProductBillList());
        return  billRepository.save(bill);
    }

    @Override
    public List<Bill> findByNameOrPhone(String name, String phone) {
        return billRepository.findByCustomerNameContainingOrCustomerPhoneContaining(name, phone);
    }
}
