# Testing Spring Boot
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
| EmployeeControllerITMysqlLocal     | Integration | @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)  |
|                                    |             | @AutoConfigureMockMvc                                                        |
| EmployeeControllerITMysqlContainer | Integration | @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)  |
|                                    |             | @AutoConfigureMockMvc                                                        |
| EmployeeRepositoryITMysqlContainer | Integration | @DataJpaTest                                                                 |
|                                    |             | @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) |                                                                                          |
| EmployeeRepositoryTests            | Unit        | @DataJpaTest                                                                 |
| EmployeeServiceTests               | Unit        |                                                                              |
| EmployeeServiceTestsMetMockMethode | Unit        |                                                                              |

# Mysql setup
### EmployeeControllerITMysqlLocal
This test requires a locally installed and running Mysql database and also a setup in _application.properties_ 
### EmployeeRepositoryITMysqlContainer
This test needs a running docker engine.
The required image is pulled from docker hub and started by the test class.
This means a local database setup is unnecessary.
This solution uses the _testcontainers_ library (see AbstractContainerBaseTest for more details).

_This project is based on Udemy course _