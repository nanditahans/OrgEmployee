package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.dto.OrgDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mappers.CreateOrgMapper;
import com.example.demo.mappers.UpdateOrgMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Org;
import com.example.demo.dao.OrgRepository;

import com.example.demo.service.OrgService;
@Service
public class OrgServiceImpl implements OrgService {
	
	private final OrgRepository orgRepo;
	private final CreateOrgMapper orgMapper;
	private final UpdateOrgMapper updateOrgMapper;
	public OrgServiceImpl(OrgRepository repo, CreateOrgMapper orgMapper, UpdateOrgMapper updateOrgMapper) {
		this.orgRepo=repo;
		this.orgMapper = orgMapper;
		this.updateOrgMapper = updateOrgMapper;
	}

	@Override
	public List<Org> getAllOrg() {
		return (List<Org>) orgRepo.findAll();
	}

	@Override
	public Org saveOrg(OrgDTO orgDTO) {
		Org org= orgMapper.OrgDtoToOrg(orgDTO);
		return orgRepo.save(org);
	}

	@Override
	public Org getOrgById(UUID orgId) throws ResourceNotFoundException {
		Optional<Org> orgOptional = orgRepo.findById(orgId);
		if(orgOptional.isPresent()){
			return  orgOptional.get();
		}else{
			throw new ResourceNotFoundException("Organization not found");
		}
	}

	@Override
	public Org updateOrg(UUID orgId, OrgDTO orgDTO) throws ResourceNotFoundException {
		Optional<Org> orgOptional= orgRepo.findById(orgId);
		if(orgOptional.isPresent()){
			Org updatedOrg= updateOrgMapper.UpdateOrg(orgOptional.get(),orgDTO);
			return orgRepo.save(updatedOrg);
		}else{
			throw new ResourceNotFoundException("Organization not found");
		}

	}

	@Override
	public void deleteOrg(UUID orgId){
		orgRepo.deleteById(orgId);
	}
}
