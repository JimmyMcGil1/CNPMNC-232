package cnpmnc_232.cnpmnc_232_backend.controller;

import cnpmnc_232.cnpmnc_232_backend.dto.request.OrderDto;
import cnpmnc_232.cnpmnc_232_backend.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Getter
@Setter
@AllArgsConstructor
@Controller
@RequestMapping("/api/orders")
public class OrderController {
    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody OrderDto dto) {
//        Order newOrd = new Order(dt)
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}