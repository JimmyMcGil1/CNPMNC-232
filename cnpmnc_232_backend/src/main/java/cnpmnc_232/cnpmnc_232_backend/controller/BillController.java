package cnpmnc_232.cnpmnc_232_backend.controller;

import cnpmnc_232.cnpmnc_232_backend.dto.request.BillDto;
import cnpmnc_232.cnpmnc_232_backend.entity.Bill;
import cnpmnc_232.cnpmnc_232_backend.entity.Order;
import cnpmnc_232.cnpmnc_232_backend.repository.BillRepository;
import cnpmnc_232.cnpmnc_232_backend.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping("/api/bill")
public class BillController {
    private BillRepository billRepo;
    private OrderRepository orderRepo;

    @PostMapping("/add")
    private ResponseEntity<?> addBill(@RequestBody BillDto dto) {
        Optional<Order> order = orderRepo.findById(dto.getIdOrder());
        if (order.isPresent()) {
            Float totalCost = order.get().getTotalCost(); //may be change the way calculating total cost
            Bill newBill = new Bill(order.get(), LocalDate.now(), dto.getDeadline(), totalCost);
            try {
                billRepo.save(newBill);
                return new ResponseEntity<>("add bill for order " + dto.getIdOrder() + " success", HttpStatus.CREATED);
            }
            catch (Exception e) {
                return new ResponseEntity<>("fail to add bill:" + e.getMessage(), HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>("fail to add bill: no order " + dto.getIdOrder() + " existed", HttpStatus.CONFLICT);
        }
    }
}
