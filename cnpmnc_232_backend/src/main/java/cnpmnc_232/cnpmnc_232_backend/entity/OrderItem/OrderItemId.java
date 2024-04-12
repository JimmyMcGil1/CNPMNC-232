package cnpmnc_232.cnpmnc_232_backend.entity.OrderItem;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemId implements Serializable {
    Integer idItem;
    Integer idOrder;
}
