package cnpmnc_232.cnpmnc_232_backend.controller;

import cnpmnc_232.cnpmnc_232_backend.dto.request.ItemDto;
import cnpmnc_232.cnpmnc_232_backend.dto.request.OrderDto;
import cnpmnc_232.cnpmnc_232_backend.entity.Item;
import cnpmnc_232.cnpmnc_232_backend.repository.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
@RequestMapping("/api/items")
public class ItemController {
    private ItemRepository itemRepo;

    @PostMapping("/add")
    public ResponseEntity<?> addItems(@RequestBody ItemDto dto) {
        Item newItem = new Item(dto.getId(), dto.getName(), dto.getSizePurchase(),
                dto.getPrice());
        itemRepo.save(newItem);
        return new ResponseEntity<>("save " + newItem.getName() + " success", HttpStatus.OK);
    }
}
