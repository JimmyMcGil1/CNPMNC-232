package cnpmnc_232.cnpmnc_232_backend.entity.OrderItem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(OrderItemId.class)
@Table(name = "ORDER_ITEM")
public class OrderItem {
    @Id
    @Column(name = "ID_ITEM")
    private Integer idItem;
    @Id
    @Column(name = "ID_ORDER")
    private Integer idOrder;
    @Column(name = "SALE_PRICE")
    Float salePrice;
    @Column(name = "AMOUNT")
    Integer amount;
}
