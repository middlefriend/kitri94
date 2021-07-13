package src.hist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import src.dbcon.DBConnect;

public class HistDAO {
  // 전체 select
  public ArrayList<HistVO> getAllHist() {
    String sql = "SELECT * FROM HISTORY ROWNUM <= 100";

    ArrayList<HistVO> blist = new ArrayList<HistVO>();
    blist = excuteSelect(sql);

    return blist;
  }


  private ArrayList<HistVO> excuteSelect(String sql) {
    // DB connection 연결
    Connection conn = DBConnect.getConnection();
    // preparedstatement 객체 생성
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    // return 객체
    ArrayList<HistVO> blist = new ArrayList<HistVO>();
    try {
      pstmt = conn.prepareStatement(sql);
      // Resultset 결과값 담기
      rs = pstmt.executeQuery();
      // List에 결과값 담기

      HistVO hvo = null;
      while (rs.next()) {
        hvo = new HistVO();
        hvo.setHistId(rs.getString("HISID"));
        hvo.setUserId(rs.getString("USERID"));
        hvo.setDateTime(rs.getString("TIME"));
        hvo.setStatus(rs.getString("STATUS"));
        hvo.setSeat(rs.getInt("SEAT"));

        blist.add(hvo);
      }
    } catch (SQLException e) {
      // e.printStackTrace();
    } finally {
      DBConnect.checkClose(rs, pstmt, conn);
    }
    return blist;
  }
  //// select 끝


  // insert 자리이동,로그인,로그아웃, 시간충전
  public int insertHistory(String id, int seat, String status) { // 성공 시 1반환, 실패 0반환
    LocalDateTime ldt = LocalDateTime.now();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String sql = "INSERT INTO HISTORY " + "VALUES ( (select COUNT(HISID) from history) + 1 " + ",'"
        + id + "' " + ",'" + sdf.format(System.currentTimeMillis()) + "' " + ",'" + status + "' "
        + "," + seat + ")";

    if (excuteInsert(sql) != 0)
      return 1;
    else
      return 0;
  }

  public int insertChargeTime(String id, int time, String status) {
    LocalDateTime ldt = LocalDateTime.now();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String sql = "INSERT INTO HISTORY " + "VALUES ( (select COUNT(HISID) from history) + 1 " + ",'"
        + id + "' " + ",'" + sdf.format(System.currentTimeMillis()) + "' " + ",'" + time + status
        + "' " + "," + null + ")";
    
    
    if (excuteInsert(sql) != 0)
      return 1;
    else
      return 0;
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
      pstmt = conn.prepareStatement(sql);
      // Resultset 결과값 담기
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      // e.printStackTrace();
    } finally {
      DBConnect.checkClose(null, pstmt, conn);
    }

    return result;
  }
  // insert end
}
