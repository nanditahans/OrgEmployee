package com.example.demo.mappers;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.OrgService;
import org.springframework.stereotype.Component;

@Component
public class UpdateEmployeeMapper {

    private final OrgService orgService;

    public UpdateEmployeeMapper(OrgService orgService) {
        this.orgService = orgService;
    }

    public Employee UpdateEmployee(Employee employee, EmployeeDTO employeeDto) throws ResourceNotFoundException {

        employeeDto.setEmpId(employee.getEmpId());
        employee.setEmpName(employeeDto.getEmpName());
        employee.setEmailId(employeeDto.getEmailId());
        employee.setActive(employeeDto.isActive());
        employee.setCurrentStatus(employeeDto.getCurrentStatus().toString());
        employee.setOrg(orgService.getOrgById(employeeDto.getOrgId()));
        return employee;
    }
}
