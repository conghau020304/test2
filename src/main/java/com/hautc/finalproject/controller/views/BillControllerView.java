package com.hautc.finalproject.controller.views;

import com.hautc.finalproject.model.Bill;
import com.hautc.finalproject.service.IBillService;
import com.hautc.finalproject.service.IProductService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/user")
public class BillControllerView {

    @Autowired
    private IBillService billService;

    @Autowired
    private IProductService productService;

    private static final String LIST_BILL = "bills";

    private static final String LIST_PRODUCT = "products";

    @RequestMapping(value = "/create-bill", method = RequestMethod.GET)
    public String formCreateBill(Model model) {
        model.addAttribute(LIST_PRODUCT, productService.getAllProduct());
        return "createBill";
    }

    @RequestMapping(value = "/bill-list", method = RequestMethod.GET)
    public String showBills(@RequestParam(value = "tk", name = "tk",required = false) String tk, Model model) {
        List<Bill> list;
       if(tk != null){
            list = billService.findByNameOrPhone(tk,tk);
        }else {
           list = billService.getAllBill();
       }
        model.addAttribute(LIST_BILL, list);
        return "billList";
    }

    @RequestMapping(value = "/delete-bill/{billId}", method = RequestMethod.GET)
    public String deleteBill(@PathVariable(value = "billId") Integer billId, Model model) {
        Bill bill = billService.findByBillId(billId);
        if (bill != null) {
            billService.deleteBill(billId);
            return "redirect:/user/bill-list";
        }
        model.addAttribute("errorMes", true);
        model.addAttribute(LIST_BILL, billService.getAllBill());
        return "billList";
    }
}
