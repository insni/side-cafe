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
	private Connection conn; // DB 커넥션(연결) 객체
	private static final String USERNAME = "root"; // DB 접속시 ID
	private static final String PASSWORD = "1234"; // DB 접속시 패스워드
	private static final String URL = "jdbc:mysql://localhost:3306/cafeReservationDB";

	public MemberDao() {
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

	public void insertMember(Member member) {
		// 쿼리문 준비
		String sql = "insert into member values(?,?,?,?,?,?,?,?);";
		// 많이 사용한다.
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId()); // 아이디 매핑
			pstmt.setString(2, member.getPw()); // 비밀번호 매핑
			pstmt.setString(3, member.getName()); // 이름 매핑
			pstmt.setString(4, member.getPhone()); // 전화번호 매핑
			pstmt.setString(5, member.getEmail()); // 이메일 매핑
			pstmt.setString(6, member.getQuestion()); // 질문유형매핑
			pstmt.setString(7, member.getAnswer()); // 질문답변매핑
			pstmt.setString(8, member.getJoinDate()); // 가입일 메핑
			// 쿼리문 실행하라.
			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("Member데이터 삽입 성공!");
				System.out.println(member);
			}

		} catch (SQLException e) {
			System.out.println("Member데이터 삽입 실패!");
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

	// 비밀번호 바꾸기
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

	// 폰 번호바꾸기
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

	// 이메일 바꾸기
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

	// 조건에 맞는 행을 DB에서 삭제하는 메서드
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

	// Member테이블에 존재하는 모든 행을 가져오는 메서드임
	public List<Member> slectAll() {

		String sql = "select * from member;";
		PreparedStatement pstmt = null;
		List<Member> list = new ArrayList<Member>();
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet re = pstmt.executeQuery();

			while (re.next()) { // 가져올게 있느냐?
				Member member = new Member();
				member.setId(re.getString("id"));
				member.setPw(re.getString("pw"));
				member.setName(re.getString("name"));
				member.setPhone(re.getString("phone"));
				member.setEmail(re.getString("email"));
				member.setQuestion(re.getString("question"));
				member.setAnswer(re.getString("answer"));
				member.setJoinDate(re.getString("joinDate"));
				list.add(member); // List<Member>에다가 추가함.
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