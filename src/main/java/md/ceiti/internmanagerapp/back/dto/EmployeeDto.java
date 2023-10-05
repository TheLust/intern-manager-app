package md.ceiti.internmanagerapp.back.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import md.ceiti.internmanagerapp.back.enums.Gender;
import md.ceiti.internmanagerapp.back.util.ConstraintViolationMessage;
import md.ceiti.internmanagerapp.back.util.Field;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
public class EmployeeDto {
    private Long id;

    @EqualsAndHashCode.Exclude
    private DepartmentDto department;

    @EqualsAndHashCode.Exclude
    private JobDto job;

    @NotBlank(message = Field.FIRST_NAME + ConstraintViolationMessage.NOT_BLANK)
    private String firstName;

    @NotBlank(message = Field.LAST_NAME + ConstraintViolationMessage.NOT_BLANK)
    private String lastName;

    @NotNull(message = Field.GENDER + ConstraintViolationMessage.NOT_NULL)
    private Gender gender;

    @NotNull(message = Field.DATE_OF_BIRTH + ConstraintViolationMessage.NOT_NULL)
    @Past(message = Field.DATE_OF_BIRTH + ConstraintViolationMessage.PAST)
    private LocalDate dateOfBirth;

    @NotBlank(message = Field.EMAIL + ConstraintViolationMessage.NOT_BLANK)
    @Email(message = ConstraintViolationMessage.EMAIL)
    private String email;

    @NotBlank(message = Field.PHONE_NUMBER + ConstraintViolationMessage.NOT_BLANK)
    private String phoneNumber;

    @NotNull(message = Field.DATE_OF_HIRE + ConstraintViolationMessage.NOT_NULL)
    @PastOrPresent(message = Field.DATE_OF_HIRE + ConstraintViolationMessage.PAST_OR_PRESENT)
    private LocalDate dateOfHire;

    @NotNull(message = Field.BONUS_SALARY + ConstraintViolationMessage.NOT_NULL)
    @Min(value = 0, message = Field.BONUS_SALARY + ConstraintViolationMessage.MIN)
    private Double bonusSalary;

    public String getDisplayName() {
        return firstName + " " + lastName + " | " + department.getName() + " | " + job.getDisplayName();
    }
}
