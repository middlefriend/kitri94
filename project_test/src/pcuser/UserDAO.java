package pcuser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dbcon.DBConnect;


public class UserDAO {

  // select모음

  public int checkID(String id) { // 회원가입 id중복체크 true 1 false 0
    String sql = "SELECT USERID FROM PCUSER WHERE USERID = '" + id + "'";

    return excuteSelectInt(sql);
  }

  public int getAuth(String id, String pwd) { // 로그인시 존재회원 체크
    // String sql = "SELECT userid FROM PCUSER WHERE USERID='" + id + "' AND PWD ='" + pwd + "'";
    String sql = "SELECT USERID FROM PCUSER WHERE USERID = '" + id + "' and PWD = '" + pwd + "'";

    return excuteSelectInt(sql);
  }

  public UserVO getUser(String id) { // 로그인 후 유저정보 가져오기
    String sql = "SELECT USERID, NAME, PWD,REMAIN FROM PCUSER WHERE USERID = '" + id + "'";

    return excuteSelectBlist(sql).get(0);
  }

  private int excuteSelectInt(String sql) {// true 1 false 0
    Connection conn = DBConnect.getConnection();
    // preparedstatement 객체 생성
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    // return 객체
    // ArrayList<UserVO> blist = new ArrayList<UserVO>();
    int result = 0;
    try {
      conn = DBConnect.getConnection();
      pstmt = conn != null ? conn.prepareStatement(sql) : null;

      rs = pstmt.executeQuery();
      while (rs.next()) {
        if (rs.getString(1) != null) {
          System.out.println(rs.getString(1));
          result = 1;
        }
      }

    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      DBConnect.checkClose(rs, pstmt, conn);
    }
    return result;
  }

  private ArrayList<UserVO> excuteSelectBlist(String sql) {
    // DB connection 연결
    Connection conn = DBConnect.getConnection();
    // preparedstatement 객체 생성
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    // return 객체
    ArrayList<UserVO> blist = new ArrayList<UserVO>();

    try {
      pstmt = conn != null ? conn.prepareStatement(sql) : null;
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
  public int insertUser(String id, String pwd, String name) { // 유저 생성 성공 시 1반환, 실패 0반환
    String sql = "INSERT INTO PCUSER (USERID,PWD,NAME,REMAIN) VALUES ('" + id + "','" + pwd + "','"
        + name + "',0)";

    return excuteInsert(sql);
  }


  public int excuteInsert(String sql) {
    // DB connection 연결
    Connection conn = DBConnect.getConnection();
    // 실행쿼리
    PreparedStatement pstmt = null;
    // return 값
    int result = 0;
    try {
      // preparedstatement 객체 생성
      pstmt = conn != null ? conn.prepareStatement(sql) : null;
      // Resultset 결과값 담기
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      DBConnect.checkClose(null, pstmt, conn);
    }

    return result;
  }
  // insert end

  // updated
  public int chargeTime(String id, int time) {// 성공 1 실패 0
    String sql =
        "UPDATE PCUSER " + "SET REMAIN = " + time + " " + "WHERE USERID = '" + id + "'";

    return excuteUpdate(sql);
  }

  public void changeRemain(String id, int time) {// 성공 1 실패 0
    String sql = "UPDATE PCUSER SET REMAIN = " + time + " WHERE USERID = '" + id + "'";

    excuteUpdate(sql);
  }


  public int excuteUpdate(String sql) {
    Connection conn = DBConnect.getConnection();
    PreparedStatement pstmt = null;
    int result = 0;
    try {
      // preparedstatement 객체 생성
      pstmt = conn != null ? conn.prepareStatement(sql) : null;

      // Resultset 결과값 담기
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      DBConnect.checkClose(null, pstmt, conn);
    }
    return result;
  }// update end

}