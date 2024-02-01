package Project_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Project_Constructor.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MenuDao {
	private Connection conn;    //DB 커넥션(연결) 객체
    private static final String USERNAME = "root";   //DB 접속시 ID
    private static final String PASSWORD = "1234";	 //DB 접속시 패스워드
    private static final String URL = "jdbc:mysql://localhost:3306/cafeReservationDB";
    public MenuDao() {
        try {
            System.out.println("생성자");
            Class.forName("com.mysql.jdbc.Driver"); 
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("드라이버 로딩 성공!!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("드라이버 로드 실패!!");
        }
    }    
    

    ObservableList<Menu> menuList = null;

    // cafeReservationDB -> menu의 데이터를 추가한다.
    public void insert(Menu menu) {
    	String sql = "insert into menu values(?,?,?,?);";
    	PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.valueOf(menu.getIdntfNmbr()));
            pstmt.setString(2, menu.getGoods());
            pstmt.setInt(3, Integer.valueOf(menu.getPrice()));
            pstmt.setInt(4, Integer.valueOf(menu.getCount()));
            int result = pstmt.executeUpdate();
            if(result == 1) {
            	System.out.println("메뉴 추가 성공!");
            }           
        } catch (SQLException e) {            
        	System.out.println("메뉴 추가 실패!");
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
    // cafeReservationDB -> menu의 데이터를 삭제한다.
    public int delete(String goods) {
    	System.out.println(goods);
    	String sql = "delete from menu where goods = ?;";
    	PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, goods);

            int result = pstmt.executeUpdate();
            if(result == 1) {
            	return 1;
            }           
            else {
            	return 0;
            }
        } catch (SQLException e) {            
        	System.out.println("메뉴 삭제 실패!");
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {                
                e.printStackTrace();
            }
        }
    }
    

    // cafeReservationDB -> menu테이블의 coffe의  goods를 삭제한다.
    public int deleteCoffe(String goods) {
    	System.out.println(goods);
    	String sql = "delete from menu where idntfNmbr > 100 and idntfNmbr < 200 and goods=?;";
    	PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, goods);

            int result = pstmt.executeUpdate();
            if(result == 1) {
            	System.out.println(goods+" 삭제 성공!");
            	return 1;
            }           
            else {
            	return 0;
            }
        } catch (SQLException e) {            
        	System.out.println(goods+" 삭제 실패!");
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {                
                e.printStackTrace();
            }
        }
    }
    // cafeReservationDB -> menu테이블의 food의  goods를 삭제한다.
    public int deleteFood(String goods) {
    	System.out.println(goods);
    	String sql = "delete from menu where idntfNmbr > 200 and idntfNmbr < 300 and goods=?;";
    	PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, goods);

            int result = pstmt.executeUpdate();
            if(result == 1) {
            	System.out.println(goods+" 삭제 성공!");
            	return 1;
            }           
            else {
            	return 0;
            }
        } catch (SQLException e) {            
        	System.out.println(goods+" 삭제 실패!");
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {                
                e.printStackTrace();
            }
        }
    }
    // cafeReservationDB -> menu테이블의 drink의  goods를 삭제한다.
    public int deleteDrink(String goods) {
    	System.out.println(goods);
    	String sql = "delete from menu where idntfNmbr > 300 and idntfNmbr < 400 and goods=?;";
    	PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, goods);

            int result = pstmt.executeUpdate();
            if(result == 1) {
            	System.out.println(goods+" 삭제 성공!");
            	return 1;
            }           
            else {
            	return 0;
            }
        } catch (SQLException e) {            
        	System.out.println(goods+" 삭제 실패!");
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {                
                e.printStackTrace();
            }
        }
    }
        
    
    
    // cafeReservationDB -> menu에 원하는 데이터가 있는지 확인한다.
    public int verify(String goods) {
        String sql = "select * from menu where goods = ?;";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, goods);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
            	return 1;
            }
            else {
            	return 0;
            }
        } catch (SQLException e) {
        	return 0;
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // cafeReservationDB -> menu의 데이터를 변경한다.
    public int update(String goods, String newGoods, int newPrice,int newCount) {
    	String sql = "update menu set goods = ?, price = ?, count=? where goods= ?;";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newGoods);
            pstmt.setInt(2, newPrice);
            pstmt.setInt(3, newCount);
            pstmt.setString(4, goods);
            int result = pstmt.executeUpdate();
            if (result == 1) {
            	return 1;
            }
            else {
            	return 0;
            }
        } catch (SQLException e) {
        	return 0;
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    
 // 재고물량 가져오기
 	public int getIdntfNmbr(String goods) {
 		String sql = "select idntfNmbr from menu where goods=?;";
 		PreparedStatement pstmt = null;
 		int idntfNmbr=0;
 		try {
 			pstmt = conn.prepareStatement(sql);
 			System.out.println("cafeReservationDB menu의 idntfNmbr 가져오기 준비완료");
 			pstmt.setString(1, goods);
 			ResultSet rs=pstmt.executeQuery();
 			System.out.println("cafeReservationDB menu의 idntfNmbr 가져오기 쿼리문 executeQuery()");
 			if(rs.next()) {
 				idntfNmbr=rs.getInt("idntfNmbr");
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
 		System.out.println("cafeReservationDB menu의 idntfNmbr 가져오기 성공");
 		return idntfNmbr;
 	}
 	
	// 재고물량 가져오기
	public int getCount(int idntfNmbr) {
		String sql = "select count from menu where idntfNmbr=?;";
		PreparedStatement pstmt = null;
		int count=0;
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println("cafeReservationDB menu의 count 가져오기 준비완료");
			pstmt.setInt(1, idntfNmbr);
			ResultSet rs=pstmt.executeQuery();
			System.out.println("cafeReservationDB menu의 count 가져오기 쿼리문 executeQuery()");
			if(rs.next()) {
				count=rs.getInt("count");
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
		System.out.println("cafeReservationDB menu의 count 가져오기 성공");
		return count;
	}
	// 재고물량 조정
 	public String updateCount(int idntfNmbr, int count) {
		String sql = "update menu set count=? where idntfNmbr = ?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setInt(2, idntfNmbr);
			pstmt.executeUpdate();
			System.out.println("CafeReservationDB menu 데이터 삽입");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("CafeReservationDB menu 재고물량수정 중 예외발생 ! ");
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "CafeReservationDB menu 재고물량 수정완료";
	}
    
    
    
    // cafeReservationDB -> menu의 데이터를 모두 가져온다.
    public ObservableList<Menu> selectAll() {
        menuList = FXCollections.observableArrayList();
    	String sql = "select * from menu;";
    	PreparedStatement pstmt = null;
    	try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Menu menu = new Menu(
						rs.getString("idntfNmbr"),
						rs.getString("goods"),
						rs.getString("price"),
						rs.getString("count"));
				menuList.add(menu);
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
    	return menuList;
    }
    
    
    
    
    // cafeReservationDB -> menu의 coffe 데이터를 모두 가져온다.
    public ObservableList<Menu> selectAllCoffe() {
        menuList = FXCollections.observableArrayList();
    	String sql = "select * from menu where idntfNmbr > 100 and idntfNmbr < 200;";
    	PreparedStatement pstmt = null;
    	try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Menu menu = new Menu(rs.getString("idntfNmbr"), rs.getString("goods"),
						rs.getString("price"),rs.getString("count"));
				menuList.add(menu);
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
    	return menuList;
    }
 // cafeReservationDB -> menu의 food 데이터를 모두 가져온다.
    public ObservableList<Menu> selectAllFood() {
        menuList = FXCollections.observableArrayList();
    	String sql = "select * from menu where idntfNmbr > 200 and idntfNmbr < 300;";
    	PreparedStatement pstmt = null;
    	try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Menu menu = new Menu(rs.getString("idntfNmbr"), rs.getString("goods"),
						rs.getString("price"),rs.getString("count"));
				menuList.add(menu);
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
    	return menuList;
    }
 // cafeReservationDB -> menu의 drink 데이터를 모두 가져온다.
    public ObservableList<Menu> selectAllDrink() {
        menuList = FXCollections.observableArrayList();
    	String sql = "select * from menu where idntfNmbr > 300 and idntfNmbr < 400;";
    	PreparedStatement pstmt = null;
    	try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Menu menu = new Menu(rs.getString("idntfNmbr"), rs.getString("goods"),
						rs.getString("price"),rs.getString("count"));
				menuList.add(menu);
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
    	return menuList;
    }
    


}