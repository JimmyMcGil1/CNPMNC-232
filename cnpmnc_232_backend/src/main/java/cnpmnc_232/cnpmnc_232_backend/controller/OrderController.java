package cnpmnc_232.cnpmnc_232_backend.controller;

import cnpmnc_232.cnpmnc_232_backend.dto.request.OrderDto;
import cnpmnc_232.cnpmnc_232_backend.dto.response.OrderRespDto;
import cnpmnc_232.cnpmnc_232_backend.entity.Order;
import cnpmnc_232.cnpmnc_232_backend.entity.Supplier;
import cnpmnc_232.cnpmnc_232_backend.repository.OrderRepository;
import cnpmnc_232.cnpmnc_232_backend.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@Controller
@RequestMapping("/api/orders")
public class OrderController {
    OrderRepository orderRepo;
    SupplierRepository suppRepo;

    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody OrderDto dto) {
        Optional<Supplier> supplier = suppRepo.findById(dto.getSupplierId());
        if (supplier.isPresent()) {
            Order newOrd = new Order(dto.getOrderDate(), supplier.get(), dto.getDeposit());
            orderRepo.save(newOrd);
            return new ResponseEntity<>("save " + newOrd.getId() + " success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("save fail, no supplier found", HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Integer id) {
        Optional<Order> order = orderRepo.findById(id);
        if (order.isPresent()) {
            Order orderData = order.get();
            OrderRespDto data = new OrderRespDto(orderData.getId(), orderData.getOrderDate(), orderData.getStatusOrder(), orderData.getDeposit()
                    , orderData.getSupplier().getId(), orderData.getTotalCost());
            return new ResponseEntity<>(data, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("can not foud order: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
