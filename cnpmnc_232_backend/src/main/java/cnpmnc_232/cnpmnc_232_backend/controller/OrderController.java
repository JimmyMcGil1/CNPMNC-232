package cnpmnc_232.cnpmnc_232_backend.controller;

import cnpmnc_232.cnpmnc_232_backend.dto.request.OrderDto;
import cnpmnc_232.cnpmnc_232_backend.dto.response.OrderRespDto;
import cnpmnc_232.cnpmnc_232_backend.dto.response.StatusRespDto;
import cnpmnc_232.cnpmnc_232_backend.entity.Order;
import cnpmnc_232.cnpmnc_232_backend.entity.Supplier;
import cnpmnc_232.cnpmnc_232_backend.repository.OrderRepository;
import cnpmnc_232.cnpmnc_232_backend.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/orders")
public class OrderController {
    OrderRepository orderRepo;
    SupplierRepository suppRepo;


    @GetMapping("/all")
    public ResponseEntity<?> allOrders() {
        try {
            List<Order> orders = orderRepo.findAll();
            List<OrderRespDto> ordersDto = orders.stream().map(order ->
                    new OrderRespDto(order.getId(), order.getOrderDate(), order.getStatusOrder(),
                            order.getDeposit(), order.getSupplier().getName(), order.getTotalCost())).collect(Collectors.toList());

            return new ResponseEntity<>(ordersDto, HttpStatus.OK);
        } catch (Exception e) {
            Dictionary<String, String> data = new Hashtable<>();
            data.put("message", "fail");
            data.put("error", e.getMessage());
            return new ResponseEntity<>(data, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody OrderDto dto) {
        Optional<Supplier> supplier = suppRepo.findById(dto.getSupplier());
        if (supplier.isPresent()) {
            try {
                Order newOrd = new Order(dto.getOrderDate(), supplier.get(), dto.getDeposit());
                orderRepo.save(newOrd);
                Dictionary<String, String> data = new Hashtable<>();
                data.put("message", "success");
                data.put("id", newOrd.getId().toString());
                return new ResponseEntity<>(data, HttpStatus.OK);
            } catch (Exception e) {
                Dictionary<String, String> data = new Hashtable<>();
                data.put("message", "fail");
                data.put("error", e.getMessage());
                return new ResponseEntity<>(data, HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>("save fail, no supplier found", HttpStatus.OK);
        }
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteOrder(@RequestParam String orderId) {
        try {
            orderRepo.deleteOrderById(Integer.parseInt(orderId));
            StatusRespDto dto = new StatusRespDto("success", "");
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            StatusRespDto dto = new StatusRespDto("fail", e.getMessage());
            return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/total-cost")
    public ResponseEntity<?> calTotalCost(@RequestParam Integer orderId) {
        try {
            orderRepo.calToalCost(orderId);
            Optional<Order> getOrder = orderRepo.findById(orderId);
            Order order = getOrder.get();
            OrderRespDto respDto = new OrderRespDto(order.getId(), order.getOrderDate(), order.getStatusOrder(),
                    order.getDeposit(), order.getSupplier().getName(), order.getTotalCost());
            return new ResponseEntity<>(respDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Fail to calculate order total cost:" + e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Integer id) {
        Optional<Order> order = orderRepo.findById(id);
        if (order.isPresent()) {
            Order orderData = order.get();
            OrderRespDto data = new OrderRespDto(orderData.getId(), orderData.getOrderDate(), orderData.getStatusOrder(), orderData.getDeposit()
                    , orderData.getSupplier().getName(), orderData.getTotalCost());
            return new ResponseEntity<>(data, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("can not found order: " + id, HttpStatus.NOT_FOUND);
        }
    }


}
