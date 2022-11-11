package cu.edu.cujae.backend.api.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cu.edu.cujae.backend.core.dto.VoterDto;
import cu.edu.cujae.backend.core.service.VoterService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1/voters")
public class VoterController {

	@Autowired
    private VoterService voterService;

  @GetMapping("")
  public ResponseEntity<List<VoterDto>> getVoters() throws SQLException {
    List<VoterDto> voterList = voterService.listVoters();
      return ResponseEntity.ok(voterList);
  }

	@GetMapping("/{id}")
    public ResponseEntity<VoterDto> getVoterById(@PathVariable int id) throws SQLException {
		VoterDto voter = voterService.getVoterById(id);
        return ResponseEntity.ok(voter);
    }
   
   @PostMapping("")
    public ResponseEntity<String> createVoter(@RequestBody VoterDto voter) throws SQLException {
      voterService.createVoter(voter);
        return ResponseEntity.ok("Voter Created");
    }
   
//   @PutMapping("")
//    public ResponseEntity<String> update(@RequestBody VoterDto voter) throws SQLException {
//      voterService.updateVoter(voter);
//        return ResponseEntity.ok("Voter Updated");
//    }
   
   @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVoter(@PathVariable int id) throws SQLException {
      voterService.deleteVoter(id);
        return ResponseEntity.ok("Voter Deleted");
    }
}