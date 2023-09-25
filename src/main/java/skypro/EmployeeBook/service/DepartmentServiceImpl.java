package skypro.EmployeeBook.service;

import org.springframework.stereotype.Service;
import skypro.EmployeeBook.Employee;
import skypro.EmployeeBook.exception.EmployeeNotFoundException;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee maxSalaryEmployee(int department) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartment()== department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee minSalaryEmployee(int department) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartment()== department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }
    @Override
    public Double sumSalaryEmployee(int department){

        return employeeService.findAll().stream()
                .filter(e -> e.getDepartment()== department)
                .collect(Collectors.summingDouble(Employee::getSalary));


    }

    @Override
    public Collection<Employee> getEmployeeFromDepartment(int department) {
        return employeeService.findAll().stream()
                .filter(e->e.getDepartment()==department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getAll() {
        return employeeService.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
