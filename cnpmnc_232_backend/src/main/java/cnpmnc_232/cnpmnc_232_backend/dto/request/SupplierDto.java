package cnpmnc_232.cnpmnc_232_backend.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SupplierDto {
    @NotEmpty
    private String Name;
    @NotEmpty
    private String Address;
    @NotEmpty
    private String Phone;
    @NotEmpty
    private String Email;
}
