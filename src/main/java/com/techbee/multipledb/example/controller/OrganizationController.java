package com.techbee.multipledb.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techbee.multipledb.example.contractor.Contractor;
import com.techbee.multipledb.example.employee.Employee;
import com.techbee.multipledb.example.service.OrganizationService;

@RestController
@RequestMapping("/org")
public class OrganizationController {

	@Autowired
	OrganizationService organizationService;
	
	@GetMapping("/getEmp")
	public List<Employee> getAllEmployee() {
		return organizationService.getAllEmployee();
	}
	
	@GetMapping("/getCont")
	public List<Contractor> getAllContractor() {
		return organizationService.getAllContractor();
	}
	
	@GetMapping("/getEmp/{empId}")
	public Employee getEmployee(@PathVariable(name = "empId") final Long employeeId) {
		return organizationService.getEmployee(employeeId);
	}
	
	@GetMapping("/getCont/{conId}")
	public Contractor getContractor(@PathVariable(name = "conId") final Long contractorId) {
		return organizationService.getContractor(contractorId);
	}

	@PostMapping(value = "/addEmp", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String saveEmployee(@RequestBody final Employee employee) {
		organizationService.saveEmployee(employee);
		return "New Employee saved successfully";
	}

	@PostMapping("/addCont")
	public String saveContractor(@RequestBody final Contractor contractor) {
		organizationService.saveContractor(contractor);
		return "New Contractor saved successfully";
	}
}
