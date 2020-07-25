package com.example.demo.mappers;

import com.example.demo.common.EmployeeCurrentStatus;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.OrgService;
import org.springframework.stereotype.Component;

@Component
public class PartialUpdateMapper {
    private final OrgService orgService;

    public PartialUpdateMapper(OrgService orgService) {
        this.orgService = orgService;
    }

    public Employee UpdateEmployee(Employee employee, EmployeeDTO employeeDto) throws ResourceNotFoundException {

        EmployeeCurrentStatus employeeCurrentStatus = null;
        employeeDto.setEmpId(employee.getEmpId());
        employeeDto.setEmpName(employee.getEmpName());
        if(employeeDto.getEmailId()==null)
            employeeDto.setEmailId(employee.getEmailId());
        else
            employee.setEmailId(employeeDto.getEmailId());
        employeeDto.setActive(employee.isActive());
        if(employeeDto.getCurrentStatus()==null) {
            employeeDto.setCurrentStatus(employeeCurrentStatus.valueOf(employee.getCurrentStatus()));
        }
        else
        {
            employee.setCurrentStatus(employeeDto.getCurrentStatus().toString());

        }
        if(employeeDto.getOrgId()==null)
            employeeDto.setOrgId(employee.getOrg().getOrgId());
        else
            employee.setOrg(orgService.getOrgById(employeeDto.getOrgId()));

        return employee;
    }
}
