package Project_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Project_Constructor.Seat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SeatDao {
	private Connection conn; // DB 커넥션(연결) 객체
	private static final String USERNAME = "root"; // DB 접속시 ID
	private static final String PASSWORD = "1234"; // DB 접속시 패스워드
	private static final String URL = "jdbc:mysql://localhost:3306/cafeReservationDB";

	public SeatDao() {
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

	public void insertSeat(Seat seat) {
		// 쿼리문 준비
		String sql = "insert into seat values(?,?,?,?);";
		// 많이 사용한다.
		PreparedStatement pstmt = null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, seat.getNo());
			pstmt.setString(2, seat.getRsrvtStartDatetime());
			pstmt.setString(3, seat.getRsrvtEndsDatetime());
			pstmt.setInt(4, seat.getRmnngMinutes());
			// 쿼리문 실행하라.
			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("seat 데이터 삽입 성공!");
			}

		} catch (SQLException e) {
			System.out.println("seat 데이터 삽입 실패!");
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
//	public void insertOne(int no, String dateTime) {
//		// 쿼리문 준비
//		String sql = "insert into seat values(?,?,?,?);";
//		// 많이 사용한다.
//		PreparedStatement pstmt = null;
//		try {
//			pstmt=conn.prepareStatement(sql2);
//			int result = pstmt.executeUpdate();
//			pstmt.setString(1, dateTime);
//			result = pstmt.executeUpdate();
//			pstmt.close();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, no);
//			pstmt.setString(2, dateTime);
//			// 쿼리문 실행하라.
//			result = pstmt.executeUpdate();
//			if (result == 1) {
//				System.out.println("seat 데이터 삽입 성공!");
//			}
//
//		} catch (SQLException e) {
//			System.out.println("seat 데이터 삽입 실패!");
//			e.printStackTrace();
//		} finally {
//			try {
//				if (pstmt != null && !pstmt.isClosed())
//					pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	public Seat selectOne(Seat seat) {
		String sql ="select * from seat where no= ? and rsrvtStartDateTime=? and rsrvtEndsDateTime=? and rmnngMinutes=?;";
		PreparedStatement pstmt = null	;
		Seat s = new Seat();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seat.getNo());
			pstmt.setString(2, seat.getRsrvtStartDatetime());
			pstmt.setString(3, seat.getRsrvtEndsDatetime());
			pstmt.setInt(4, seat.getRmnngMinutes());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				s.setNo(rs.getInt("no"));
				s.setRsrvtStartDatetime(rs.getString("rsrvtStartDateTime"));
				s.setRsrvtEndsDatetime(rs.getString("rsrvtEndsDateTime"));
				s.setRmnngMinutes(rs.getInt("rmnngMinutes"));
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
		return s;
	}
	

//	public Seat selectOne(int no,int day) {
//		String sql = "select * from seat where no=? and datetime=?;";
//		PreparedStatement pstmt = null;
//		Seat seat = new Seat();
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, no);
//			pstmt.setInt(2, day);
//			ResultSet rs = pstmt.executeQuery();
//			System.out.println("executeQuery()");
//			if (rs.next()) {
//				seat.setNo(rs.getInt("no"));
//				seat.setDay(rs.getString("datetime"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (pstmt != null && !pstmt.isClosed())
//					pstmt.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		}
//		return seat;
//	}	
		// 예약취소
		public void delete(Seat seat) {
			String sql = "delete from seat where no= ? and rsrvtStartDatetime=? and rsrvtEndsDatetime=? and rmnngMinutes=?;";
			PreparedStatement pstmt = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, seat.getNo());
				pstmt.setString(2, seat.getRsrvtStartDatetime());
				pstmt.setString(3, seat.getRsrvtStartDatetime());
				pstmt.setInt(4, seat.getRmnngMinutes());
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


	
//	public void changeTime(String name, String selectTime) {
//		String sql = "update resrvation set name=? where selectTime = ?;";
//		PreparedStatement pstmt = null;
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, name);
//			pstmt.setString(2, selectTime);
//			pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (pstmt != null && !pstmt.isClosed())
//					pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	
	// 예약취소
//	public void cancel(String id) {
//		String sql = "delete from resrvation where id = ?;";
//		PreparedStatement pstmt = null;
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
//			pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (pstmt != null && !pstmt.isClosed())
//					pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}



		 // cafeReservationDB -> menu의 drink 데이터를 모두 가져온다.
	    public ObservableList<Seat> selectOne(int no) {
	    	seatList = FXCollections.observableArrayList();
	    	String sql = "select * from seat where no=?;";
	    	PreparedStatement pstmt = null;
	    	try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					Seat seat = new Seat();
					seat.setNo(rs.getInt("no"));
					seat.setRsrvtStartDatetime(rs.getString("rsrvtStartDatetime"));
					seat.setRsrvtEndsDatetime(rs.getString("rsrvtEndsDatetime"));
					seat.setRmnngMinutes(rs.getInt("rmnngMinutes"));
					seatList.add(seat);
				}
	    	} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(pstmt!=null&&!pstmt.isClosed()) {
						pstmt.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
	    	return seatList;
	    }
	
	ObservableList<Seat> seatList = null;
	 // cafeReservationDB -> menu의 drink 데이터를 모두 가져온다.
    public ObservableList<Seat> selectAll() {
    	seatList = FXCollections.observableArrayList();
    	String sql = "select * from seat;";
    	PreparedStatement pstmt = null;
    	try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Seat seat = new Seat();
				seat.setNo(rs.getInt("no"));
				seat.setRsrvtStartDatetime(rs.getString("rsrvtStartDatetime"));
				seat.setRsrvtEndsDatetime(rs.getString("rsrvtEndsDatetime"));
				seat.setRmnngMinutes(rs.getInt("rmnngMinutes"));
				seatList.add(seat);
			}
    	} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null&&!pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
    	return seatList;
    }
    

}