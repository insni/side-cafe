package Project_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Project_Constructor.ReservationProperty;
import Project_Constructor.Rsrvt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RsrvtDao {
	private Connection conn; // DB 커넥션(연결) 객체
	private static final String USERNAME = "root"; // DB 접속시 ID
	private static final String PASSWORD = "1234"; // DB 접속시 패스워드
	private static final String URL = "jdbc:mysql://localhost:3306/cafeReservationDB";

	public RsrvtDao() {
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

	public void insertRsrvt(Rsrvt rsrvt) {
		// 쿼리문 준비
		String sql = "insert into rsrvt values(?,?,?,?,?,?,?,?,?);";
		// 많이 사용한다.
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rsrvt.getId());
			pstmt.setString(2, rsrvt.getName());
			pstmt.setString(3, rsrvt.getGoods());
			pstmt.setInt(4, rsrvt.getPrice());
			pstmt.setInt(5, rsrvt.getCount());
			pstmt.setInt(6, rsrvt.getNo());
			pstmt.setString(7, rsrvt.getRsrvtStartDateTime());
			pstmt.setString(8, rsrvt.getRsrvtEndsDateTime());
			pstmt.setInt(9, rsrvt.getRmnngMinutes());
			// 쿼리문 실행하라.
			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("reservation 데이터 삽입 성공!");
			}

		} catch (SQLException e) {
			System.out.println("reservation 데이터 삽입 실패!");
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

	public Rsrvt selectOne(String id) {
		String sql = "select * from rsrvt where id =?;";
		PreparedStatement pstmt = null;
		Rsrvt rsrvt = new Rsrvt();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				rsrvt.setId(rs.getString("id"));
				rsrvt.setName(rs.getString("name"));
				rsrvt.setCount(rs.getInt("count"));
				rsrvt.setGoods(rs.getString("goods"));
				rsrvt.setNo(rs.getInt("no"));
				rsrvt.setPrice(rs.getInt("price"));
				rsrvt.setRsrvtStartDateTime(rs.getString("rsrvtStartDateTime"));
				rsrvt.setRsrvtEndsDateTime(rs.getString("rsrvtEndsDateTime"));
				rsrvt.setRmnngMinutes(rs.getInt("rmnngMinutes"));

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
		return rsrvt;
	}

	public Rsrvt selectOne(int no) {
		String sql = "select * from rsrvt where no=?;";
		PreparedStatement pstmt = null;
		Rsrvt rsrvt = new Rsrvt();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("executeQuery()");
			if (rs.next()) {
				rsrvt.setId(rs.getString("id"));
				rsrvt.setName(rs.getString("name"));
				rsrvt.setGoods(rs.getString("goods"));
				rsrvt.setNo(rs.getInt("no"));
				rsrvt.setPrice(rs.getInt("price"));
				rsrvt.setRsrvtStartDateTime(rs.getString("rsrvtStartTime"));
				rsrvt.setRsrvtEndsDateTime(rs.getString("rsrvtEndsTime"));
				rsrvt.setRmnngMinutes(rs.getInt("rmnngMinutes"));

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
		return rsrvt;
	}

	public Rsrvt selectRsrvtStartDateTime(String rsrvtStartDateTime) {
		String sql = "select * from rsrvt where rsrvtStartDateTime=?;";
		PreparedStatement pstmt = null;
		Rsrvt rsrvt = new Rsrvt();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rsrvtStartDateTime);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("executeQuery()");
			if (rs.next()) {
				rsrvt.setId(rs.getString("id"));
				rsrvt.setName(rs.getString("name"));
				rsrvt.setGoods(rs.getString("goods"));
				rsrvt.setPrice(rs.getInt("price"));
				rsrvt.setCount(rs.getInt("count"));
				rsrvt.setNo(rs.getInt("no"));
				rsrvt.setRsrvtStartDateTime(rs.getString("rsrvtStartDateTime"));
				rsrvt.setRsrvtEndsDateTime(rs.getString("rsrvtEndsDateTime"));
				rsrvt.setRmnngMinutes(rs.getInt("rmnngMinutes"));
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
		return rsrvt;
	}

	public Rsrvt selectOne(Rsrvt rsrvt) {
		String sql = "select * from rsrvt where id=? " + "	and name=? and goods=? and price=?"
				+ " and count=? and no=? and rsrvtStartDateTime=?" + "and rsrvtEndsDateTime=? and rmnngMinutes=?;";

		PreparedStatement pstmt = null;
		Rsrvt r = new Rsrvt();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, r.getId());
			pstmt.setString(2, r.getName());
			pstmt.setString(3, r.getGoods());
			pstmt.setInt(4, r.getPrice());
			pstmt.setInt(5, r.getCount());
			pstmt.setInt(6, r.getNo());
			pstmt.setString(7, r.getRsrvtEndsDateTime());
			pstmt.setString(8, r.getRsrvtEndsDateTime());
			pstmt.setInt(9, r.getRmnngMinutes());
			ResultSet rs = pstmt.executeQuery();
			System.out.println("executeQuery()");
			if (rs.next()) {
				rsrvt.setId(rs.getString("id"));
				rsrvt.setName(rs.getString("name"));
				rsrvt.setGoods(rs.getString("goods"));
				rsrvt.setPrice(rs.getInt("price"));
				rsrvt.setCount(rs.getInt("count"));
				rsrvt.setNo(rs.getInt("no"));
				rsrvt.setRsrvtStartDateTime(rs.getString("rsrvtStartTime"));
				rsrvt.setRsrvtEndsDateTime(rs.getString("rsrvtEndsTime"));
				rsrvt.setRmnngMinutes(rs.getInt("rmnngMinutes"));
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
		return rsrvt;
	}

	public void changeTime(String name, String selectTime) {
		String sql = "update rsrvt set name=? where selectTime = ?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, selectTime);
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
	public void cancel(ReservationProperty reservationProperty) {
		String sql = "delete from rsrvt where no=? and id=? and name=?"
    			+ "	  and goods=? and count=? and rsrvtStartDateTime=?"
    			+ "   and rsrvtEndsDateTime=? and price=? and rmnngMinutes=?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reservationProperty.getNo());
			pstmt.setString(2, reservationProperty.getId());
			pstmt.setString(3, reservationProperty.getName());
			pstmt.setString(4, reservationProperty.getGoods());
			pstmt.setString(5, reservationProperty.getCount());
			pstmt.setString(6, reservationProperty.getRsrvtStartDateTime());
			pstmt.setString(7, reservationProperty.getRsrvtEndsDateTime());
			pstmt.setString(8, reservationProperty.getPrice());
			pstmt.setString(9, reservationProperty.getRmnngMinutes());
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
	
	// 예약한 데이터 모두 가져오기
	public List<Rsrvt> slectAll() {
		String sql = "select * from rsrvt;";
		PreparedStatement pstmt = null;
		List<Rsrvt> list = new ArrayList<Rsrvt>();
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet re = pstmt.executeQuery();

			while (re.next()) {
				Rsrvt rsrvt = new Rsrvt();
				rsrvt.setId(re.getString("id"));
				rsrvt.setName(re.getString("name"));
				rsrvt.setGoods(re.getString("goods"));
				rsrvt.setPrice(re.getInt("price"));
				rsrvt.setCount(re.getInt("count"));
				rsrvt.setNo(re.getInt("no"));
				rsrvt.setRsrvtStartDateTime(re.getString("rsrvtStartDateTime"));
				rsrvt.setRsrvtEndsDateTime(re.getString("rsrvtEndsDateTime"));
				rsrvt.setRmnngMinutes(re.getInt("rmnngMinutes"));
				list.add(rsrvt);
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
	// 예약한 데이터 모두 가져오기
	public List<Rsrvt> slectAll(int no) {
		String sql = "select * from rsrvt where no=?;";
		PreparedStatement pstmt = null;
		List<Rsrvt> list = new ArrayList<Rsrvt>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			ResultSet re = pstmt.executeQuery();
			while (re.next()) {
				Rsrvt rsrvt = new Rsrvt();
				rsrvt.setId(re.getString("id"));
				rsrvt.setName(re.getString("name"));
				rsrvt.setGoods(re.getString("goods"));
				rsrvt.setPrice(re.getInt("price"));
				rsrvt.setCount(re.getInt("count"));
				rsrvt.setNo(re.getInt("no"));
				rsrvt.setRsrvtStartDateTime(re.getString("rsrvtStartDateTime"));
				rsrvt.setRsrvtEndsDateTime(re.getString("rsrvtEndsDateTime"));
				rsrvt.setRmnngMinutes(re.getInt("rmnngMinutes"));
				list.add(rsrvt);
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
		
	ObservableList<ReservationProperty> rsrvtList=null;
    public ObservableList<ReservationProperty> selectOneDate(String date) {
    	rsrvtList.clear();
    	rsrvtList = FXCollections.observableArrayList();
    	String sql = "select * from rsrvt where rsrvtStartDateTime = ?;";
    	PreparedStatement pstmt = null;
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ReservationProperty rsrvt = new ReservationProperty(
						rs.getInt("no"),
						rs.getString("id"),
						rs.getString("name"),
						rs.getString("goods"),
						rs.getInt("count"),
						rs.getString("rsrvtStartDateTime"),
						rs.getString("rsrvtEndsDateTime"),
						rs.getInt("price"), 
						rs.getInt("rmnngMinutes"));
				rsrvtList.add(rsrvt);
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
    	return rsrvtList;
    }


    public ObservableList<ReservationProperty> selectOne(ReservationProperty reservationProperty) {
    	rsrvtList.clear();
    	rsrvtList = FXCollections.observableArrayList();
    	String sql = "select * from rsrvt where no=? and id=? name=?"
    			+ "	  and goods=? and count=? and rsrvtStartDateTime=?"
    			+ "   and rsrvtEndsDateTime=? and price=? and rmnngMinutes=?;";
    	PreparedStatement pstmt = null;
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reservationProperty.getNo());
			pstmt.setString(2, reservationProperty.getId());
			pstmt.setString(3, reservationProperty.getName());
			pstmt.setString(4, reservationProperty.getGoods());
			pstmt.setString(5, reservationProperty.getCount());
			pstmt.setString(6, reservationProperty.getRsrvtStartDateTime());
			pstmt.setString(7, reservationProperty.getRsrvtEndsDateTime());
			pstmt.setString(8, reservationProperty.getPrice());
			pstmt.setString(9, reservationProperty.getRmnngMinutes());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ReservationProperty rsrvt = new ReservationProperty(
						rs.getInt("no"),
						rs.getString("id"),
						rs.getString("name"),
						rs.getString("goods"),
						rs.getInt("count"),
						rs.getString("rsrvtStartDateTime"),
						rs.getString("rsrvtEndsDateTime"),
						rs.getInt("price"), 
						rs.getInt("rmnngMinutes"));
				rsrvtList.add(rsrvt);
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
    	return rsrvtList;
    }
}