package com.example.demo.service.impl;

import com.example.demo.dao.OrgRepository;
import com.example.demo.dto.OrgDTO;
import com.example.demo.entity.Org;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mappers.CreateOrgMapper;
import com.example.demo.mappers.UpdateOrgMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class OrgServiceTest {

    @Mock
    private OrgRepository orgRepo;
    @Mock
    private CreateOrgMapper orgMapper;
    @Mock
    private UpdateOrgMapper updateOrgMapper;

    private OrgServiceImpl orgService;

    @BeforeEach
    void Intialize(){
        initMocks(this);
        orgService=new OrgServiceImpl(orgRepo,orgMapper,updateOrgMapper);
    }

    @Test
    void SaveOrgTest(){
        final OrgDTO orgDTO = new OrgDTO();
        orgDTO.setOrgName("CVT");
        orgDTO.setOrgId(UUID.fromString("a0f72f38-69b5-4cd5-88b1-a51095775dc4"));
        when(orgMapper.OrgDtoToOrg(any(OrgDTO.class))).thenReturn(new Org());
        when(orgRepo.save(any(Org.class))).thenReturn(new Org());
        Org org=orgService.saveOrg(orgDTO);
    }

    @Test
    void GetAllOrgTest(){
        when(orgRepo.findAll()).thenReturn(Arrays.asList(new Org()));
        final List<Org> orgs =orgService.getAllOrg();
    }

    @Test
    void GetOrgByIdTest() throws Exception{
        when(orgRepo.findById(UUID.fromString("a0f72f38-69b5-4cd5-88b1-a51095775dc4"))).thenReturn(Optional.of(new Org()));
        final Org orgs=orgService.getOrgById(UUID.fromString("a0f72f38-69b5-4cd5-88b1-a51095775dc4"));
    }

    @Test
    void GetOrgByIdThrowsExceptionTest() throws Exception{
        when(orgRepo.findById(UUID.fromString("fcdeeeae-0ae2-4f6e-8131-a82db736779a"))).thenReturn(Optional.of(new Org()));
        assertThrows(ResourceNotFoundException.class,() -> {
            orgService.getOrgById(UUID.fromString("8e49a322-2085-408d-a90d-2a3619bd902f"));
        });
    }
    @Test
    void UpdateOrgTest() throws Exception{
        final OrgDTO orgDTO = new OrgDTO();
        orgDTO.setOrgName("CVT");
        when(orgRepo.findById(UUID.fromString("a0f72f38-69b5-4cd5-88b1-a51095775dc4"))).thenReturn(Optional.of(new Org()));
        when(updateOrgMapper.UpdateOrg(any(Org.class),any(OrgDTO.class))).thenReturn(new Org());
        when(orgRepo.save(any(Org.class))).thenReturn(new Org());
        Org org=orgService.updateOrg(UUID.fromString("a0f72f38-69b5-4cd5-88b1-a51095775dc4"),orgDTO);
    }

    @Test
    void UpdateOrgTestThrowsException() throws Exception{
        final OrgDTO orgDTO = new OrgDTO();
        orgDTO.setOrgName("CVT");
        when(orgRepo.findById(UUID.fromString("a0f72f38-69b5-4cd5-88b1-a51095775dc4"))).thenReturn(Optional.of(new Org()));
        when(updateOrgMapper.UpdateOrg(any(Org.class),any(OrgDTO.class))).thenReturn(new Org());
        when(orgRepo.save(any(Org.class))).thenReturn(new Org());
        assertThrows(ResourceNotFoundException.class,() -> {
            orgService.updateOrg(UUID.fromString("8e49a322-2085-408d-a90d-2a3619bd902f"),orgDTO);
        });
    }

    @Test
    void DeleteOrgTest(){
        orgService.deleteOrg(UUID.fromString("a0f72f38-69b5-4cd5-88b1-a51095775dc4"));
        verify(orgRepo).deleteById(UUID.fromString("a0f72f38-69b5-4cd5-88b1-a51095775dc4"));
    }


}
