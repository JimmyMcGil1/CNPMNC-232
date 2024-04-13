package cnpmnc_232.cnpmnc_232_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class OrderRespDto {
    Integer id;
    LocalDate orderDate;
    Boolean statusOrder;
    Float deposit;
    String supplierName;
    Float totalCost;
    Boolean haveInvoice;
}
