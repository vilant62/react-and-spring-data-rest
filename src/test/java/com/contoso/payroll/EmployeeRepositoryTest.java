package com.contoso.payroll;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ManagerRepository managerRepository;

    private Manager manager;

    @BeforeEach
    void setUp() {
        // Create a manager and set it in the security context
        manager = new Manager("John Doe", "password", "ROLE_MANAGER");
        managerRepository.save(manager);

        SecurityContextHolder.getContext().setAuthentication(
            new UsernamePasswordAuthenticationToken(manager.getName(), manager.getPassword())
        );
    }

    @Test
    void testSaveEmployeeWithAuthorizedManager() {
        Employee employee = new Employee("Jane", "Smith", "Developer", manager);
        Employee savedEmployee = employeeRepository.save(employee);

        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getManager()).isEqualTo(manager);
    }

    @Test
    void testDeleteEmployeeByIdWithAuthorizedManager() {
        Employee employee = new Employee("Jane", "Smith", "Developer", manager);
        Employee savedEmployee = employeeRepository.save(employee);

        employeeRepository.deleteById(savedEmployee.getId());

        Optional<Employee> deletedEmployee = employeeRepository.findById(savedEmployee.getId());
        assertThat(deletedEmployee).isEmpty();
    }
}
