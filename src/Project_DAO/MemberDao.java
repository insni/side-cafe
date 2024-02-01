package Project_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Project_Constructor.Member;

public class MemberDao {
	private Connection conn; // DB Ŀ�ؼ�(����) ��ü
	private static final String USERNAME = "root"; // DB ���ӽ� ID
	private static final String PASSWORD = "1234"; // DB ���ӽ� �н�����
	private static final String URL = "jdbc:mysql://localhost:3306/cafeReservationDB";

	public MemberDao() {
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

	public void insertMember(Member member) {
		// ������ �غ�
		String sql = "insert into member values(?,?,?,?,?,?,?,?);";
		// ���� ����Ѵ�.
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId()); // ���̵� ����
			pstmt.setString(2, member.getPw()); // ��й�ȣ ����
			pstmt.setString(3, member.getName()); // �̸� ����
			pstmt.setString(4, member.getPhone()); // ��ȭ��ȣ ����
			pstmt.setString(5, member.getEmail()); // �̸��� ����
			pstmt.setString(6, member.getQuestion()); // ������������
			pstmt.setString(7, member.getAnswer()); // �����亯����
			pstmt.setString(8, member.getJoinDate()); // ������ ����
			// ������ �����϶�.
			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("Member������ ���� ����!");
				System.out.println(member);
			}

		} catch (SQLException e) {
			System.out.println("Member������ ���� ����!");
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

	public Member selectOne(String id) {
		String sql = "select * from member where id =?;";
		PreparedStatement pstmt = null;
		Member member = new Member();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("email"));
				member.setQuestion(rs.getString("question"));
				member.setAnswer(rs.getString("answer"));
				member.setJoinDate(rs.getString("joinDate"));
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
		return member;
	}

	public Member selectOne(String id, String pw) {
		String sql = "select * from member where id =? and pw=?;";
		PreparedStatement pstmt = null;
		Member member = new Member();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("executeQuery()");
			if (rs.next()) {

				member.setId((rs.getString("id")));
				member.setPw(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("email"));
				member.setQuestion(rs.getString("question"));
				member.setAnswer(rs.getString("answer"));
				member.setJoinDate(rs.getString("joinDate"));

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
		System.out.println("return member()");
		return member;
	}

	// ��й�ȣ �ٲٱ�
	public void updatePw(Member member, String pw) {
		String sql = "update member set pw=? where id = ?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, member.getId());
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

	// �� ��ȣ�ٲٱ�
	public void updatePhone(Member member, String phone) {
		String sql = "update member set phone=? where id = ?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phone);
			pstmt.setString(2, member.getId());
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

	// �̸��� �ٲٱ�
	public void updateEmail(Member member, String email) {
		String sql = "update member set email=? where id = ?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, member.getId());
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

	// ���ǿ� �´� ���� DB���� �����ϴ� �޼���
	public void deleteMember(Member member) {
		String sql = "delete from member where id = ?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
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

	public int selectEmail(String email) {
		String sql = "select email from member where email=?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return 1;

			} else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	// Member���̺� �����ϴ� ��� ���� �������� �޼�����
	public List<Member> slectAll() {

		String sql = "select * from member;";
		PreparedStatement pstmt = null;
		List<Member> list = new ArrayList<Member>();
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet re = pstmt.executeQuery();

			while (re.next()) { // �����ð� �ִ���?
				Member member = new Member();
				member.setId(re.getString("id"));
				member.setPw(re.getString("pw"));
				member.setName(re.getString("name"));
				member.setPhone(re.getString("phone"));
				member.setEmail(re.getString("email"));
				member.setQuestion(re.getString("question"));
				member.setAnswer(re.getString("answer"));
				member.setJoinDate(re.getString("joinDate"));
				list.add(member); // List<Member>���ٰ� �߰���.
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