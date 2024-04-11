package cnpmnc_232.cnpmnc_232_backend.controller;

import cnpmnc_232.cnpmnc_232_backend.dto.request.ItemDto;
import cnpmnc_232.cnpmnc_232_backend.dto.response.StatusRespDto;
import cnpmnc_232.cnpmnc_232_backend.dto.response.UpdateObjectRespDto;
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
        try {
            Item newItem = new Item(dto.getItem_name(), dto.getSize());
            Item savedIt = itemRepo.save(newItem);
            UpdateObjectRespDto resp_dto = new UpdateObjectRespDto("success", savedIt.getId(), "");
            return new ResponseEntity<>(resp_dto, HttpStatus.OK);
        } catch (Exception e) {
            StatusRespDto respDto = new StatusRespDto("fail", e.getMessage());
            return new ResponseEntity<>(respDto, HttpStatus.CONFLICT);
        }
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
