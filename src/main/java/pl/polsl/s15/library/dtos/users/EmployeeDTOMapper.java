package pl.polsl.s15.library.dtos.users;

import pl.polsl.s15.library.domain.user.Employee;
import pl.polsl.s15.library.domain.user.User;

public class EmployeeDTOMapper extends UsersDTOMapper {

    public static Employee employeeToEntity(EmployeeDTO employeeDTO) {
        User user = userToEntity(employeeDTO);
        return new Employee(user);
    }
}
