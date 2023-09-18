package nl.jeroen.udemy.springboottesting.repository;

import nl.jeroen.udemy.springboottesting.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository sut;

    // save operation
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {
        Employee savedEmployee = bewaarPietSchrijvers();
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @Test
    public void givenSavedEmployees_whenFindAll_thenEmployeesReturnedInList() {
        bewaarPietSchrijvers();
        Employee employee2 = Employee.builder()
                .firstName("Eddy")
                .lastName("Treytel")
                .email("e.treytel@gmail.com")
                .build();
        sut.save(employee2);

        List<Employee> employeeList = sut.findAll();

        assertThat(employeeList).isNotNull();
        assertThat(employeeList).hasSize(2);
    }

    @Test
    public void testGetById() {
        Employee savedEmployee = bewaarPietSchrijvers();
        Employee getEmployee = sut.findById(savedEmployee.getId()).get();
        assertThat(getEmployee).isNotNull();
        assertThat(getEmployee.getLastName()).isEqualTo(savedEmployee.getLastName());
    }

    @Test
    public void testGetByEmail() {
        Employee savedEmployee = bewaarPietSchrijvers();
        Employee getEmployee = sut.findByEmail(savedEmployee.getEmail()).get();
        assertThat(getEmployee).isNotNull();
        assertThat(getEmployee.getEmail()).isEqualTo(savedEmployee.getEmail());
    }

    @Test
    public void testUpdate() {
        Employee savedEmployee = bewaarPietSchrijvers();

        Employee fetchedEmployee = sut.findById(savedEmployee.getId()).get();
        fetchedEmployee.setLastName("Schrijver");
        sut.save(fetchedEmployee);
        assertThat(fetchedEmployee.getLastName()).isEqualTo("Schrijver");
    }

    @Test
    public void testDelete() {
        Employee savedEmployee = bewaarPietSchrijvers();

        sut.delete(savedEmployee);
        Optional<Employee> employeeOptional = sut.findById(savedEmployee.getId());
        assertThat(employeeOptional).isEmpty();
    }

    @Test
    public void givenJPQLwithIndexParams() {
        bewaarPietSchrijvers();
        String firstName = "Piet";
        String lastName = "Schrijvers";
        Employee fetchedEmployee = sut.findByJPQL(firstName, lastName);
        assertThat(fetchedEmployee).isNotNull();
    }

    @Test
    public void givenJPQLwithNamedParams() {
        bewaarPietSchrijvers();
        String firstName = "Piet";
        String lastName = "Schrijvers";
        Employee fetchedEmployee = sut.findByJPQLNamedParams(firstName, lastName);
        assertThat(fetchedEmployee).isNotNull();
    }

    @Test
    public void givenNativeSqlWithIndexParams() {
        bewaarPietSchrijvers();
        String firstName = "Piet";
        String lastName = "Schrijvers";
        Employee fetchedEmployee = sut.findByNativeSQL(firstName, lastName);
        assertThat(fetchedEmployee).isNotNull();
    }

    @Test
    public void givenNativeSqlWithNamedParams() {
        bewaarPietSchrijvers();
        String firstName = "Piet";
        String lastName = "Schrijvers";
        Employee fetchedEmployee = sut.findByNativeSQLNamed(firstName, lastName);
        assertThat(fetchedEmployee).isNotNull();
    }

    private Employee bewaarPietSchrijvers() {
        Employee employee = Employee.builder()
                .firstName("Piet")
                .lastName("Schrijvers")
                .email("p.schrijvers@gmail.com")
                .build();
        return sut.save(employee);
    }
}

