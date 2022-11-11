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

import cu.edu.cujae.backend.core.dto.Electoral_CollegeDto;
import cu.edu.cujae.backend.core.service.CollegeService;

@Service
public class CollegeServiceImpl implements CollegeService {

   @Autowired
   private JdbcTemplate jdbcTemplate;

   public Electoral_CollegeDto createNewDto(ResultSet resultSet) throws SQLException {
      int id_college = resultSet.getInt(1); // parametro 1
      String name_college = resultSet.getString(2); // parametro 2
      String address = resultSet.getString(3); // parametro 3
      int district = resultSet.getInt(4); // parametro 4

      return new Electoral_CollegeDto(id_college, name_college, address, district);
   }

   @Override
   public List<Electoral_CollegeDto> listColleges() throws SQLException { // Aparentemente esta funcion ya esta
      List<Electoral_CollegeDto> list = new ArrayList<>();

      String function = "{?= call SELECT * FROM college}";

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
         Electoral_CollegeDto dto = createNewDto(resultSet);
         list.add(dto);
      }

      return list;
   }

   @Override
   public Electoral_CollegeDto getCollegeById(int collegeId) throws SQLException {
      Electoral_CollegeDto college = null;

      PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM college where id_college = ?");

      pstmt.setInt(1, collegeId);

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
         college = createNewDto(resultSet);
      }

      return college;
   }

   @Override
   public void createCollege(Electoral_CollegeDto college) throws SQLException { // Originalmente aqui no se creaba el ID
        String function = "{call create_college(?,?,?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setString(1, college.getNameCollege());
        statement.setString(2, college.getAdress());
        statement.setInt(3, college.getId_district());
        statement.execute();

   }

   @Override
   public void updateCollege(Electoral_CollegeDto cdr) throws SQLException { // Originalmente este metodo actualizaba ademas de los valores del Dto su ID tambien
      String function = "{call update_college(?,?,?,?)}";

      CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
      statement.setInt(1, cdr.getCodCollege());
      statement.setInt(2, cdr.getId_district());
      statement.setString(3, cdr.getNameCollege());
      statement.setString(4, cdr.getAdress());
      statement.execute();

   }

   @Override
   public void deleteCollege(int collegeId) throws SQLException {
      String function = "{call delete_college(?)}";

      CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
      statement.setInt(1, collegeId);
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