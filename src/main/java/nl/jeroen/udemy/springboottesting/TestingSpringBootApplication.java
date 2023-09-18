package nl.jeroen.udemy.springboottesting;

import nl.jeroen.udemy.springboottesting.model.Employee;
import nl.jeroen.udemy.springboottesting.service.EmployeeService;
import nl.jeroen.udemy.springboottesting.service.impl.EmployeeServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TestingSpringBootApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TestingSpringBootApplication.class, args);
//		EmployeeService employeeService = context.getBean(EmployeeServiceImpl.class);
//
//		Employee employee = Employee.builder()
//				.firstName("Jeroen")
//				.lastName("Jongkees")
//				.email("jeroenjongkees@gmail.com")
//				.build();
//
//		employeeService.saveEmployee(employee);
	}
}
