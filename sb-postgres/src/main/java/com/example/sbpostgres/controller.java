package com.example.sbpostgres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee")
public class controller {

    @Autowired
    repository employeeRepository;

    @GetMapping("/get-all-employees")
    public List<model> getAllEmployee(){
        return employeeRepository.findAll();

    }

    @GetMapping("/get-employee/{id}")
    public model getEmployeebyId(@PathVariable(value = "id") Integer employeeId)

    {
        model employeeEntity = employeeRepository.findById(employeeId).get();

        return employeeEntity;
    }

    @PostMapping("/create-employees")
    public model createEmployee(@RequestBody model employee) {

        return employeeRepository.save(employee);
    }

    @PutMapping("/update-employees/{id}")
    public ResponseEntity<model> updateEmployee(@PathVariable(value = "id") Integer employeeId,
                                                @RequestBody model employeeDetails) {
        model employee = employeeRepository.findById(employeeId).get();

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setName(employeeDetails.getName());
        employee.setLocation(employeeDetails.getLocation());
        final model updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/delete-employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer employeeId)
    {
        model employee = employeeRepository.findById(employeeId).get();

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
