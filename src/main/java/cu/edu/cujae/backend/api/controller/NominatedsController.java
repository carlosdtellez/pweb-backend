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

import cu.edu.cujae.backend.core.dto.NominatedDto;
import cu.edu.cujae.backend.core.service.NominatedService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1/nominateds")
public class NominatedsController {

	@Autowired
    private NominatedService nominatedService;

  @GetMapping("")
  public ResponseEntity<List<NominatedDto>> getUsers() throws SQLException {
    List<NominatedDto> nominatedList = nominatedService.listNominateds();
      return ResponseEntity.ok(nominatedList);
  }

	@GetMapping("/{id}")
    public ResponseEntity<NominatedDto> getUserById(@PathVariable int id) throws SQLException {
      NominatedDto nominated = nominatedService.getNominatedById(id);
        return ResponseEntity.ok(nominated);
    }
   
   @PostMapping("")
    public ResponseEntity<String> create(@RequestBody NominatedDto nominated) throws SQLException {
      nominatedService.createNominated(nominated);
        return ResponseEntity.ok("Nominated Created");
    }
   
   @PutMapping("")
    public ResponseEntity<String> update(@RequestBody NominatedDto nominated) throws SQLException {
      nominatedService.updateNominated(nominated);
        return ResponseEntity.ok("Nominated Updated");
    }
   
   @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) throws SQLException {
      nominatedService.deleteNominated(id);
        return ResponseEntity.ok("Nominated deleted");
    }
}