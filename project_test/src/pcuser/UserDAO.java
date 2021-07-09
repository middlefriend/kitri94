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

	public int checkID(String id) { // 회원가입 id중복체크 true 1 false 0
		String sql = "SELECT userid FROM PCUSER WHERE USERID='" + id + "'";

		ArrayList<UserVO> blist = new ArrayList<UserVO>();
		blist = excuteSelect(sql);

		if (blist.get(0).getName() != null)
			return 1;
		else
			return 0;

	}

	public int getAuth(String id, String pwd) {
		String sql = "SELECT userid FROM PCUSER WHERE USERID='" + id + "' AND PWD ='" + pwd + "'";

		ArrayList<UserVO> blist = new ArrayList<UserVO>();
		blist = excuteSelect(sql);

		if (blist.get(0).getName() != null)
			return 1;
		else
			return 0;
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
	public int insertUser(String id, String pwd, String name) { // 유저 생성 성공 시 1반환, 실패 0반환
		String sql = "INSERT INTO PCUSER (USERID,NAME,PWD) VALUES ('" + id + "','" + pwd + "','" + name + "')";
		// "";

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnect.checkClose(null, pstmt, conn);
		}

		return result;
	}
	// insert end

	// updated
	public int chargeTime(String id, int time) {
		String sql = "UPDATE PCUSER " + "SET REMAIN = " + time + " " + "WHERE USERID = '" + id + "'";

		if (excuteUpdate(sql) != 0)
			return 1;
		else
			return 0;
	}

	public int excuteUpdate(String sql) {

		Connection conn = DBConnect.getConnection();
		PreparedStatement pstmt = null;
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
		return result;
	}// update end

}
