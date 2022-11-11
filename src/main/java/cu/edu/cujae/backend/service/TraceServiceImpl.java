package cu.edu.cujae.backend.service;

import java.io.IOException;
import java.security.Timestamp;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
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

import cu.edu.cujae.backend.core.dto.TraceDto;
import cu.edu.cujae.backend.core.service.TraceService;

@Service
public class TraceServiceImpl implements TraceService {

   @Autowired
   private JdbcTemplate jdbcTemplate;

   public TraceDto createNewDto(ResultSet resultSet) throws SQLException {
      int id_trace = resultSet.getInt(1); // parametro 1
      int user = resultSet.getInt(2); // parametro 2
      String operation = resultSet.getString(3); // parametro 4
      Date date = resultSet.getDate(4); // parametro 3
      int ip = resultSet.getInt(4); // parametro 3

      return new TraceDto(id_trace, user, operation, date, ip);
   }

   @Override
   public List<TraceDto> listTraces() throws SQLException { // Aparentemente esta funcion ya esta
      List<TraceDto> list = new ArrayList<>();

      String function = "{?= call SELECT * FROM trace}";

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
         TraceDto dto = createNewDto(resultSet);
         list.add(dto);
      }

      return list;
   }

   @Override
   public TraceDto getTraceById(int cdrId) throws SQLException {
      TraceDto cdr = null;

      PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM trace where id_trace = ?");

      pstmt.setInt(1, cdrId);

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
         cdr = createNewDto(resultSet);
      }

      return cdr;
   }

   @Override
   public void createTrace(TraceDto cdr) throws SQLException { // Originalmente aqui no se creaba el ID
      //   String function = "{call create_cdr(?,?,?,?)}";

      //   CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
      //   statement.setInt(1, cdr.getCodCDR());
      //   statement.setString(2, cdr.getNamCDR());
      //   statement.setInt(3, cdr.getId_college());
      //   statement.setInt(4, cdr.getId_presidentCDR());
      //   statement.execute();
   }

   @Override
   public void updateTrace(TraceDto cdr) throws SQLException { // Originalmente este metodo actualizaba ademas de los valores del Dto su ID tambien
      // String function = "{call update_cdr(?,?,?,?)}";

      // CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
      // statement.setString(1, cdr.getNamCDR());
      // statement.setInt(2, cdr.getId_presidentCDR());
      // statement.setInt(3, cdr.getId_college());
      // statement.setInt(4, cdr.getCodCDR());
      // statement.execute();
   }

   @Override
   public void deleteTrace(int cdrId) throws SQLException {
      // String function = "{call delete_cdr(?)}";

      // CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
      // //statement.setInt(1, nominatedId); <------ Linea original
      // statement.setInt(1, cdrId);
      // statement.execute();
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