package com.hautc.finalproject.repository;

import com.hautc.finalproject.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IBillRepository extends JpaRepository<Bill, Integer> {
    List<Bill> findByCustomerNameContainingOrCustomerPhoneContaining(String name, String phone);
}
