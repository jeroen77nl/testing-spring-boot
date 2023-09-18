package nl.jeroen.udemy.springboottesting.service.impl;

import lombok.RequiredArgsConstructor;
import nl.jeroen.udemy.springboottesting.exception.EmployeeAlreadyExistsException;
import nl.jeroen.udemy.springboottesting.model.Employee;
import nl.jeroen.udemy.springboottesting.repository.EmployeeRepository;
import nl.jeroen.udemy.springboottesting.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        String email = employee.getEmail();
        Optional<Employee> savedEmployee = employeeRepository.findByEmail(email);
        if (savedEmployee.isPresent()) {
            throw new EmployeeAlreadyExistsException("Employee already exists with given email:" + email);
        }
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }
}
