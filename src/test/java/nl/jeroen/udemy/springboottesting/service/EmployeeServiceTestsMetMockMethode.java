package nl.jeroen.udemy.springboottesting.service;

import nl.jeroen.udemy.springboottesting.model.Employee;
import nl.jeroen.udemy.springboottesting.repository.EmployeeRepository;
import nl.jeroen.udemy.springboottesting.service.impl.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.Optional;

public class EmployeeServiceTestsMetMockMethode {
    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    @BeforeEach
    public void setup() {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    public void givenEmployee_whenSaveEmployee_thenReturnEmployee() {
        // given
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Jeroen")
                .lastName("Jansen")
                .email("jeroen@gmail.com")
                .build();
        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.empty());
        BDDMockito.given(employeeRepository.save(employee))
                .willReturn(employee);

        System.out.println(employeeRepository);
        System.out.println(employeeService);

        // when
        Employee savedEmployee = employeeService.saveEmployee(employee);
        System.out.println(savedEmployee);
        // then
        Assertions.assertThat(savedEmployee).isNotNull();
    }

}
