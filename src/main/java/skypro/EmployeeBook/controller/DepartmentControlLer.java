package skypro.EmployeeBook.controller;

import org.springframework.web.bind.annotation.*;
import skypro.EmployeeBook.Employee;
import skypro.EmployeeBook.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentControlLer {
    private final DepartmentService departmentService;

    public DepartmentControlLer(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("{id}/salary/max")
    public Employee maxSalary(@PathVariable int id) {
        return departmentService.maxSalaryEmployee(id);
    }

    @GetMapping("{id}/salary/min")
    public Employee minSalary(@PathVariable int id) {
        return departmentService.minSalaryEmployee(id);
    }

    @GetMapping("{id}/salary/sum")
    public Double sumSalary(@PathVariable int id) {
        return departmentService.sumSalaryEmployee(id);
    }

    @GetMapping("{id}/employees")
    public Collection<Employee> getEmployeeFromDepartment(@PathVariable int id) {
        return departmentService.getEmployeeFromDepartment(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getAll() {
        return departmentService.getAll();

    }
}
