package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.OrgDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.OrgService;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.dao.OrgRepository;

import com.example.demo.service.EmployeeService;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlEnumValue;
import java.util.UUID;

@RestController
@Validated
public class Controller {


	private final EmployeeService empService;

	private final OrgService orgService;


	public Controller(EmployeeService empService, OrgService orgService) {
		this.empService = empService;
		this.orgService = orgService;
	}

	@PostMapping(value = "/org")
	public ResponseEntity<ResponseDTO> saveOrg(@RequestBody OrgDTO orgDTO) {
		return ResponseEntity.ok(new ResponseDTO(true,orgService.saveOrg(orgDTO)));
	}
	@GetMapping(value = "/org")
	public ResponseEntity<ResponseDTO> getAllOrg(){
		return ResponseEntity.ok(new ResponseDTO(true,orgService.getAllOrg()));
	}
	@GetMapping("/org/{orgId}")
	public ResponseEntity<ResponseDTO> getOrgById(@PathVariable(value = "orgId") UUID orgId) throws ResourceNotFoundException {
		return ResponseEntity.ok(new ResponseDTO(true,orgService.getOrgById(orgId)));}

	@PutMapping("/org/{orgId}")
	public ResponseEntity<ResponseDTO> updateOrg(@PathVariable(value = "orgId") UUID orgId, @RequestBody OrgDTO orgDTO) throws ResourceNotFoundException {
		return ResponseEntity.ok(new ResponseDTO(true,orgService.updateOrg(orgId,orgDTO)));
	}
	@DeleteMapping("/org/{orgId}")
	public ResponseEntity<ResponseDTO> deleteOrg(@PathVariable(value="orgId") UUID orgId)throws ResourceNotFoundException{
		orgService.deleteOrg(orgId);
		return ResponseEntity.ok(new ResponseDTO(true,"Org deleted Successfully"));
	}
	@GetMapping(value="/emp")
	public ResponseEntity<ResponseDTO> getAllEmployee(){
		return ResponseEntity.ok(new ResponseDTO(true,empService.getAllEmployee()));
	}
	
	@PostMapping(value = "/emp")
	public ResponseEntity<ResponseDTO> saveEmployee( @RequestBody @Valid EmployeeDTO createEmployeeDTO)  {
		return ResponseEntity.ok(new ResponseDTO(true,empService.saveEmployee(createEmployeeDTO)));
	}

	@GetMapping(value="/emp/{empId}")
	public ResponseEntity<ResponseDTO> getEmpById(@PathVariable(value = "empId") UUID empId)
			throws ResourceNotFoundException{
		return ResponseEntity.ok(new ResponseDTO(true,empService.getEmployeeById(empId)));
	}
	@PutMapping("/emp/{empId}")
	public ResponseEntity<ResponseDTO> updateEmployee(@PathVariable(value = "empId") UUID empId,@Valid @RequestBody EmployeeDTO employeeDTO) throws ResourceNotFoundException {
		return ResponseEntity.ok(new ResponseDTO(true,empService.updateEmployee(empId,employeeDTO)));
	}
	@PatchMapping ("/emp/{empId}")
	public ResponseEntity<ResponseDTO> partialUpdateEmployee(@Valid @PathVariable(value = "empId") UUID empId, @Valid @RequestBody EmployeeDTO employeeDTO) throws ResourceNotFoundException {
		return ResponseEntity.ok(new ResponseDTO(true,empService.partialUpdateEmployee(empId,employeeDTO)));
	}
	@DeleteMapping("/emp/{empId}")
	public ResponseEntity<ResponseDTO> deleteEmployee(@PathVariable(value="empId") UUID empId)throws ResourceNotFoundException{
		empService.deleteEmployee(empId);
		return ResponseEntity.ok(new ResponseDTO(true,"Employee deleted Successfully"));
	}


	/*@PutMapping("/org/{id}")
	public ResponseEntity<Org> updateOrg(@PathVariable(value = "id") UUID orgId,
										 @RequestBody Org orgDetails) throws ResourceNotFoundException {
		Org org = orgRepoistory.findById(orgId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + orgId));

		org.setOrgname(orgDetails.getOrgname());

		final Org updatedOrg = orgRepoistory.save(org);
		return ResponseEntity.ok(updatedOrg);
	}*/
	/*@GetMapping("/org")
	public Iterable<Org> GetAllOrg(){
		return orgRepoistory.findAll();
	}


	@GetMapping("/org/{id}")
	public ResponseEntity<Org> getOrgById(@PathVariable(value = "id") UUID orgId)
			throws com.example.demo.exception.ResourceNotFoundException {
		Org org = orgRepoistory.findById(orgId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + orgId));
		return ResponseEntity.ok().body(org);
	}
	@PutMapping("/org/{id}")
	public ResponseEntity<Org> updateOrg(@PathVariable(value = "id") UUID orgId,
			 @RequestBody Org orgDetails) throws ResourceNotFoundException {
		Org org = orgRepoistory.findById(orgId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + orgId));

		org.setOrgname(orgDetails.getOrgname());

		final Org updatedOrg = orgRepoistory.save(org);
		return ResponseEntity.ok(updatedOrg);
	}
	@DeleteMapping("/org/{id}")
	public ResponseEntity<?> deleteOrg(@PathVariable(value="id") UUID orgId)throws ResourceNotFoundException{
		orgRepoistory.findById(orgId).orElseThrow(() -> new ResourceNotFoundException("Org not found for this id :: " + orgId));
		orgRepoistory.deleteById(orgId);
		return ResponseEntity.ok().build ();
	}
*/
	
	

}
