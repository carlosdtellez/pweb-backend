package cu.edu.cujae.backend.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.Electoral_CollegeDto;

public interface CollegeService {
	List<Electoral_CollegeDto> listColleges() throws SQLException;
	Electoral_CollegeDto getCollegeById(int cdrId) throws SQLException; // originalmente el parametro era String
	void createCollege(Electoral_CollegeDto cdr) throws SQLException;
	void updateCollege(Electoral_CollegeDto cdr) throws SQLException;
	void deleteCollege(int id) throws SQLException; // originalmente el parametro era String
}