package Project_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Project_Constructor.RsrvtDateTime;

public class RsrvtDateTimeDao {
	private Connection conn; // DB 커넥션(연결) 객체
	private static final String USERNAME = "root"; // DB 접속시 ID
	private static final String PASSWORD = "1234"; // DB 접속시 패스워드
	private static final String URL = "jdbc:mysql://localhost:3306/cafeReservationDB";

	public RsrvtDateTimeDao() {
		// connection객체를 생성해서 DB에 연결함.
		try {
			System.out.println("생성자");
			// 동적 객체를 만들어줌
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("드라이버 로딩 성공!!");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("드라이버 로드 실패!!");
		}
	}

	public void insert(RsrvtDateTime rsrvtDateTime) {
		// 쿼리문 준비
		String sql = "insert into rsrvtDateTime values(?);";
		// 많이 사용한다.
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rsrvtDateTime.getDateTime());
			// 쿼리문 실행하라.
			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("rsrvtDateTime 데이터 삽입 성공!");
			}

		} catch (SQLException e) {
			System.out.println("rsrvtDateTime 데이터 삽입 실패!");
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public RsrvtDateTime selectOne(String dateTime) {
		String sql = "select * from rsrvtDateTime where dateTime =?;";
		PreparedStatement pstmt = null	;
		RsrvtDateTime rsrvtDateTime= new RsrvtDateTime();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dateTime);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				rsrvtDateTime.setDateTime(rs.getString("dateTime"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return rsrvtDateTime;
	}
	
	
	
	public void update(String dateTime) {
		String sql = "update rsrvtDateTime set dateTime=? where dateTime= ?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dateTime);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	// 예약취소
	public void deleteOne(String dateTime) {
		String sql = "delete from rsrvtDateTime datetime = ?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dateTime);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	

	public List<RsrvtDateTime> slectAll() {
		String sql = "select * from rsrvtDateTime;";
		PreparedStatement pstmt = null;
		List<RsrvtDateTime> list = new ArrayList<RsrvtDateTime>();
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet re = pstmt.executeQuery();

			while (re.next()) { 
				RsrvtDateTime rsrvtDateTime=new RsrvtDateTime();
				rsrvtDateTime.setDateTime(re.getString("dateTime"));
				list.add(rsrvtDateTime);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}