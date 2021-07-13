package hist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import dbcon.DBConnect;


public class HistDAO {
  // 전체 select
  public ArrayList<HistVO> getAllHist() {
    String sql = "SELECT * FROM HISTORY ROWNUM <= 100";

    return  excuteSelect(sql);
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
      pstmt = conn != null ? conn.prepareStatement(sql) : null;
      // Resultset 결과값 담기
      rs = pstmt != null ? pstmt.executeQuery() : null;
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
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      DBConnect.checkClose(rs, pstmt, conn);
    }
    return blist;
  }
  //// select 끝


  // insert (로그인, 로그아웃, 자리이동)
  public void insertHistory(String id, int seat, String status) { // 성공 시 1반환, 실패 0반환
    LocalDateTime ldt = LocalDateTime.now();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    int result = 0;
    String sql = "INSERT INTO HISTORY(HISID, USERID, TIME, STATUS, SEAT) " + "VALUES((select COUNT(HISID) from history) + 1 " + ",'"
        + id + "' " + ",'" + sdf.format(System.currentTimeMillis()) + "' " + ",'" + status + "' "
        + "," + seat + ")";

    excuteInsert(sql);
  }


	public void insertChargeTime(String id, int time, String status) { //시간충전로그
		LocalDateTime ldt = LocalDateTime.now();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int result = 0;
		String sql = "INSERT INTO HISTORY(HISID, USERID, TIME, STATUS, SEAT) " + "VALUES((select COUNT(HISID) from history) + 1 " + ",'" + id + "' "
				+ ",'" + sdf.format(System.currentTimeMillis()) + "' " + ",'" + time +status + "' " + "," + null + ")";

      excuteInsert(sql);
    }

	public void excuteInsert(String sql) {
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
        // TODO Auto-generated catch block
        e.printStackTrace();
      } finally {
        DBConnect.checkClose(null, pstmt, conn);
      }
    }
  // insert end
}