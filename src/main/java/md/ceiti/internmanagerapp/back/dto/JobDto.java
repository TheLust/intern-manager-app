package md.ceiti.internmanagerapp.back.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import md.ceiti.internmanagerapp.back.enums.Stage;
import md.ceiti.internmanagerapp.back.util.ConstraintViolationMessage;
import md.ceiti.internmanagerapp.back.util.Field;

@Getter
@Setter
public class JobDto {

    private Long id;

    @NotBlank(message = Field.NAME + ConstraintViolationMessage.NOT_BLANK)
    private String name;

    @NotNull(message = Field.STAGE + ConstraintViolationMessage.NOT_NULL)
    private Stage stage;

    @NotNull(message = Field.BASE_SALARY + ConstraintViolationMessage.NOT_NULL)
    @Min(value = 0, message = Field.BASE_SALARY + ConstraintViolationMessage.MIN)
    private Double baseSalary;

    public String getDisplayName() {
        return name + " | " + stage.getDisplayName();
    }
}
