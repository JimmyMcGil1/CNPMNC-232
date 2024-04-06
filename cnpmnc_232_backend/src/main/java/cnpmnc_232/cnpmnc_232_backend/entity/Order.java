package cnpmnc_232.cnpmnc_232_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "ORDER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate orderDate;
    @OneToOne(cascade = CascadeType.ALL, targetEntity = Supplier.class)
    private Long supplier;
    private Float deposit;
    private Boolean statusOrder;
    public Order(String localDate, Long supplier, Float deposit) {
        this.orderDate = LocalDate.parse(localDate);
        this.supplier = supplier;
        this.deposit = deposit;
        this.statusOrder = true;
    }
}
