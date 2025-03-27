package com.contoso.payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class ManagerTest {

    @Test
    void testManagerConstructorAndGetters() {
        String[] roles = {"ROLE_ADMIN", "ROLE_USER"};
        Manager manager = new Manager("John Doe", "password123", roles);

        assertEquals("John Doe", manager.getName());
        assertNotNull(manager.getPassword());
        assertTrue(manager.getPassword().startsWith("$2a$"));
        assertArrayEquals(roles, manager.getRoles());
    }

    @Test
    void testSetPassword() {
        Manager manager = new Manager("John Doe", "password123", "ROLE_USER");
        String oldPassword = manager.getPassword();

        manager.setPassword("newPassword456");
        String newPassword = manager.getPassword();

        assertNotEquals(oldPassword, newPassword);
        assertTrue(newPassword.startsWith("$2a$"));
    }

    @Test
    void testSetters() {
        Manager manager = new Manager("John Doe", "password123", "ROLE_USER");

        manager.setName("Jane Doe");
        manager.setRoles(new String[]{"ROLE_MANAGER"});

        assertEquals("Jane Doe", manager.getName());
        assertArrayEquals(new String[]{"ROLE_MANAGER"}, manager.getRoles());
    }

    @Test
    void testToString() {
        Manager manager = new Manager("John Doe", "password123", "ROLE_USER");

        String expected = "Manager{id=null, name='John Doe', roles=[ROLE_USER]}";
        assertEquals(expected, manager.toString());
    }
}
