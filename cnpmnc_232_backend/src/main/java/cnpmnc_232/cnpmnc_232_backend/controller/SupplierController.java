package cnpmnc_232.cnpmnc_232_backend.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@Setter
@AllArgsConstructor
@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @GetMapping("/order")
    public ResponseEntity<String> getOrder() {
        return ResponseEntity.ok("order");
    }
}
