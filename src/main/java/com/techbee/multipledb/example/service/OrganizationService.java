package com.techbee.multipledb.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techbee.multipledb.example.contractor.Contractor;
import com.techbee.multipledb.example.contractor.ContractorRepository;
import com.techbee.multipledb.example.employee.Employee;
import com.techbee.multipledb.example.employee.EmployeeRepository;

@Service
public class OrganizationService {

	@Autowired
	ContractorRepository contractorRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	public List<Contractor> getAllContractor() {
		return contractorRepository.findAll();
	}

	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public void saveContractor(Contractor contractor) {
		contractorRepository.save(contractor);
	}

	public Contractor getContractor(Long contractorId) {
		Optional<Contractor> optional = contractorRepository.findById(contractorId);
		return optional.get();
	}

	public Employee getEmployee(Long employeeId) {
		Optional<Employee> optional =  employeeRepository.findById(employeeId);
		return optional.get();
	}
}
