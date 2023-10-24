package com.employeeapi.employeeservice.controller;

import com.employeeapi.employeeservice.model.Address;
import com.employeeapi.employeeservice.model.Employee;
import com.employeeapi.employeeservice.model.EmployeeResponse;
import com.employeeapi.employeeservice.repo.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    ModelMapper modelMapper;

    @Value("${address.base.url}")
    private String baseurl;


    @PostMapping("/add/employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        employeeRepo.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }


    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponse>  getEmployeeById(@PathVariable("id") Integer id){
        Optional<Employee> byId = employeeRepo.findById(id);
        Employee employee =byId.get();

        EmployeeResponse employeeResponse= modelMapper.map(employee,EmployeeResponse.class);
        employeeResponse.setAddress(getForObject(id));
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
    }

    private Address getForObject(int id) {
        //resttemplate is going to depricate from spring 6.X , replacement is  web client and feign client
        return restTemplate.getForObject(baseurl+"/employees/"+id, Address.class);
    }
}
