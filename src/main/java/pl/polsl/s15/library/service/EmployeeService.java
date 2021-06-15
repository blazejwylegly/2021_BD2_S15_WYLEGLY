package pl.polsl.s15.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.s15.library.dtos.users.EmployeeDTO;
import pl.polsl.s15.library.repository.EmployeeRepository;
import pl.polsl.s15.library.repository.UserRepository;

@Service
public class EmployeeService extends UserService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(UserRepository userRepository,
                           EmployeeRepository employeeRepository) {
        super(userRepository);
        this.employeeRepository = employeeRepository;
    }

    public void createEmployee(EmployeeDTO employeeDTO) {
        validateIfUserExists(employeeDTO);

    }
}
