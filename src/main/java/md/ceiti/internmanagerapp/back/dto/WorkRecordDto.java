package md.ceiti.internmanagerapp.back.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import md.ceiti.internmanagerapp.back.util.ConstraintViolationMessage;
import md.ceiti.internmanagerapp.back.util.Field;

import java.time.LocalDate;

@Getter
@Setter
public class WorkRecordDto {

    private Long id;


    @NotNull(message = Field.ASSIGNMENT + ConstraintViolationMessage.NOT_NULL)
    private AssignmentDto assignment;

    @NotNull(message = Field.DATE + ConstraintViolationMessage.NOT_NULL)
    @PastOrPresent(message = Field.DATE + ConstraintViolationMessage.PAST_OR_PRESENT)
    private LocalDate date;

    @NotNull(message = Field.WORKED_HOURS + ConstraintViolationMessage.NOT_NULL)
    @Min(value = 0, message = Field.WORKED_HOURS + ConstraintViolationMessage.MIN)
    private Double workedHours;

    private Double amount;
}
