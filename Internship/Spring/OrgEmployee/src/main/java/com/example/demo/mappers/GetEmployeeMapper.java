package com.example.demo.mappers;

import com.example.demo.common.EmployeeCurrentStatus;
import com.example.demo.dao.EmployeeRepository;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Org;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.OrgService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class GetEmployeeMapper {

    private final OrgService orgService;
    private final EmployeeRepository employeeRepository;

    public GetEmployeeMapper(OrgService orgService, EmployeeRepository employeeRepository) {
        this.orgService = orgService;
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO GetEmployeeMapper(UUID empId, EmployeeDTO employeeDTO) throws ResourceNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(empId);
        EmployeeCurrentStatus employeeCurrentStatus = null;
        employeeDTO.setEmpId(employee.get().getEmpId());
        employeeDTO.setEmpName(employee.get().getEmpName());
        employeeDTO.setEmailId(employee.get().getEmailId());
        employeeDTO.setCurrentStatus(employeeCurrentStatus.valueOf(employee.get().getCurrentStatus()));
        employeeDTO.setActive(employee.get().isActive());
        employeeDTO.setOrgId(employee.get().getOrg().getOrgId());
        return employeeDTO;
    }


}
