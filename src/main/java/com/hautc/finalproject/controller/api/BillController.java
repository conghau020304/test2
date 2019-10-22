package com.hautc.finalproject.controller.api;

import com.hautc.finalproject.model.Bill;
import com.hautc.finalproject.service.IBillService;
import com.hautc.finalproject.service.IProductBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user/api/bills")
public class BillController {

    @Autowired
    private IBillService billService;

    @Autowired
    IProductBillService productBillService;

    @GetMapping("")
    public List<Bill> getAllBill() {
        return billService.getAllBill();
    }

    @PostMapping("")
    public Bill insertBill(@Valid @RequestBody Bill bill) {
        return billService.insertBill(bill);
    }

    @GetMapping(value = "/{billId}")
    public ResponseEntity<Bill> getBillById(@PathVariable(value = "billId") Integer billId) {
        Bill bill = billService.findByBillId(billId);
        if (bill == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(bill);
    }

    @PutMapping("/{billId}")
    public ResponseEntity<Bill> updateBill(@PathVariable(value = "billId") Integer billId, @RequestBody Bill billDetails) {
        Bill bill = billService.findByBillId(billId);
        if (bill == null) {
            return ResponseEntity.notFound().build();
        }
        bill.setProductBillList(billDetails.getProductBillList());
        bill.setCustomerAddress(billDetails.getCustomerAddress());
        bill.setCustomerDateOfBirth(billDetails.getCustomerDateOfBirth());
        bill.setCustomerName(billDetails.getCustomerName());
        bill.setCustomerPhone(billDetails.getCustomerPhone());
        bill.setNote(bill.getNote());
        Bill billUpdate = billService.updateBill(bill);
        return ResponseEntity.ok().body(billUpdate);
    }

    @DeleteMapping(value = "/{billId}")
    public ResponseEntity deleteBill(@PathVariable(value = "billId") Integer billId) {
        Bill bill = billService.findByBillId(billId);
        if (bill == null) {
            return ResponseEntity.notFound().build();
        }
        billService.deleteBill(billId);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
