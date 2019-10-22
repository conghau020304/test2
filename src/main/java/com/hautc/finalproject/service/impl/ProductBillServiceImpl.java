package com.hautc.finalproject.service.impl;

import com.hautc.finalproject.model.ProductBill;
import com.hautc.finalproject.repository.IProductBillRepository;
import com.hautc.finalproject.repository.IProductRepository;
import com.hautc.finalproject.service.IProductBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productBillServiceImpl")
public class ProductBillServiceImpl implements IProductBillService {

    @Autowired
    private IProductBillRepository productBillRepository;

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<ProductBill> insertProductBill(List<ProductBill> productBills) {
        productBills.forEach(p -> {
            ProductBill productBill = productBillRepository.save(p);
            if(productBill != null){
                productRepository.updateQuantity(productBill.getQuantity(),productBill.getProductId());
            }
        });
        return productBills;
    }

    @Override
    public ProductBill findByProductIdAndBillId(String productId, Integer billId) {
        return productBillRepository.findByProductIdAndBillId(productId,billId);
    }

}
