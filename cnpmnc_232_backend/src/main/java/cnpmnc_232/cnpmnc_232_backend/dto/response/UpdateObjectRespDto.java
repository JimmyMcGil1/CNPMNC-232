package cnpmnc_232.cnpmnc_232_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateObjectRespDto {
    String message;
    Integer id;
    String error;
}
