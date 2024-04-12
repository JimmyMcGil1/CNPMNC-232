package cnpmnc_232.cnpmnc_232_backend.controller;

import cnpmnc_232.cnpmnc_232_backend.dto.request.BillDto;
import cnpmnc_232.cnpmnc_232_backend.dto.response.StatusRespDto;
import cnpmnc_232.cnpmnc_232_backend.dto.response.UpdateObjectRespDto;
import cnpmnc_232.cnpmnc_232_backend.entity.Bill;
import cnpmnc_232.cnpmnc_232_backend.entity.Order;
import cnpmnc_232.cnpmnc_232_backend.repository.BillRepository;
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
                UpdateObjectRespDto respDto = new UpdateObjectRespDto("success", newBill.getId(), "");
                return new ResponseEntity<>(respDto, HttpStatus.CREATED);
            } catch (Exception e) {
                StatusRespDto respDto = new StatusRespDto("fail", e.getMessage());
                return new ResponseEntity<>(respDto, HttpStatus.CONFLICT);
            }
        } else {
            StatusRespDto respDto = new StatusRespDto("fail", "unknown error");
            return new ResponseEntity<>(respDto, HttpStatus.CONFLICT);
        }
    }
}
