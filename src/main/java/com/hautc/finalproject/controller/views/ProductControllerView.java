package com.hautc.finalproject.controller.views;

import com.hautc.finalproject.model.Product;
import com.hautc.finalproject.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin
@Controller
@RequestMapping("/user")
public class ProductControllerView {

    @Autowired
    public IProductService productService;

    @RequestMapping( value = "/product-list", method = RequestMethod.GET)
    public String showProducts(){
        return "productList";
    }


}
