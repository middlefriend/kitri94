package src.dbcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBConnect {
  public static Connection getConnection() {
    String user = "vippc";
    String password = "1234";
    // 학원 DB
    String url = "jdbc:oracle:thin:@192.168.0.209:1521:xe";
    // 로컬DB
    // String url = "jdbc:oracle:thin:@localhost:xe";

    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");

      return DriverManager.getConnection(url, user, password);
    } catch (ClassNotFoundException e) {
      System.out.println("ojdbc.jar이 없습니다.(드라이버가 존재하지 않습니다.)");
      e.printStackTrace();
    } catch (SQLException e) {
      System.out.println("url,user,password 및 DB가 켜져있는지 확인하세요.");
      e.printStackTrace();
    }
    return null;
  }

  public static void checkClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
    try {
      if (rs != null && rs.isClosed())
        rs.close();
      if (pstmt != null && pstmt.isClosed())
        pstmt.close();
      if (conn != null && conn.isClosed())
        conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
