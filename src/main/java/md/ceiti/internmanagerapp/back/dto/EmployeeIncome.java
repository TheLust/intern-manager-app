package md.ceiti.internmanagerapp.back.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeIncome {
    private ProjectDto project;
    private Double workedHours;
    private Double paymentPerHour;
    private Double income;
}
