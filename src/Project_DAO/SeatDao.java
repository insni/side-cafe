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
	private Connection conn; // DB Ŀ�ؼ�(����) ��ü
	private static final String USERNAME = "root"; // DB ���ӽ� ID
	private static final String PASSWORD = "1234"; // DB ���ӽ� �н�����
	private static final String URL = "jdbc:mysql://localhost:3306/cafeReservationDB";

	public SeatDao() {
		// connection��ü�� �����ؼ� DB�� ������.
		try {
			System.out.println("������");
			// ���� ��ü�� �������
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("����̹� �ε� ����!!");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("����̹� �ε� ����!!");
		}
	}

	public void insertSeat(Seat seat) {
		// ������ �غ�
		String sql = "insert into seat values(?,?,?,?);";
		// ���� ����Ѵ�.
		PreparedStatement pstmt = null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, seat.getNo());
			pstmt.setString(2, seat.getRsrvtStartDatetime());
			pstmt.setString(3, seat.getRsrvtEndsDatetime());
			pstmt.setInt(4, seat.getRmnngMinutes());
			// ������ �����϶�.
			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("seat ������ ���� ����!");
			}

		} catch (SQLException e) {
			System.out.println("seat ������ ���� ����!");
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
//		// ������ �غ�
//		String sql = "insert into seat values(?,?,?,?);";
//		// ���� ����Ѵ�.
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
//			// ������ �����϶�.
//			result = pstmt.executeUpdate();
//			if (result == 1) {
//				System.out.println("seat ������ ���� ����!");
//			}
//
//		} catch (SQLException e) {
//			System.out.println("seat ������ ���� ����!");
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
		// �������
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

	
	// �������
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



		 // cafeReservationDB -> menu�� drink �����͸� ��� �����´�.
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
	 // cafeReservationDB -> menu�� drink �����͸� ��� �����´�.
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