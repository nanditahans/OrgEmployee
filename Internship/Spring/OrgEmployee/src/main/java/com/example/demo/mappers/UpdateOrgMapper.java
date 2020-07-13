package com.example.demo.mappers;

import com.example.demo.dto.OrgDTO;
import com.example.demo.entity.Org;
import org.springframework.stereotype.Component;

@Component
public class UpdateOrgMapper {

    public Org UpdateOrg(Org org, OrgDTO orgDto){
        org.setOrgName(orgDto.getOrgName());
        return org;
    }
}
