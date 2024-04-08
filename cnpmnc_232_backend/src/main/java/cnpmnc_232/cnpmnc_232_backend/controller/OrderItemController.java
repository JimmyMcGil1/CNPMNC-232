package cnpmnc_232.cnpmnc_232_backend.controller;

import cnpmnc_232.cnpmnc_232_backend.dto.request.DeleteOrderItem;
import cnpmnc_232.cnpmnc_232_backend.dto.request.OrderItemDto;
import cnpmnc_232.cnpmnc_232_backend.entity.OrderItem.OrderItem;
import cnpmnc_232.cnpmnc_232_backend.entity.OrderItem.OrderItemId;
import cnpmnc_232.cnpmnc_232_backend.repository.OrderItemRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/order-item/")
@RestController
public class OrderItemController {
    OrderItemRepository odsItemssRepo;

    @PostMapping("/add")
    public ResponseEntity<?> addItem(@RequestBody OrderItemDto dto) {
        OrderItem newOrIt = new OrderItem(dto.getItemId(), dto.getOrderId(),
                dto.getSalePrice(), dto.getAmount());
        try {
            this.odsItemssRepo.save(newOrIt);
            return new ResponseEntity<>("save new item for order success", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Fail to save item for order:" + e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deleteItem(@RequestBody DeleteOrderItem dto) {
       try {
        this.odsItemssRepo.removeItem(dto.getItemId(), dto.getOrderId());
        return new ResponseEntity<>("remove item " + dto.getItemId() + " from order " + dto.getOrderId() + " success",
                HttpStatus.OK);
       }
       catch (Exception e) {
           return new ResponseEntity<>("fail to remove: " + e.getMessage(), HttpStatus.CONFLICT);
       }
    }
    @GetMapping("/all/items/{id}")
    public ResponseEntity<?> getAllItems(@PathVariable Integer id) {
        try {
            List<OrderItem> orderItems = this.odsItemssRepo.findByOrder(id);
            return new ResponseEntity<>(orderItems, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Fail to get order item of order " + id + ":" + e.getMessage(), HttpStatus.OK);
        }

    }

}
