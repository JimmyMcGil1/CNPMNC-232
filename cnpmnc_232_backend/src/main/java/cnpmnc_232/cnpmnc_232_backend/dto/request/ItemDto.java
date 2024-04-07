package cnpmnc_232.cnpmnc_232_backend.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    @NotEmpty
    private Integer id;
    @NotEmpty
    private String item_name;
    @NotEmpty
    private Integer size;
}
