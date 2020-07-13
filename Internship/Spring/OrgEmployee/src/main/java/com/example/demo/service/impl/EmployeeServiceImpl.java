package com.example.demo.service.impl;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mappers.*;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.dao.EmployeeRepository;

import com.example.demo.service.EmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private final EmployeeRepository empRepo;
	private final OrgServiceImpl orgImpl;
	private final UpdateEmployeeMapper updateEmployeeMapper;
	private final CreateEmployeeMapper employeeMapper;
	private final GetEmployeeMapper getEmployeeMapper;
	private final PartialUpdateMapper partialUpdateMapper;
	private final GetAllEmployeeMapper getAllEmployeeMapper;

	public EmployeeServiceImpl(EmployeeRepository empRepo, OrgServiceImpl orgImpl, UpdateEmployeeMapper updateEmployeeMapper, CreateEmployeeMapper employeeMapper, GetEmployeeMapper getEmployeeMapper, PartialUpdateMapper partialUpdateMapper, GetAllEmployeeMapper getAllEmployeeMapper) {
		this.empRepo=empRepo;
		this.orgImpl = orgImpl;
		this.updateEmployeeMapper = updateEmployeeMapper;
		this.employeeMapper = employeeMapper;
		this.getEmployeeMapper = getEmployeeMapper;
		this.partialUpdateMapper = partialUpdateMapper;
		this.getAllEmployeeMapper = getAllEmployeeMapper;
	}

	/*@Override
	public Employee saveEmployee(EmployeeDTO employeeDTO) {
		Employee employee =employeeMapper.EmployeeDtoToEmployee(employeeDTO);
		return empRepo.save(employee);
	}*/

	public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
		Employee employee = null;
		try {
			employee = employeeMapper.EmployeeDtoToEmployee(employeeDTO);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(employee.getEmpId().toString().getBytes().length);
		System.out.println(employee.getEmpName().getBytes().length);
		empRepo.save(employee);
		return employeeDTO;
	}

	@Override
	public List<EmployeeDTO> getAllEmployee() {

		List<Employee> employeesList = (List<Employee>) empRepo.findAll();
		List<EmployeeDTO> employees=employeesList.stream().map(employee ->getAllEmployeeMapper.getAllEmployees(employee)).collect(Collectors.toList());
		return employees;


	}

	@Override
	public EmployeeDTO updateEmployee(UUID empId, EmployeeDTO employeeDTO) throws ResourceNotFoundException {
		Optional<Employee> employeeOptional=empRepo.findById(empId);
		if(employeeOptional.isPresent()){
			Employee updatedEmployee=updateEmployeeMapper.UpdateEmployee(employeeOptional.get(),employeeDTO);
			empRepo.save(updatedEmployee);
			return employeeDTO;
		}else{
			throw new ResourceNotFoundException("Enter Valid EmployeeId");
		}
	}

	@Override
	public EmployeeDTO partialUpdateEmployee(UUID empId, EmployeeDTO employeeDTO) throws ResourceNotFoundException {
		Optional<Employee> employeeOptional=empRepo.findById(empId);
		if(employeeOptional.isPresent()){
			Employee updatedEmployee=partialUpdateMapper.UpdateEmployee(employeeOptional.get(),employeeDTO);
			empRepo.save(updatedEmployee);
			return employeeDTO;
		}else{
			throw new ResourceNotFoundException("Enter Valid EmployeeId");
		}

	}

	@Override
	public EmployeeDTO getEmployeeById(UUID empId) throws ResourceNotFoundException {
		if(empId!=null){
			EmployeeDTO employeeDTO=new EmployeeDTO();
			getEmployeeMapper.GetEmployeeMapper(empId,employeeDTO);
			return employeeDTO;
		}else{
			throw new ResourceNotFoundException("Enter Valid EmployeeId");
		}

	}

	@Override
	public void deleteEmployee(UUID empId){
		empRepo.deleteById(empId);
	}
	/*@Override
	public List<Employee> findAll() {
		return (List<Employee>) empRepo.findAll();
	}

	@Override
	public Optional<Employee> findById(UUID id) {
		return empRepo.findById(id);*/


		/*@Override
	public EmployeeDetailsDTO saveEmployee(EmployeeDetailsDTO emp) {
		emp.setOrg(orgImpl.findById(emp.getOrg().getOrgId().toString()));
		orgImpl.findById(emp.getOrgId());
		return empRepo.save(emp);
	}*/

	/*@Override
	public Employee saveEmployee(Employee emp) {
		emp.setOrg(orgImpl.findById(emp.getOrg().getOrgId().toString()));
		return empRepo.save(emp);
	}*/



	}
