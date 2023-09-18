package nl.jeroen.udemy.springboottesting.service;

import nl.jeroen.udemy.springboottesting.exception.EmployeeAlreadyExistsException;
import nl.jeroen.udemy.springboottesting.model.Employee;
import nl.jeroen.udemy.springboottesting.repository.EmployeeRepository;
import nl.jeroen.udemy.springboottesting.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void givenEmployee_whenSaveEmployee_thenReturnEmployee() {
        Employee employee = maakEmployee();
        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.empty());
        given(employeeRepository.save(employee))
                .willReturn(employee);

        Employee savedEmployee = employeeService.saveEmployee(employee);

        assertThat(savedEmployee).isNotNull();
    }

    @Test
    public void givenExistingEmail_whenSaveEmployee_thenThrowsException() {
        Employee employee = maakEmployee();
        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.of(employee));

        assertThatExceptionOfType(EmployeeAlreadyExistsException.class).isThrownBy(
                () -> employeeService.saveEmployee(employee));

        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList() {
        var employee = maakEmployee();
        var employee2 = maakEmployee2();
        given(employeeRepository.findAll()).willReturn(List.of(employee, employee2));

        var employees = employeeService.getAllEmployees();

        assertThat(employees).isNotNull();
        assertThat(employees).hasSize(2);
        verify(employeeRepository).findAll();
    }

    @Test
    public void givenEmptyEmployeesList_whenGetAllEmployees_thenReturnEmptyEmployeesList() {
        given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        var employees = employeeService.getAllEmployees();

        assertThat(employees).isEmpty();
        verify(employeeRepository).findAll();
    }

    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() {
        given(employeeRepository.findById(1L))
                .willReturn(Optional.of(maakEmployee()));
        var optionalEmployee = employeeService.getEmployeeById(1L);
        assertThat(optionalEmployee).isNotEmpty();
    }

    @Test
    public void givenEmployee_whenUpdateEmployee_thenReturnUpdatedEmployee() {
        var employee = maakEmployee();
        given(employeeRepository.save(employee))
                .willReturn(employee);
        employee.setFirstName("XXX");
        var updatedEmployee = employeeService.updateEmployee(employee);
        assertThat(updatedEmployee.getFirstName()).isEqualTo("XXX");
    }

    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenNothing_BDD_style() {
        long employeeId = 1L;
        willDoNothing().given(employeeRepository).deleteById(employeeId);
        employeeService.deleteEmployee(employeeId);
        verify(employeeRepository).deleteById(employeeId);
    }

    private Employee maakEmployee() {
        return Employee.builder()
                 .firstName("Jeroen")
                .lastName("Jansen")
                .email("jeroen@gmail.com")
                .build();
    }

    private Employee maakEmployee2() {
        return Employee.builder()
                .firstName("Jan")
                .lastName("Pietersen")
                .email("jp@gmail.com")
                .build();
    }
}
