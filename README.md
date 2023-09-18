# Testing Spring Boot REST
A project for testing REST controllers for managing Employees (crud) in combination with a Mysql database.
The app contains the three standard Spring layers:
- controller
- service
- repository
### Other packages:
- model (Employee)
- exception

# Overview of test classes

| Class                              | Level       | Class annotation                                                             |
|------------------------------------|-------------|------------------------------------------------------------------------------|
| EmployeeControllerTests            | Unit        | @WebMvcTest                                                                  |
| EmployeeControllerMysqlLocalIT     | Integration | @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)  |
|                                    |             | @AutoConfigureMockMvc                                                        |
| EmployeeControllerMysqlContainerIT | Integration | @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)  |
|                                    |             | @AutoConfigureMockMvc                                                        |
| EmployeeRepositoryMysqlContainerIT | Integration | @DataJpaTest                                                                 |
|                                    |             | @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) |                                                                                          |
| EmployeeRepositoryTests            | Unit        | @DataJpaTest                                                                 |
| EmployeeServiceTests               | Unit        |                                                                              |
| EmployeeServiceMetMockMethodeTests | Unit        |                                                                              |

# Mysql setup
### EmployeeControllerMysqlLocalIT
This test requires a locally installed and running Mysql database and also a setup in _application.properties_ 
### EmployeeRepositoryMysqlContainerIT
All this test requires is a running docker engine. A local database setup is unnecessary.
The required image is pulled from docker hub and started as a container by the running test class.
This solution uses the _testcontainers_ library (see AbstractContainerBaseTest for more details).

_This project is based on Udemy course https://www.udemy.com/course/testing-spring-boot-application-with-junit-and-mockito_