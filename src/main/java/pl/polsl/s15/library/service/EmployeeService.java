package pl.polsl.s15.library.service;

import org.springframework.stereotype.Service;
import pl.polsl.s15.library.dtos.users.EmployeeDTO;
import pl.polsl.s15.library.repository.EmployeeRepository;
import pl.polsl.s15.library.repository.UserRepository;

@Service
public class EmployeeService extends UserService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(UserRepository userRepository,
                           RoleService roleService,
                           EmployeeRepository employeeRepository) {
        super(userRepository, roleService);
        this.employeeRepository = employeeRepository;
    }

    public void createEmployee(EmployeeDTO employeeDTO) {
        validateIfUserExistsByCredentials(employeeDTO);

    }
}
