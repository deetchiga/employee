package net.guides.springboot2.springboot2jpacrudexample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

//import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2jpacrudexample.model.Department;
import net.guides.springboot2.springboot2jpacrudexample.repository.DepartmentRepository;

@RestController
@RequestMapping("/api/s1")

public class DepartmentController {
	@Autowired
	private DepartmentRepository departmentRepository;
	;

	@GetMapping("/Department")
	public List<Department> getAllDepartment() {
		return departmentRepository.findAll();
	}

	/*@GetMapping("/Department/{id}/{Deptname}")
	public List<Department> getDepartmentByIdAndDepartmentName(@PathVariable Long id,@PathVariable String Deptname) {

		return departmentRepository.findByIdAndDeptname(id,Deptname);
	}*/
	@GetMapping("/Department_p")
	public ResponseEntity<Department> getDepartmentById(@RequestParam long id)
			throws ResourceNotFoundException {
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("department not found for this id:: " + id));
		return ResponseEntity.ok().body(department);
	}
	@GetMapping("/Depart/{id}/{Deptname}")
	public Department getDepartmentByIdAndDepa(@PathVariable(value = "id") Long  DepartmentId ,@PathVariable(value="Deptname") String Deptname)
			throws ResourceNotFoundException {
		Department department = departmentRepository.findByIdAndDeptname(DepartmentId,Deptname);
		return department;
	}
     @GetMapping("/Department_s")
	 public  Department getDepartmentByIdOrDepartmentName(@RequestBody Department dpet) {
		//Long id =2L;
		//String Deptname="civil";
		 //JSONParser parser = new JSONParser();
		 //String s= dpet;
		 //try {
			 //Object obj = parser.parse(s);
		 Long did= dpet.getId();
		 String name=dpet.getDeptname();

			 Department department = departmentRepository.findByIdOrDeptname(did,name);
			 System.out.println(dpet);
			 return department;
		 //}
	 }
	@PostMapping("/Department")
	public Department createDepartment(@Valid @RequestBody Department department) {
		return departmentRepository.save(department);
	}

	@PutMapping("/Department/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable(value = "id") Long DepartmentId,
			@Valid @RequestBody Department departmentDetails) throws ResourceNotFoundException {
		Department department = departmentRepository.findById(DepartmentId)
				.orElseThrow(() -> new ResourceNotFoundException("department not found for this id :: " + DepartmentId));

		department.setDeptname(departmentDetails.getDeptname());
		final Department updatedDepartment = departmentRepository.save(department);
		return ResponseEntity.ok(updatedDepartment);
	}


	@DeleteMapping("/Department/{id}")
	public Map<String, Boolean> deleteDepartment(@PathVariable(value = "id") Long DepartmentId)
			throws ResourceNotFoundException {
		 Department department= departmentRepository.findById(DepartmentId)
				.orElseThrow(() -> new ResourceNotFoundException("department not found for this id :: " + DepartmentId));

		departmentRepository.delete(department);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}

