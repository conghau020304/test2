package com.hautc.finalproject.service.impl;

import com.hautc.finalproject.dto.StatisticalDTO;
import com.hautc.finalproject.model.Product;
import com.hautc.finalproject.repository.IProductRepository;
import com.hautc.finalproject.service.IProductService;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service("productServiceImpl")
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProductById(String productId) {
        return productRepository.findById(productId);
    }

    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product insertAndUpdateProduct(Product product) {
        LocalDateTime now = LocalDateTime.now();
        product.setDateAdded(now);
        return productRepository.save(product);
    }

    @Override
    public void updateQuantity(Integer quantity, String productId) {
        productRepository.updateQuantity(quantity, productId);
    }

    @Override
    public List<Product> findByIdOrName(String name, String id) {
        return productRepository.findByProductNameContainingOrProductIdContaining(name, id);
    }

    @Override
    public List<StatisticalDTO> getAllStatistical(String year) {
        return productRepository.getAllStatistical(year);
    }

    @Override
    public List<StatisticalDTO> getStatisticalByMonth(String month, String year) {
        return productRepository.getStatisticalByMonth(month, year);
    }

    @Override
    public Map<String, Object> parameters(String month, List<StatisticalDTO> statisticalDTOList) {
        LocalDateTime now = LocalDateTime.now();
        Map<String, Object> item = new HashMap<String, Object>();
        item.put("mainDataSoucre", new JRBeanCollectionDataSource(statisticalDTOList));
        item.put("month", month);
        item.put("now", now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        item.put("total", (int)statisticalDTOList.stream().mapToLong(t -> t.getQuantity()).sum());
        return item;
    }
}
