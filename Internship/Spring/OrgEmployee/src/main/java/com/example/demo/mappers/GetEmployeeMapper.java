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

    private final EmployeeRepository employeeRepository;

    public GetEmployeeMapper(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO GetEmployeeMapper(Employee employee, EmployeeDTO employeeDTO){
        employeeDTO.setEmpId(employee.getEmpId());
        employeeDTO.setEmpName(employee.getEmpName());
        employeeDTO.setEmailId(employee.getEmailId());
        employeeDTO.setCurrentStatus(EmployeeCurrentStatus.valueOf(employee.getCurrentStatus()));
        employeeDTO.setActive(employee.isActive());
        employeeDTO.setOrgId(employee.getOrg().getOrgId());
        return employeeDTO;
    }


}
