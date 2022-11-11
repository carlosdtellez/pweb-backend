package cu.edu.cujae.backend.service;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tomcat.websocket.server.UriTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import cu.edu.cujae.backend.core.dto.MunicipalityDto;
import cu.edu.cujae.backend.core.dto.NominatedDto;
import cu.edu.cujae.backend.core.service.MunicipalityService;
import cu.edu.cujae.backend.core.service.NominatedService;

@Service
public class MunicipalityServiceImpl implements MunicipalityService {

   @Autowired
   private JdbcTemplate jdbcTemplate;

   public MunicipalityDto createNewDto(ResultSet resultSet) throws SQLException {
      int codMun = resultSet.getInt(1);
      String namMun = resultSet.getString(2);

      return new MunicipalityDto(codMun, namMun);
   }

   @Override
   public List<MunicipalityDto> listMunicipalitys() throws SQLException { // Aparentemente esta funcion ya esta
      List<MunicipalityDto> list = new ArrayList<>();

      //String function = "{?= call select_all_nominateds()}";
      String function = "{?= call SELECT * FROM municipality}";

      Connection connection = jdbcTemplate.getDataSource().getConnection();
      connection.setAutoCommit(false);
      CallableStatement statement = connection.prepareCall(function);
      statement.registerOutParameter(1, Types.OTHER);
      statement.execute();

      ResultSet resultSet = (ResultSet) statement.getObject(1);

      while (resultSet.next()) {
         // int id_nominated = resultSet.getInt(1); // parametro 6
         // int id_voter = resultSet.getInt(2); // parametro 7
         // String occupation = resultSet.getString(3); // parametro 1
         // String profetion = resultSet.getString(4); // parametro 2
         // String phone = resultSet.getString(5); // parametro 3
         // String int_rev = resultSet.getString(6); // parametro 4
         // String bio_data = resultSet.getString(7); // parametro 5
         // int process_e = resultSet.getInt(8); // parametro 8
         // int cant_vote = resultSet.getInt(9); // parametro 9

         //NominatedDto dto = new NominatedDto(occupation, profetion, phone, int_rev, bio_data, id_nominated, id_voter, process_e, cant_vote);
    	  MunicipalityDto dto = createNewDto(resultSet);
         list.add(dto);
      }

      return list;
   }

   @Override
   public MunicipalityDto getMunicipalityById(int municipalityId) throws SQLException {
	   MunicipalityDto municipality = null;

      PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM municipality where id_municipality = ?");

      pstmt.setInt(1, municipalityId);

      ResultSet resultSet = pstmt.executeQuery();

      while (resultSet.next()) {
         // int id_nominated = resultSet.getInt(1); // parametro 6
         // int id_voter = resultSet.getInt(2); // parametro 7
         // String occupation = resultSet.getString(3); // parametro 1
         // String profetion = resultSet.getString(4); // parametro 2
         // String phone = resultSet.getString(5); // parametro 3
         // String int_rev = resultSet.getString(6); // parametro 4
         // String bio_data = resultSet.getString(7); // parametro 5
         // int process_e = resultSet.getInt(8); // parametro 8
         // int cant_vote = resultSet.getInt(9); // parametro 9

         // nominated = new NominatedDto(occupation, profetion, phone, int_rev, bio_data, id_nominated, id_voter, process_e, cant_vote);
    	  municipality = createNewDto(resultSet);
      }

      return municipality;
   }

   @Override
   public void createMunicipality(MunicipalityDto municipality) throws SQLException { // Originalmente aqui no se creaba el ID
        String function = "{call create_municipality(?,?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(1, municipality.getCodMun());
        statement.setString(2, municipality.getNamMun());
        statement.execute();

   }

   @Override
   public void updateMunicipality(MunicipalityDto municipality) throws SQLException { // Originalmente este metodo actualizaba ademas de los valores del Dto su ID tambien
      String function = "{call update_municipality(?,?)}";

      CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
      statement.setInt(1, municipality.getCodMun());
      statement.setString(2, municipality.getNamMun());
      statement.execute();

   }

   @Override
   public void deleteMunicipality(int municipalityId) throws SQLException {
      String function = "{call delete_municipality(?)}";

      CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
      //statement.setInt(1, nominatedId); <------ Linea original
      statement.setInt(1, municipalityId);
      statement.execute();

   }

   // ---------------------------> ESTE METODO SEGURO ES ALGO ESPECIFICO DE GIANFRANCO <---------------------------
   // @Override
   // public NominatedDto getNominatedByName(String nominatedName) throws SQLException {
   //    NominatedDto nominated = null;

   //    PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
   //              "SELECT * FROM hotel_chain where chain_name = ?");

   //    pstmt.setString(1, nominatedName);

   //    ResultSet resultSet = pstmt.executeQuery();

   //    while (resultSet.next()) {
   //       int nominatedId = resultSet.getInt(2);

   //       nominated = new NominatedDto(nominatedId, nominatedName);
   //    }

   //    return nominated;
   // }
}