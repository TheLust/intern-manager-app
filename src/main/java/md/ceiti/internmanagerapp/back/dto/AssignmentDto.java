package md.ceiti.internmanagerapp.back.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignmentDto {

    private Long id;

    private ProjectDto project;

    private EmployeeDto employee;

    public String getDisplayName() {
        return project.getName() + " - " + employee.getDisplayName();
    }
}
