package cnpmnc_232.cnpmnc_232_backend.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeleteOrderItem {
    @NotEmpty
    Integer itemId;
    @NotEmpty
    Integer orderId;
}
