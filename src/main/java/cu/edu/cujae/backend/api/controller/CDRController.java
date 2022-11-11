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

import cu.edu.cujae.backend.core.dto.CDRDto;
import cu.edu.cujae.backend.core.service.CDRService;

@RestController
@RequestMapping("/api/v1/cdrs")
public class CDRController {
	
	@Autowired
	private CDRService cdrService;
	
	@GetMapping("/")
    public ResponseEntity<List<CDRDto>> getUsers() throws SQLException {
		List<CDRDto> userList = cdrService.listCDRs();
        return ResponseEntity.ok(userList);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<CDRDto> getUserById(@PathVariable int id) throws SQLException {
		CDRDto user = cdrService.getCDRById(id);
        return ResponseEntity.ok(user);
    }
	
	@PostMapping("/")
    public ResponseEntity<String> create(@RequestBody CDRDto cdr) throws SQLException {
		cdrService.createCDR(cdr);
        return ResponseEntity.ok("CDR Created");
    }
	
	@PutMapping("/")
    public ResponseEntity<String> update(@RequestBody CDRDto cdr) throws SQLException {
		//TODO code for update
        return ResponseEntity.ok("CDR Updated");
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) throws SQLException {
		cdrService.deleteCDR(id);
        return ResponseEntity.ok("CDR deleted");
    }
}
