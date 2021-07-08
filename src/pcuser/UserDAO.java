package pcuser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dbcon.DBConnect;

public class UserDAO {

  // 전체 select
  public ArrayList<UserVO> getAllUser() {
    String sql = "SELECT * FROM PCUSER";

    ArrayList<UserVO> blist = new ArrayList<UserVO>();
    blist = excuteSelect(sql);

    return blist;
  }

  public ArrayList<UserVO> checkID(String id) {
    String sql = "SELECT * FROM PCUSER WHERE USERID='" + id + "'";

    ArrayList<UserVO> blist = new ArrayList<UserVO>();
    blist = excuteSelect(sql);

    return blist;
  }

  public ArrayList<UserVO> getAuth(String id, String pwd) {
    String sql = "SELECT * FROM PCUSER WHERE USERID='" + id + "' AND PWD ='" + pwd + "'";

    ArrayList<UserVO> blist = new ArrayList<UserVO>();
    blist = excuteSelect(sql);

    return blist;
  }


  private ArrayList<UserVO> excuteSelect(String sql) {
    // DB connection 연결
    Connection conn = DBConnect.getConnection();
    // preparedstatement 객체 생성
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    // return 객체
    ArrayList<UserVO> blist = new ArrayList<UserVO>();
    try {
      pstmt = conn.prepareStatement(sql);
      // Resultset 결과값 담기
      rs = pstmt.executeQuery();
      // List에 결과값 담기

      UserVO uvo = null;
      while (rs.next()) {
        uvo = new UserVO();
        uvo.setUserID(rs.getString("USERID"));
        uvo.setName(rs.getString("NAME"));
        uvo.setPwd(rs.getString("PWD"));
        uvo.setRemain(rs.getInt("REMAIN"));
        blist.add(uvo);
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      DBConnect.checkClose(rs, pstmt, conn);
    }
    return blist;
  }
  //// select 끝


  // insert
  public int insertUser(String id, String pwd, String name) {
    String sql =
        "INSERT INTO PCUSER (USERID,NAME,PWD) VALUES ('" + id + "','" + pwd + "','" + name + "')";
    int result = 0;
    result = excuteInsert(sql);

    return result;
  }

  public int excuteInsert(String sql) {
    // DB connection 연결
    Connection conn = DBConnect.getConnection();
    // 실행쿼리
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    // return 값
    int result = 0;
    try {
      // preparedstatement 객체 생성
      pstmt = conn.prepareStatement(sql);
      // Resultset 결과값 담기
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      DBConnect.checkClose(rs, pstmt, conn);
    }

    return result;
  }

  // updated
  public int update(UserVO uvo, int chargeTime) {

    // DB connection 연결
    Connection conn = DBConnect.getConnection();
    // 실행쿼리
    String sql = "UPDATE PCUSER SET REMAIN=? WHERE USERID=? ";

    PreparedStatement pstmt = null;
    int result = 0;
    try {
      // preparedstatement 객체 생성
      pstmt = conn.prepareStatement(sql);
      // ? 인자값 넣어주기
      pstmt.setInt(1, chargeTime);
      pstmt.setString(2, uvo.getUserID());

      // Resultset 결과값 담기
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return result;

  }

  // delete (bookid)
  public int delete(int bookId) {
    Connection conn = DBconnect.getInstance();
    // 실행쿼리
    String sql = "DELETE FROM BOOK WHERE BOOKID=?) ";

    PreparedStatement pstmt = null;
    int result = 0;
    try {
      // preparedstatement 객체 생성
      pstmt = conn.prepareStatement(sql);
      // ? 인자값 넣어주기
      pstmt.setInt(1, bookId);

      // Resultset 결과값 담기
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return result;
  }

}
