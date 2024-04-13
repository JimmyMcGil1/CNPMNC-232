package cnpmnc_232.cnpmnc_232_backend.controller;

import cnpmnc_232.cnpmnc_232_backend.dto.request.OrderDto;
import cnpmnc_232.cnpmnc_232_backend.dto.response.OrderRespDto;
import cnpmnc_232.cnpmnc_232_backend.entity.Invoice;
import cnpmnc_232.cnpmnc_232_backend.entity.Order;
import cnpmnc_232.cnpmnc_232_backend.entity.Supplier;
import cnpmnc_232.cnpmnc_232_backend.repository.InvoiceRepository;
import cnpmnc_232.cnpmnc_232_backend.repository.OrderRepository;
import cnpmnc_232.cnpmnc_232_backend.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    InvoiceRepository invoiceRepo;


    @GetMapping("/all")
    public ResponseEntity<?> allOrders() {
        try {
            List<Order> orders = orderRepo.findAll();
            List<OrderRespDto> ordersDto = orders.stream().map(order -> {
                Boolean haveInvoice = false;
                Optional<Invoice> getInvoice = invoiceRepo.findByOrderId(order.getId());
                if (getInvoice.isPresent()) haveInvoice = true;
                return new OrderRespDto(order.getId(), order.getOrderDate(), order.getStatusOrder(),
                        order.getDeposit(), order.getSupplier().getName(), order.getTotalCost(), haveInvoice);
            }).collect(Collectors.toList());

            return new ResponseEntity<>(ordersDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Fail to get orders:" + e.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody OrderDto dto) {
        Optional<Supplier> supplier = suppRepo.findById(dto.getSupplier());
        if (supplier.isPresent()) {
            Order newOrd = new Order(dto.getOrderDate(), supplier.get(), dto.getDeposit());
            orderRepo.save(newOrd);
            Map<String, Long> responseData = new HashMap<>();
            responseData.put("newOrderId", Long.valueOf(newOrd.getId()));
            return ResponseEntity.ok().body(responseData);
        } else {
            return new ResponseEntity<>("save fail, no supplier found", HttpStatus.OK);
        }
    }


    @GetMapping("/delete")
    public ResponseEntity<?> deleteOrder(@RequestParam String orderId) {
        try {
            orderRepo.deleteOrderById(Integer.parseInt(orderId));
            return new ResponseEntity<>("Delete order " + orderId + " success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Fail to delete order " + orderId + ":" + e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/total-cost")
    public ResponseEntity<?> calTotalCost(@RequestParam Integer orderId) {
        try {
            orderRepo.calToalCost(orderId);
            Optional<Order> getOrder = orderRepo.findById(orderId);
            Order order = getOrder.get();
            Boolean haveInvoice = isHaveInvoice(order.getId());
            OrderRespDto respDto = new OrderRespDto(order.getId(), order.getOrderDate(), order.getStatusOrder(),
                    order.getDeposit(), order.getSupplier().getName(), order.getTotalCost(), haveInvoice);
            return new ResponseEntity<>(respDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Fail to calculate order total cost:" + e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    boolean isHaveInvoice(Integer orderId) {
        Optional<Invoice> getInvoice = invoiceRepo.findByOrderId(orderId);
        if (getInvoice.isPresent()) return true;
        return false;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Integer id) {
        Optional<Order> order = orderRepo.findById(id);
        if (order.isPresent()) {
            Order orderData = order.get();
            Boolean haveInvoice = isHaveInvoice(order.get().getId());
            OrderRespDto data = new OrderRespDto(orderData.getId(), orderData.getOrderDate(), orderData.getStatusOrder(), orderData.getDeposit()
                    , orderData.getSupplier().getName(), orderData.getTotalCost(), haveInvoice);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("can not found order: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
