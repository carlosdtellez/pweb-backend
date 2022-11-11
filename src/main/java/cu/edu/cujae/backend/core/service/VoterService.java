package cu.edu.cujae.backend.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.VoterDto;

public interface VoterService {
	List<VoterDto> listVoters() throws SQLException;
	VoterDto getVoterById(int voterId) throws SQLException; // originalmente el parametro era String
	void createVoter(VoterDto voter) throws SQLException;
//	void updateVoter(VoterDto voter) throws SQLException;
	void deleteVoter(int id) throws SQLException; // originalmente el parametro era String
}