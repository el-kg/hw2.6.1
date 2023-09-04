package skypro.EmployeeBook.service;

import skypro.EmployeeBook.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName,int department, double salary);

    Employee deleteEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Collection<Employee> findAll();
    void checkName(String firstName, String lastName);
}

