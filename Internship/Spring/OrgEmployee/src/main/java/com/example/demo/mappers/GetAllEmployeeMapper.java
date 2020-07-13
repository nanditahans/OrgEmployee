package com.example.demo.mappers;

import com.example.demo.common.EmployeeCurrentStatus;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class GetAllEmployeeMapper {

    public EmployeeDTO getAllEmployees(Employee employee) {
        EmployeeDTO employeeDTO=new EmployeeDTO();
        EmployeeCurrentStatus employeeCurrentStatus = null;
        employeeDTO.setEmpId(employee.getEmpId());
        employeeDTO.setEmpName(employee.getEmpName());
        employeeDTO.setEmailId(employee.getEmailId());
        employeeDTO.setCurrentStatus(employeeCurrentStatus.valueOf(employee.getCurrentStatus()));
        employeeDTO.setActive(employee.isActive());
        employeeDTO.setOrgId(employee.getOrg().getOrgId());
        return employeeDTO;


    }

}