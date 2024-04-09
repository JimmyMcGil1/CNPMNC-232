package cnpmnc_232.cnpmnc_232_backend.controller;

import cnpmnc_232.cnpmnc_232_backend.dto.request.ItemDto;
import cnpmnc_232.cnpmnc_232_backend.entity.Item;
import cnpmnc_232.cnpmnc_232_backend.repository.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@AllArgsConstructor
@Controller
@RequestMapping("/api/items")
public class ItemController {
    private ItemRepository itemRepo;

    @PostMapping("/add")
    public ResponseEntity<?> addItems(@RequestBody ItemDto dto) {
        Item newItem = new Item(dto.getId(), dto.getItem_name(), dto.getSize());
        itemRepo.save(newItem);
        return new ResponseEntity<>("save success", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<Item> rtLst = new ArrayList<>();
        rtLst = StreamSupport.stream(itemRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return new ResponseEntity<>(rtLst, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItem(@PathVariable Integer id) {
        System.out.println("Id:" + id);
        Optional<Item> order = itemRepo.findById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);

    }
}
