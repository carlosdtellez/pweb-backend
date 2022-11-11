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

import cu.edu.cujae.backend.core.dto.MunicipalityDto;
import cu.edu.cujae.backend.core.service.MunicipalityService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1/municipalities")
public class MunicipalityController {

	@Autowired
    private MunicipalityService municipalityService;

  @GetMapping("")
  public ResponseEntity<List<MunicipalityDto>> getMunicipalitys() throws SQLException {
    List<MunicipalityDto> voterList = municipalityService.listMunicipalitys();
      return ResponseEntity.ok(voterList);
  }

	@GetMapping("/{id}")
    public ResponseEntity<MunicipalityDto> getMunicipalityById(@PathVariable int id) throws SQLException {
		MunicipalityDto voter = municipalityService.getMunicipalityById(id);
        return ResponseEntity.ok(voter);
    }
   
   @PostMapping("")
    public ResponseEntity<String> createMunicipality(@RequestBody MunicipalityDto voter) throws SQLException {
	   municipalityService.createMunicipality(voter);
        return ResponseEntity.ok("Municipality Created");
    }
   
   @PutMapping("")
    public ResponseEntity<String> updateMunicipality(@RequestBody MunicipalityDto voter) throws SQLException {
	   municipalityService.updateMunicipality(voter);
        return ResponseEntity.ok("Municipality Updated");
    }
   
   @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMunicipality(@PathVariable int id) throws SQLException {
	   municipalityService.deleteMunicipality(id);
        return ResponseEntity.ok("Municipality Deleted");
    }
}