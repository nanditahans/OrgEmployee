package com.example.demo.service;

import com.example.demo.dto.OrgDTO;
import com.example.demo.entity.Org;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface OrgService {
	public List<Org> getAllOrg();
	public Org saveOrg( OrgDTO orgDTO);
	public Org getOrgById(UUID orgId) throws ResourceNotFoundException;
	public Org updateOrg(UUID orgId, OrgDTO orgDTO) throws ResourceNotFoundException;
	public void deleteOrg(UUID orgId) throws ResourceNotFoundException;
}
