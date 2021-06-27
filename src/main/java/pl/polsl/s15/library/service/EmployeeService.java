package pl.polsl.s15.library.service;

import org.springframework.stereotype.Service;
import pl.polsl.s15.library.domain.user.Employee;
import pl.polsl.s15.library.dtos.users.EmployeeDTO;
import pl.polsl.s15.library.dtos.users.EmployeeDTOMapper;
import pl.polsl.s15.library.dtos.users.UsersDTOMapper;
import pl.polsl.s15.library.dtos.users.permissions.roles.RoleDTOMapper;
import pl.polsl.s15.library.repository.EmployeeRepository;
import pl.polsl.s15.library.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService extends UserService {

    private EmployeeRepository employeeRepository;
    private EmployeeDTOMapper employeeDTOMapper;

    private static final List<String> DEFAULT_EMPLOYEE_ROLES = Arrays.asList("EMPLOYEE");

    public EmployeeService(UserRepository userRepository,
                           RoleService roleService,
                           RoleDTOMapper roleDTOMapper,
                           EmployeeRepository employeeRepository,
                           EmployeeDTOMapper employeeDTOMapper) {
        super(userRepository, roleService, employeeDTOMapper, roleDTOMapper);
        this.employeeRepository = employeeRepository;
        this.employeeDTOMapper = employeeDTOMapper;
    }

    public void createEmployee(EmployeeDTO employeeDTO) {
        validateIfUserExistsByCredentials(employeeDTO);
        Employee employee = employeeDTOMapper.employeeToEntity(employeeDTO);
        addDefaultRoles(DEFAULT_EMPLOYEE_ROLES, employee);
        employeeRepository.save(employee);
    }
}
