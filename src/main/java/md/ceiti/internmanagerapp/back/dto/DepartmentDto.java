package md.ceiti.internmanagerapp.back.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import md.ceiti.internmanagerapp.back.util.ConstraintViolationMessage;
import md.ceiti.internmanagerapp.back.util.Field;

@Getter
@Setter
public class DepartmentDto {

    private Long id;

    @NotBlank(message = Field.NAME + ConstraintViolationMessage.NOT_BLANK)
    private String name;
}
