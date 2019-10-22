package com.hautc.finalproject.service;

import com.hautc.finalproject.dto.StatisticalDTO;
import com.hautc.finalproject.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IProductService {
    List<Product> getAllProduct();

    Optional<Product> findProductById(String productId);

    void deleteProduct(String productId);

    Product insertAndUpdateProduct(Product product);

    void updateQuantity(Integer quantity, String productId);

    List<Product> findByIdOrName(String name, String id);

    List<StatisticalDTO> getAllStatistical(String year);

    List<StatisticalDTO> getStatisticalByMonth(String month, String year);

    public Map<String, Object> parameters(String month, List<StatisticalDTO> statisticalDTOList);
}
