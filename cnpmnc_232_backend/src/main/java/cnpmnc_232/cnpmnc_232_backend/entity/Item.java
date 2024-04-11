package cnpmnc_232.cnpmnc_232_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Table(name = "ITEM")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ITEM_NAME")
    private String item_name;
    @Column(name = "Size")
    private Integer size;
    public Item(String name, Integer size) {
        this.item_name = name;
        this.size = size;
    }
}
