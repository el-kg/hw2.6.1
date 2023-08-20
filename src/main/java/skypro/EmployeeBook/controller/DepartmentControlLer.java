package skypro.EmployeeBook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import skypro.EmployeeBook.Employee;
import skypro.EmployeeBook.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentControlLer {
    private final DepartmentService departmentService;

    public DepartmentControlLer(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("/max-salary")
    public Employee maxSalary(@RequestParam int department){
        return departmentService.maxSalaryEmployee(department);
    }
    @GetMapping("/min-salary")
    public Employee minSalary(@RequestParam int department){
        return departmentService.minSalaryEmployee(department);
    }
    @GetMapping(value = "/all",params = "departmentId")
    public Collection<Employee> getEmployeeFromDepartment(@RequestParam int departmentId){
        return departmentService.getEmployeeFromDepartment(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> getAll(){
        return departmentService.getAll();

    }
}
