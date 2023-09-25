package skypro.EmployeeBook.service;

import skypro.EmployeeBook.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee maxSalaryEmployee(int department);
    Employee minSalaryEmployee(int department);

    Double sumSalaryEmployee(int department);

    Collection<Employee> getEmployeeFromDepartment(int department);
    Map<Integer, List<Employee>> getAll();
}
