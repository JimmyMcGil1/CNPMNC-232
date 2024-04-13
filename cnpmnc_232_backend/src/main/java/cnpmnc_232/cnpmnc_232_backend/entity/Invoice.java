package cnpmnc_232.cnpmnc_232_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "INVOICE")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @OneToOne(targetEntity = Order.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ORDER")
    Order order;

    @Column(name = "INVOICE_DATE")
    LocalDate invoiceDate;

    @Column(name = "TOTAL_COST")
    Float totalCost;

    public Invoice(Order order, LocalDate invoiceDate, Float totalCost) {
        this.order = order;
        this.invoiceDate = invoiceDate;
        this.totalCost = totalCost;
    }
}
