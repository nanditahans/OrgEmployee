package com.example.demo.controller;

import com.example.demo.common.EmployeeCurrentStatus;
import com.example.demo.dao.EmployeeRepository;
import com.example.demo.dao.OrgRepository;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.OrgDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.entity.Org;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.OrgService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class ControllerTest {

    @Mock
    private EmployeeService empService;
    @Mock
    private OrgService orgService;

    private Controller controller;

    @BeforeEach
    void setUp() {
        initMocks(this);
        controller = new Controller(empService, orgService);
    }

    @Test
    void testSaveOrg() {
        final OrgDTO orgDTO = new OrgDTO();
        orgDTO.setOrgName("CVT");
        orgDTO.setOrgId(UUID.fromString("67167f75-03e9-4e9a-a8c3-089995601ff0"));
        when(orgService.saveOrg(any(OrgDTO.class))).thenReturn(new Org());
        final ResponseEntity<ResponseDTO> saveOrg = controller.saveOrg(orgDTO);
    }

    @Test
    void testGetAllOrg() {
        when(orgService.getAllOrg()).thenReturn(Arrays.asList(new Org()));
        final ResponseEntity<ResponseDTO> getAllOrg= controller.getAllOrg();
    }

    @Test
    void testGetOrgById() throws Exception {
        when(orgService.getOrgById(UUID.fromString("b3554581-8476-465d-9534-3af9b2508c7c"))).thenReturn(new Org());
        final ResponseEntity<ResponseDTO> getOrgById = controller.getOrgById(UUID.fromString("5e592fcc-528c-41a0-9d71-b59c17134391"));
    }

    @Test
    void testGetOrgById_OrgServiceThrowsResourceNotFoundException() throws Exception {
        when(orgService.getOrgById(UUID.fromString("5e592fcc-528c-41a0-9d71-b59c17134391"))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> {
            controller.getOrgById(UUID.fromString("5e592fcc-528c-41a0-9d71-b59c17134391"));
        });
    }

    @Test
    void testUpdateOrg() throws Exception {
        final OrgDTO orgDTO = new OrgDTO();
        orgDTO.setOrgName("orgName");
        orgDTO.setOrgId(UUID.fromString("67167f75-03e9-4e9a-a8c3-089995601ff0"));
        when(orgService.updateOrg(eq(UUID.fromString("c553350e-349e-4050-be33-beb7e730bb01")), any(OrgDTO.class))).thenReturn(new Org());
        final ResponseEntity<ResponseDTO> result = controller.updateOrg(UUID.fromString("6ac06c3b-6343-438a-ab0e-669df49c7adc"), orgDTO);
    }

    @Test
    void testUpdateOrg_OrgServiceThrowsResourceNotFoundException() throws Exception {
        // Setup
        final OrgDTO orgDTO = new OrgDTO();
        orgDTO.setOrgName("orgName");
        orgDTO.setOrgId(UUID.fromString("67167f75-03e9-4e9a-a8c3-089995601ff0"));
        when(orgService.updateOrg(eq(UUID.fromString("6ac06c3b-6343-438a-ab0e-669df49c7adc")), any(OrgDTO.class))).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class, () -> {
            controller.updateOrg(UUID.fromString("6ac06c3b-6343-438a-ab0e-669df49c7adc"), orgDTO);
        });
    }

    @Test
    void testDeleteOrg() throws Exception {
        final ResponseEntity<ResponseDTO> result = controller.deleteOrg(UUID.fromString("789de660-8b34-4a2a-a999-8f713539517c"));
        verify(orgService).deleteOrg(UUID.fromString("789de660-8b34-4a2a-a999-8f713539517c"));
    }

    @Test
    void testDeleteOrg_OrgServiceThrowsResourceNotFoundException() throws Exception {
        doThrow(ResourceNotFoundException.class).when(orgService).deleteOrg(UUID.fromString("03798a88-e1df-431d-97d7-fe7cc98516ea"));
        assertThrows(ResourceNotFoundException.class, () -> {
            controller.deleteOrg(UUID.fromString("03798a88-e1df-431d-97d7-fe7cc98516ea"));
        });
    }

    @Test
    void testGetAllEmployee() {

        final List<EmployeeDTO> employeeDTOS = Arrays.asList(new EmployeeDTO(UUID.fromString("104f4c56-39f7-4cd2-8872-0d9e57d446fb"), "empName", "emailId", false, EmployeeCurrentStatus.PERMANENT, UUID.fromString("520f4fc6-52fd-4ba7-a752-f2b2ba35e087")));
        when(empService.getAllEmployee()).thenReturn(employeeDTOS);
        final ResponseEntity<ResponseDTO> result = controller.getAllEmployee();
    }

    @Test
    void testSaveEmployee() {
        final EmployeeDTO createEmployeeDTO = new EmployeeDTO(UUID.fromString("a3f1fe18-e8e2-4c2a-8dad-e1241735ca47"), "Nandita", "n.h@gmail.com", false, EmployeeCurrentStatus.PERMANENT, UUID.fromString("88e84b67-ce85-40c3-97e1-e12871386bfd"));
        final EmployeeDTO employeeDTO = new EmployeeDTO(UUID.fromString("514f3aae-2c5c-4fb1-b187-d20d64bbc146"), "Nandita", "n.h@gmail.com", false, EmployeeCurrentStatus.PERMANENT, UUID.fromString("a0e89234-c7c0-456d-a38b-6fbdfb6cc5dd"));
        when(empService.saveEmployee(any(EmployeeDTO.class))).thenReturn(employeeDTO);
        final ResponseEntity<ResponseDTO> result = controller.saveEmployee(createEmployeeDTO);
    }

    @Test
    void testGetEmpById() throws Exception {
        final EmployeeDTO employeeDTO = new EmployeeDTO(UUID.fromString("0833857e-c4e1-40fe-b39b-4fce643c624b"), "Nandita", "n.h@gmail.com", false, EmployeeCurrentStatus.PERMANENT, UUID.fromString("52d9f547-ff75-4125-9226-8645baf25a75"));
        when(empService.getEmployeeById(UUID.fromString("39b5e9c9-d9b3-4ec9-80e5-33303850a07c"))).thenReturn(employeeDTO);
        final ResponseEntity<ResponseDTO> result = controller.getEmpById(UUID.fromString("28918ee5-bf72-48b9-a9c3-990033c7aa42"));
    }

    @Test
    void testGetEmpById_EmployeeServiceThrowsResourceNotFoundException() throws Exception {
        when(empService.getEmployeeById(UUID.fromString("514f3aae-2c5c-4fb1-b187-d20d64bbc146"))).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class, () -> {
            controller.getEmpById(UUID.fromString("514f3aae-2c5c-4fb1-b187-d20d64bbc146"));
        });
    }

    @Test
    void testUpdateEmployee() throws Exception {
        final EmployeeDTO employeeDTO = new EmployeeDTO(UUID.fromString("885526a4-12c9-433f-941b-cd84f53be312"), "Nandita", "n.h@gmail.com", false, EmployeeCurrentStatus.PERMANENT, UUID.fromString("efed709a-3dd5-4345-85d2-63260a8c5ecb"));
        final EmployeeDTO employeeDTO1 = new EmployeeDTO(UUID.fromString("514f3aae-2c5c-4fb1-b187-d20d64bbc146"), "Nandita", "n.h@gmail.com", false, EmployeeCurrentStatus.PERMANENT, UUID.fromString("94602db0-4099-4231-9569-169b58e6c554"));
        when(empService.updateEmployee(eq(UUID.fromString("514f3aae-2c5c-4fb1-b187-d20d64bbc146")), any(EmployeeDTO.class))).thenReturn(employeeDTO1);
        final ResponseEntity<ResponseDTO> result = controller.updateEmployee(UUID.fromString("514f3aae-2c5c-4fb1-b187-d20d64bbc146"), employeeDTO);
    }

    @Test
    void testUpdateEmployee_EmployeeServiceThrowsResourceNotFoundException() throws Exception {
        final EmployeeDTO employeeDTO = new EmployeeDTO(UUID.fromString("885526a4-12c9-433f-941b-cd84f53be312"), "Nandita", "n.h@gmail.com", false, EmployeeCurrentStatus.PERMANENT, UUID.fromString("efed709a-3dd5-4345-85d2-63260a8c5ecb"));
        when(empService.updateEmployee(eq(UUID.fromString("465fbc0f-e655-4860-a75d-6293cde6920e")), any(EmployeeDTO.class))).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class, () -> {
            controller.updateEmployee(UUID.fromString("465fbc0f-e655-4860-a75d-6293cde6920e"), employeeDTO);
        });
    }

    @Test
    void testPartialUpdateEmployee() throws Exception {
        final EmployeeDTO employeeDTO = new EmployeeDTO(UUID.fromString("45a2e8fe-e0ed-40c9-b2cc-28804af59518"), "Nandita", "n.h@gmail.com", false, EmployeeCurrentStatus.PERMANENT, UUID.fromString("131bd2ea-6f41-4bc9-9086-e7712a407763"));
        final EmployeeDTO employeeDTO1 = new EmployeeDTO(UUID.fromString("53419c02-e398-447b-bed0-f9eb62b29433"), "empName", "emailId", false, EmployeeCurrentStatus.PERMANENT, UUID.fromString("1bf2a6e5-e2be-485d-b8de-52f5bc65969d"));
        when(empService.partialUpdateEmployee(eq(UUID.fromString("9d45c451-8981-4c02-bc93-8021a45c84fa")), any(EmployeeDTO.class))).thenReturn(employeeDTO1);
        final ResponseEntity<ResponseDTO> result = controller.partialUpdateEmployee(UUID.fromString("10554201-4f5c-4de3-bf1d-d0856dfbd233"), employeeDTO);

    }

    @Test
    void testPartialUpdateEmployee_EmployeeServiceThrowsResourceNotFoundException() throws Exception {
        final EmployeeDTO employeeDTO = new EmployeeDTO(UUID.fromString("45a2e8fe-e0ed-40c9-b2cc-28804af59518") ,"Nandita", "n.h@gmail.com", false, EmployeeCurrentStatus.PERMANENT, UUID.fromString("131bd2ea-6f41-4bc9-9086-e7712a407763"));
        when(empService.partialUpdateEmployee(eq(UUID.fromString("9d45c451-8981-4c02-bc93-8021a45c84fa")), any(EmployeeDTO.class))).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class, () -> {
            controller.partialUpdateEmployee(UUID.fromString("9d45c451-8981-4c02-bc93-8021a45c84fa"), employeeDTO);
        });
    }

    @Test
    void testDeleteEmployee() throws Exception {

        final ResponseEntity<ResponseDTO> result = controller.deleteEmployee(UUID.fromString("465fbc0f-e655-4860-a75d-6293cde6920e"));
        verify(empService).deleteEmployee(UUID.fromString("465fbc0f-e655-4860-a75d-6293cde6920e"));
    }

    @Test
    void testDeleteEmployee_EmployeeServiceThrowsResourceNotFoundException() throws Exception {
        doThrow(ResourceNotFoundException.class).when(empService).deleteEmployee(UUID.fromString("465fbc0f-e655-4860-a75d-6293cde6920e"));
        assertThrows(ResourceNotFoundException.class, () -> {
            controller.deleteEmployee(UUID.fromString("465fbc0f-e655-4860-a75d-6293cde6920e"));
        });
    }
}
