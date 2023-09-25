package skypro.EmployeeBook.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import skypro.EmployeeBook.Employee;
import skypro.EmployeeBook.exception.EmployeeAlreadyAddedException;
import skypro.EmployeeBook.exception.EmployeeNotFoundException;
import skypro.EmployeeBook.exception.EmployeeStorageIsFullException;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    private EmployeeServiceImpl testingEmployee;

    @BeforeEach
    void beforeEach() {
        testingEmployee = new EmployeeServiceImpl();
    }

    Employee expectedEmployee = new Employee("Ivan", "Ivanov", 3, 45_000);

    @Test
    void addEmployee_shouldAddEmployeeToMapAndReturnEmployee() {
        Employee result = testingEmployee.addEmployee(
                expectedEmployee.getFirstName(), expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(), expectedEmployee.getSalary());
        assertTrue(testingEmployee.findAll().contains(expectedEmployee));
        assertEquals(expectedEmployee, result);

    }

    @Test
    void addEmployee_shouldThrowExceptionWhenMapSizeIsFull() {
        for (int i = 0; i < 5; i++) {
            testingEmployee.addEmployee(
                    expectedEmployee.getFirstName() + i, expectedEmployee.getLastName() + i,
                    expectedEmployee.getDepartment(), expectedEmployee.getSalary());
        }
        assertThrows(EmployeeStorageIsFullException.class,
                () -> testingEmployee.addEmployee(
                        expectedEmployee.getFirstName(), expectedEmployee.getLastName(),
                        expectedEmployee.getDepartment(), expectedEmployee.getSalary()));
    }

    @Test
    void addEmployee_shouldThrowExceptionWhenEmployeeIsAlreadyAdded() {
        testingEmployee.addEmployee(expectedEmployee.getFirstName(), expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(), expectedEmployee.getSalary());
        assertThrows(EmployeeAlreadyAddedException.class,
                () -> testingEmployee.addEmployee(expectedEmployee.getFirstName(), expectedEmployee.getLastName(),
                        expectedEmployee.getDepartment(), expectedEmployee.getSalary()));
    }

    @Test
    void deleteEmployee_shouldReturnEmployeeWhenEmployeeWasDeleted() {
        Employee result = testingEmployee.addEmployee(expectedEmployee.getFirstName(), expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(), expectedEmployee.getSalary());
        assertEquals(testingEmployee.deleteEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName()), result);
        assertFalse(testingEmployee.findAll().contains(expectedEmployee));
    }

    @Test
    void deleteEmployee_shouldThrowExceptionWhenEmployeeNotFounded() {
        assertThrows(EmployeeNotFoundException.class,
                () -> testingEmployee.deleteEmployee(expectedEmployee.getFirstName(), expectedEmployee.getLastName()));

    }

    @Test
    void findEmployee_shouldReturnEmployeeWhenEmployeeInMap() {
        Employee result = testingEmployee.addEmployee(expectedEmployee.getFirstName(), expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(), expectedEmployee.getSalary());
        assertEquals(testingEmployee.findEmployee(expectedEmployee.getFirstName(), expectedEmployee.getLastName())
                , result);
    }

    @Test
    void findEmployee_shouldThrowEmployeeNotFoundExceptionWhenEmployeeAreNotInMap() {
        assertThrows(EmployeeNotFoundException.class,
                () -> testingEmployee.findEmployee(expectedEmployee.getFirstName(), expectedEmployee.getLastName()));
    }

    @Test
    void findAll_shouldReturnEmployeeWhenEmployeeInMap() {
        Employee employee = new Employee("Petr", "Petrov", 2, 47_000);
        testingEmployee.addEmployee(expectedEmployee.getFirstName(), expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(), expectedEmployee.getSalary());
        testingEmployee.addEmployee(employee.getFirstName(),
                employee.getLastName(), employee.getDepartment(), employee.getSalary());
        Collection<Employee> result = testingEmployee.findAll();
        assertTrue(result.containsAll(List.of(expectedEmployee, employee)));
    }

    @Test
    void findAll_shouldReturnEmptyListWhenEmployeesAreNotInMap() {
        Collection<Employee> all = testingEmployee.findAll();
        assertTrue(all.isEmpty());

    }
}