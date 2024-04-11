package cnpmnc_232.cnpmnc_232_backend.controller;

import cnpmnc_232.cnpmnc_232_backend.dto.request.SupplierDto;
import cnpmnc_232.cnpmnc_232_backend.dto.response.SupplierRespDto;
import cnpmnc_232.cnpmnc_232_backend.entity.Order;
import cnpmnc_232.cnpmnc_232_backend.entity.Supplier;
import cnpmnc_232.cnpmnc_232_backend.repository.OrderRepository;
import cnpmnc_232.cnpmnc_232_backend.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    private OrderRepository orderRepo;
    private SupplierRepository supplierRepo;


    @PostMapping("/add")
    public ResponseEntity<?> addSupplier(@RequestBody SupplierDto dto) {
        Supplier newSupplier = new Supplier(dto.getName(), dto.getAddress(), dto.getPhone(), dto.getEmail());
        supplierRepo.save(newSupplier);
        return new ResponseEntity<>("save supplier success", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllSuppliers() {
        try {
            List<Supplier> getSupps = supplierRepo.findAll();
            List<SupplierRespDto> suppsDto = getSupps.stream()
                    .map(supp -> {
                        List<Integer> ordersId = supp.getOrders().stream().map(Order::getId).toList();
                        return new SupplierRespDto(supp.getId(), supp.getName(), supp.getAddress(), supp.getPhone(),
                                supp.getEmail(), ordersId);
                    })
                    .toList();

            return new ResponseEntity<>(suppsDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Fail to get all supplier:" + e.getMessage(), HttpStatus.OK);
        }
    }


}
