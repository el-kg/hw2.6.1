package skypro.EmployeeBook.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import skypro.EmployeeBook.Employee;
import skypro.EmployeeBook.exception.EmployeeAlreadyAddedException;
import skypro.EmployeeBook.exception.EmployeeIncorrectNameException;
import skypro.EmployeeBook.exception.EmployeeNotFoundException;
import skypro.EmployeeBook.exception.EmployeeStorageIsFullException;

import java.util.*;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String,Employee> employees;
    private static final int EMPLOYEES_SIZE = 5;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName,int department,double salary) {
        if (employees.size() == EMPLOYEES_SIZE) {
            throw new EmployeeStorageIsFullException();
        }

        String key = generateKey(lastName,firstName);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        StringUtils.capitalize(firstName);
        StringUtils.capitalize(lastName);
        Employee employee = new Employee(firstName, lastName,department,salary);
        employees.put(key,employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        String key = generateKey(lastName,firstName);
        Employee employee = employees.remove(key);
        if (Objects.isNull(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String key = generateKey(lastName,firstName);
        Employee employee = employees.get(key);
        if (Objects.isNull(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }
    @Override
    public void checkName (String firstName, String lastName){
        boolean b = StringUtils.containsAny(firstName,"0123456789~`[]{}!@#$%^&*()<>?/,.");
        boolean a= StringUtils.containsAny(lastName,"0123456789~`[]{}!@#$%^&*()<>?/,.");
        if(b==true||a==true){throw new EmployeeIncorrectNameException();}
    }

    public Collection<Employee> findAll() {
        return employees.values();
    }
    private String generateKey(String firstName, String lastName){
       return lastName +firstName;
    }

}

