package cnpmnc_232.cnpmnc_232_backend.controller;

import cnpmnc_232.cnpmnc_232_backend.dto.request.SupplierDto;
import cnpmnc_232.cnpmnc_232_backend.entity.Supplier;
import cnpmnc_232.cnpmnc_232_backend.repository.ItemRepository;
import cnpmnc_232.cnpmnc_232_backend.repository.OrderRepository;
import cnpmnc_232.cnpmnc_232_backend.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@AllArgsConstructor
@Controller
@RequestMapping("/api/suppliers")
public class SupplierController {
    OrderRepository orderRepo;
    ItemRepository itemRepo;
    SupplierRepository supplierRepo;


    @PostMapping("/add")
    public ResponseEntity<?> addSupplier(@RequestBody SupplierDto dto) {
        Supplier newSupplier = new Supplier(dto.getName(), dto.getAddress(), dto.getPhone(), dto.getEmail());
        supplierRepo.save(newSupplier);
        return new ResponseEntity<>("save supplier success", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllSuppliers() {
        List<Supplier> rtnList = new ArrayList<>();
        rtnList = StreamSupport.stream(supplierRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }
}
