package cnpmnc_232.cnpmnc_232_backend.controller;

import cnpmnc_232.cnpmnc_232_backend.entity.Bill;
import cnpmnc_232.cnpmnc_232_backend.entity.Invoice;
import cnpmnc_232.cnpmnc_232_backend.entity.Order;
import cnpmnc_232.cnpmnc_232_backend.repository.InvoiceRepository;
import cnpmnc_232.cnpmnc_232_backend.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    InvoiceRepository invoiceRepo;
    OrderRepository orderRepo;


    @PostMapping("/add")
    private ResponseEntity<?> addInvoice(@RequestParam Integer orderId) {
        Optional<Order> order = orderRepo.findById(orderId);
        if (order.isPresent()) {
            Float totalCost = order.get().getTotalCost();
            Float changeAmount = order.get().getDeposit() > totalCost ? order.get().getDeposit() - totalCost : 0;
            Invoice newInvoice = new Invoice(order.get(), LocalDate.now(), totalCost);
            try {
                invoiceRepo.save(newInvoice);
                Map<String, Integer> responseData = new HashMap<>();
                responseData.put("orderId", orderId);
                return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
            } catch (Exception e) {
                return new ResponseEntity<>("fail to add bill:" + e.getMessage(), HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>("fail to add bill: no order " + orderId + " existed", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/total-invoice-amount")
    private ResponseEntity<?> getTotalInvoiceAmount() {
        Float totalInvoiceAmount = 0.0f;
        for (Invoice invoice : invoiceRepo.findAll()) {
            totalInvoiceAmount += invoice.getTotalCost();
        }
        return new ResponseEntity<>(totalInvoiceAmount, HttpStatus.OK);
    }
}
