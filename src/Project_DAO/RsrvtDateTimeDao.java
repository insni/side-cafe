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
	private Connection conn; // DB Ŀ�ؼ�(����) ��ü
	private static final String USERNAME = "root"; // DB ���ӽ� ID
	private static final String PASSWORD = "1234"; // DB ���ӽ� �н�����
	private static final String URL = "jdbc:mysql://localhost:3306/cafeReservationDB";

	public RsrvtDateTimeDao() {
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

	public void insert(RsrvtDateTime rsrvtDateTime) {
		// ������ �غ�
		String sql = "insert into rsrvtDateTime values(?);";
		// ���� ����Ѵ�.
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rsrvtDateTime.getDateTime());
			// ������ �����϶�.
			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("rsrvtDateTime ������ ���� ����!");
			}

		} catch (SQLException e) {
			System.out.println("rsrvtDateTime ������ ���� ����!");
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

	
	
	// �������
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