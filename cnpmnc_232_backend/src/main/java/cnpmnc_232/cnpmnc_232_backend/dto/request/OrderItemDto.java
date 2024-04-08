package cnpmnc_232.cnpmnc_232_backend.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    @NotEmpty
    Integer itemId;
    @NotEmpty
    Integer orderId;
    @NotEmpty
    Float salePrice;
    @NotEmpty
    Integer amount;
}
