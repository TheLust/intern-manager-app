package md.ceiti.internmanagerapp.back.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import md.ceiti.internmanagerapp.back.util.ConstraintViolationMessage;
import md.ceiti.internmanagerapp.back.util.Field;

@Getter
@Setter
public class WageDto {

    private Long id;

    private ProjectDto project;

    private JobDto job;

    @NotNull(message = Field.PAYMENT_PER_HOUR + ConstraintViolationMessage.NOT_NULL)
    @Min(value = 0, message = Field.PAYMENT_PER_HOUR + ConstraintViolationMessage.MIN)
    private Double paymentPerHour;
}
