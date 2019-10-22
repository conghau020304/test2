package com.hautc.finalproject.repository;

import com.hautc.finalproject.dto.StatisticalDTO;
import com.hautc.finalproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, String> {

    List<Product> findByProductNameContainingOrProductIdContaining(String productName, String productId);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.quantity = p.quantity - ?1 WHERE p.productId = ?2")
    void updateQuantity(Integer quantity, String productId);

    @Query("SELECT new com.hautc.finalproject.dto.StatisticalDTO (d.productId, p.productName, d.quantity, SUBSTRING(b.dateOfSell,6,2) as month, SUBSTRING(b.dateOfSell,1,4) as year, b.billId) " +
            "FROM Product p INNER JOIN ProductBill d ON p.productId = d.productId " +
            "INNER JOIN Bill b ON d.billId = b.billId " +
            "WHERE SUBSTRING(b.dateOfSell,6,2) = ?1 AND SUBSTRING(b.dateOfSell,1,4) = ?2")
    List<StatisticalDTO> getStatisticalByMonth(String month, String year);

    @Query("SELECT new com.hautc.finalproject.dto.StatisticalDTO(d.productId, p.productName, d.quantity, SUBSTRING(b.dateOfSell,6,2) as month, SUBSTRING(b.dateOfSell,1,4) as year, b.billId) " +
            "FROM Product p INNER JOIN ProductBill d ON p.productId = d.productId " +
            "INNER JOIN Bill b ON d.billId = b.billId " +
            "WHERE SUBSTRING(b.dateOfSell,1,4) = ?1")
    List<StatisticalDTO> getAllStatistical(String year);
}
