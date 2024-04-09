package cnpmnc_232.cnpmnc_232_backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDto {
    private LocalDate orderDate;
    private Integer supplier;
    private Float deposit;
}
