package com.contoso.payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void testEmployeeConstructorAndGetters() {
        Manager manager = new Manager("John", "Doe", "Engineering");
        Employee employee = new Employee("Jane", "Smith", "Software Engineer", manager);

        assertEquals("Jane", employee.getFirstName());
        assertEquals("Smith", employee.getLastName());
        assertEquals("Software Engineer", employee.getDescription());
        assertEquals(manager, employee.getManager());
    }

    @Test
    void testSetters() {
        Manager manager = new Manager("John", "Doe", "Engineering");
        Employee employee = new Employee("Jane", "Smith", "Software Engineer", manager);

        employee.setFirstName("Alice");
        employee.setLastName("Johnson");
        employee.setDescription("Senior Engineer");

        assertEquals("Alice", employee.getFirstName());
        assertEquals("Johnson", employee.getLastName());
        assertEquals("Senior Engineer", employee.getDescription());
    }

    @Test
    void testEqualsAndHashCode() {
        Manager manager = new Manager("John", "Doe", "Engineering");
        Employee employee1 = new Employee("Jane", "Smith", "Software Engineer", manager);
        Employee employee2 = new Employee("Jane", "Smith", "Software Engineer", manager);

        employee1.setId(1L);
        employee2.setId(1L);

        assertEquals(employee1, employee2);
        assertEquals(employee1.hashCode(), employee2.hashCode());
    }

    @Test
    void testToString() {
        Manager manager = new Manager("John", "Doe", "Engineering");
        Employee employee = new Employee("Jane", "Smith", "Software Engineer", manager);

        String expected = "Employee{id=null, firstName='Jane', lastName='Smith', description='Software Engineer', version=null, manager=" + manager + "}";
        assertEquals(expected, employee.toString());
    }
}
