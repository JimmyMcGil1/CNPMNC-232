package cnpmnc_232.cnpmnc_232_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BILL")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @OneToOne(targetEntity = Order.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ORDER")
    Order order;

    @Column(name = "BILL_DATE")
    LocalDate createDate;

    @Column(name = "DEADLINE")
    LocalDate deadLineDate;

    @Column(name = "TOTAL_COST")
    Float totalCost;
    public Bill(Order order, LocalDate createDate, LocalDate deadline, Float totalCost) {
        this.order = order;
        this.createDate = createDate;
        this.deadLineDate = deadline;
        this.totalCost = totalCost;
    }

}
