package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;

public interface EmployeeService {
	public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
	public List<EmployeeDTO> getAllEmployee();
	public EmployeeDTO updateEmployee(UUID empId, EmployeeDTO employeeDTO) throws ResourceNotFoundException;
	public EmployeeDTO partialUpdateEmployee(UUID empId,EmployeeDTO employeeDTO) throws ResourceNotFoundException;
	public EmployeeDTO getEmployeeById(UUID empId) throws ResourceNotFoundException;
	public void  deleteEmployee(UUID empId) throws ResourceNotFoundException;
}
