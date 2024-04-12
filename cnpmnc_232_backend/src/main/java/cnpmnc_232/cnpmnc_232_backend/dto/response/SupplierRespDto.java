package cnpmnc_232.cnpmnc_232_backend.dto.response;

import cnpmnc_232.cnpmnc_232_backend.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class SupplierRespDto {
    Integer id;
    String name;
    private String address;
    private String phone;
    private String email;
    private List<Integer> ordersId;
}
