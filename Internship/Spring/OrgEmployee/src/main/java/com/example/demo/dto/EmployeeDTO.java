package com.example.demo.dto;

import com.example.demo.common.EmployeeCurrentStatus;
import com.sun.istack.NotNull;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.UUID;
@Validated
public class EmployeeDTO {


    private UUID empId;

    @Size(max = 20,message = "Employee Name should be Less than 20")
    private String empName;

    @Email(message = "Enter Valid Email Address")
    private String emailId;

    private boolean active;

    @Enumerated
    private EmployeeCurrentStatus currentStatus;

    @NotNull
    private UUID orgId;

    public UUID getEmpId() {
        return empId;
    }

    public void setEmpId(UUID empId) {
        this.empId = empId;
    }

    public UUID getOrgId() {
        return orgId;
    }

    public void setOrgId(UUID orgId) {
        this.orgId = orgId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public EmployeeCurrentStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(EmployeeCurrentStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public EmployeeDTO() {
    }

    public EmployeeDTO(UUID empId, String empName, String emailId, boolean active, EmployeeCurrentStatus currentStatus, UUID orgId){
        this.empId = empId;
        this.empName = empName;
        this.emailId = emailId;
        this.active = active;
        this.currentStatus = currentStatus;
        this.orgId = orgId;
    }


    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", active=" + active +
                ", currentStatus=" + currentStatus +
                ", orgId=" + orgId +
                '}';
    }
}
