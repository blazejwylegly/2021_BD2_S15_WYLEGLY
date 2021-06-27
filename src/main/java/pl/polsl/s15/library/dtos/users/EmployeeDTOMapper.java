package pl.polsl.s15.library.dtos.users;

import org.springframework.stereotype.Component;
import pl.polsl.s15.library.domain.user.Employee;
import pl.polsl.s15.library.domain.user.User;
import pl.polsl.s15.library.dtos.users.permissions.PermissionsDTOMapper;

@Component
public class EmployeeDTOMapper extends UsersDTOMapper {

    public EmployeeDTOMapper(PermissionsDTOMapper permissionsDTOMapper) {
        super(permissionsDTOMapper);
    }

    public Employee employeeToEntity(EmployeeDTO employeeDTO) {
        User user = userToEntity(employeeDTO);
        return new Employee(user);
    }
}
