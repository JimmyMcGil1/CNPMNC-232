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
import java.util.Optional;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    InvoiceRepository invoiceRepo;
    OrderRepository orderRepo;
    @PostMapping("/add")
    private ResponseEntity<?> addInvoice(@RequestParam  Integer orderId) {
        Optional<Order> order = orderRepo.findById(orderId);
        if (order.isPresent()) {
            Float totalCost = order.get().getTotalCost(); //may be change the way calculating total cost
            Float changeAmount = order.get().getDeposit() > totalCost ? order.get().getDeposit() - totalCost : 0;
            Invoice newInvoice = new Invoice(order.get(), LocalDate.now(),totalCost, changeAmount);
            try {
                invoiceRepo.save(newInvoice);
                return new ResponseEntity<>("add invoice for order " + orderId + " success", HttpStatus.CREATED);
            }
            catch (Exception e) {
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
