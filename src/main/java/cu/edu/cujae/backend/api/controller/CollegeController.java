package cu.edu.cujae.backend.api.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cu.edu.cujae.backend.core.dto.Electoral_CollegeDto;
import cu.edu.cujae.backend.core.service.CollegeService;

@RestController
@RequestMapping("/api/v1/colleges")
public class CollegeController {
	
	@Autowired
	private CollegeService collegeService;
	
	@GetMapping("/")
    public ResponseEntity<List<Electoral_CollegeDto>> getUsers() throws SQLException {
		List<Electoral_CollegeDto> userList = collegeService.listColleges();
        return ResponseEntity.ok(userList);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Electoral_CollegeDto> getUserById(@PathVariable int id) throws SQLException {
		Electoral_CollegeDto user = collegeService.getCollegeById(id);
        return ResponseEntity.ok(user);
    }
	
	@PostMapping("/")
    public ResponseEntity<String> create(@RequestBody Electoral_CollegeDto college) throws SQLException {
		collegeService.createCollege(college);
        return ResponseEntity.ok("College Created");
    }
	
	@PutMapping("/")
    public ResponseEntity<String> update(@RequestBody Electoral_CollegeDto college) throws SQLException {
		//TODO code for update
        return ResponseEntity.ok("College pdated");
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) throws SQLException {
		collegeService.deleteCollege(id);
        return ResponseEntity.ok("College deleted");
    }
}
