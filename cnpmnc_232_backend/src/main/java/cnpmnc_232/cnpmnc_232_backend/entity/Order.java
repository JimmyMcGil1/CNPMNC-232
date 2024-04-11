package cnpmnc_232.cnpmnc_232_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ORDER_")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ORDER_DATE")
    private LocalDate orderDate;

    @ManyToOne(cascade = CascadeType.ALL,targetEntity = Supplier.class)
    @JoinColumn(name = "ID_SUPPLIER", nullable = true)
    private Supplier supplier;

    @Column(name = "DEPOSIT")
    private Float deposit;

    @Column(name = "STATUS_ORDER")
    private Boolean statusOrder;

    @Column(name = "TOTAL_COST")
    private Float totalCost;

    public Order(LocalDate localDate, Supplier supplier, Float deposit) {
        this.orderDate = localDate;
        this.supplier = supplier;
        this.deposit = deposit;
        this.statusOrder = false;
        this.totalCost = 0f;
    }
}
