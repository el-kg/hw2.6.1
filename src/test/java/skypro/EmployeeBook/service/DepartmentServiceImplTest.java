package skypro.EmployeeBook.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import skypro.EmployeeBook.Employee;
import skypro.EmployeeBook.exception.EmployeeNotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    private EmployeeServiceImpl employeeService;
    @InjectMocks
    private DepartmentServiceImpl departmentService;
    private List<Employee> employees = List.of(
            new Employee("Ivan", "Ivanov", 3, 45_000),
            new Employee("Petr", "Petrov", 2, 47_000),
            new Employee("Fedor", "Maximov", 3, 48_000));

    @Test
    void maxSalaryEmployee_shouldReturnEmployeeWithMaxSalaryWhenEmployeesInDepartment() {
        when(employeeService.findAll()).thenReturn(employees);
        Employee result = departmentService.maxSalaryEmployee(employees.get(0).getDepartment());
        assertEquals(employees.get(2), result);
    }

    @Test
    void maxSalaryEmployee_shouldTrowExceptionWhenDepartmentIsEmpty() {
        when(employeeService.findAll()).thenReturn(Collections.emptyList());
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.maxSalaryEmployee(1));
    }

    @Test
    void minSalaryEmployee_shouldReturnEmployeeWithMaxSalaryWhenEmployeesInDepartment() {
        when(employeeService.findAll()).thenReturn(employees);
        Employee result = departmentService.minSalaryEmployee(employees.get(2).getDepartment());
        assertEquals(employees.get(0), result);
    }

    @Test
    void minSalaryEmployee_shouldTrowExceptionWhenDepartmentIsEmpty() {
        when(employeeService.findAll()).thenReturn(Collections.emptyList());
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.minSalaryEmployee(1));

    }

    @Test
    void sumSalaryEmployee_shouldReturnNullWhenDepartmentIsEmpty() {
        when(employeeService.findAll()).thenReturn(Collections.emptyList());
        assertEquals(departmentService.sumSalaryEmployee(1),
                null);
    }

    @Test
    void sumSalaryEmployee_shouldReturnSumWhenEmployeesAreInDepartment() {
        when(employeeService.findAll()).thenReturn(employees);
        Double expectedResult = employees.get(0).getSalary() + employees.get(2).getSalary();
        assertEquals(departmentService.sumSalaryEmployee(3), expectedResult);
    }

    @Test
    void getEmployeeFromDepartment_shouldReturnEmployeesCollectionWhenEmployeesAreInDepartment() {
        when(employeeService.findAll()).thenReturn(employees);
        Collection<Employee> result = departmentService.getEmployeeFromDepartment(employees.get(0).getDepartment());
        assertEquals(List.of(employees.get(0), employees.get(2)), result);
    }

    @Test
    void getEmployeeFromDepartment_shouldReturnEmptyListWhenDepartmentIsEmpty() {
        when(employeeService.findAll()).thenReturn(Collections.emptyList());
        Collection<Employee> result = departmentService.getEmployeeFromDepartment(1);
        assertEquals(Collections.emptyList(), result);

    }


    @Test
    void getAll_shouldReturnMapWithEmployeeAreBeenInDepartment() {
        when(employeeService.findAll()).thenReturn(employees);
        Map<Integer, List<Employee>> expectedMap = Map.of(employees.get(1).getDepartment(), List.of(employees.get(1)),
                employees.get(0).getDepartment(), List.of(employees.get(0), employees.get(2)));
        Map<Integer, List<Employee>> result = departmentService.getAll();
        assertEquals(expectedMap, result);
    }

    @Test
    void getAll_shouldReturnEmptyMapWhenAreNotEployees() {
        when(employeeService.findAll()).thenReturn(Collections.emptyList());
        Map<Integer, List<Employee>> expectedMap = Map.of();
        Map<Integer, List<Employee>> result = departmentService.getAll();
        assertEquals(expectedMap, result);

    }

}