package skypro.EmployeeBook.service;

import org.springframework.stereotype.Service;
import skypro.EmployeeBook.Employee;
import skypro.EmployeeBook.exception.EmployeeAlreadyAddedException;
import skypro.EmployeeBook.exception.EmployeeNotFoundException;
import skypro.EmployeeBook.exception.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private List<Employee> employees;
    private static final int EMPLOYEES_SIZE = 5;

    public EmployeeServiceImpl() {
        this.employees = new ArrayList<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() == EMPLOYEES_SIZE) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.remove(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public List<Employee> findAll() {
        return employees;
    }
}

