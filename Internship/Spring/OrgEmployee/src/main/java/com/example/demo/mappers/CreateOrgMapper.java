package com.example.demo.mappers;

import com.example.demo.dto.OrgDTO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Org;
import org.springframework.stereotype.Component;

@Component
public class CreateOrgMapper {
    public Org OrgDtoToOrg(OrgDTO orgDTO){
        Org org=new Org();
        org.setOrgName(orgDTO.getOrgName());
        return org;
    }
    /**
     * EmployeeCreateResponseDTO
     * service -> EmployeeCreateResponseDTO -> controller
     */
}
