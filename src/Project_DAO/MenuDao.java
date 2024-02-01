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
	private Connection conn;    //DB Ŀ�ؼ�(����) ��ü
    private static final String USERNAME = "root";   //DB ���ӽ� ID
    private static final String PASSWORD = "1234";	 //DB ���ӽ� �н�����
    private static final String URL = "jdbc:mysql://localhost:3306/cafeReservationDB";
    public MenuDao() {
        try {
            System.out.println("������");
            Class.forName("com.mysql.jdbc.Driver"); 
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("����̹� �ε� ����!!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("����̹� �ε� ����!!");
        }
    }    
    

    ObservableList<Menu> menuList = null;

    // cafeReservationDB -> menu�� �����͸� �߰��Ѵ�.
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
            	System.out.println("�޴� �߰� ����!");
            }           
        } catch (SQLException e) {            
        	System.out.println("�޴� �߰� ����!");
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
    // cafeReservationDB -> menu�� �����͸� �����Ѵ�.
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
        	System.out.println("�޴� ���� ����!");
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
    

    // cafeReservationDB -> menu���̺��� coffe��  goods�� �����Ѵ�.
    public int deleteCoffe(String goods) {
    	System.out.println(goods);
    	String sql = "delete from menu where idntfNmbr > 100 and idntfNmbr < 200 and goods=?;";
    	PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, goods);

            int result = pstmt.executeUpdate();
            if(result == 1) {
            	System.out.println(goods+" ���� ����!");
            	return 1;
            }           
            else {
            	return 0;
            }
        } catch (SQLException e) {            
        	System.out.println(goods+" ���� ����!");
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
    // cafeReservationDB -> menu���̺��� food��  goods�� �����Ѵ�.
    public int deleteFood(String goods) {
    	System.out.println(goods);
    	String sql = "delete from menu where idntfNmbr > 200 and idntfNmbr < 300 and goods=?;";
    	PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, goods);

            int result = pstmt.executeUpdate();
            if(result == 1) {
            	System.out.println(goods+" ���� ����!");
            	return 1;
            }           
            else {
            	return 0;
            }
        } catch (SQLException e) {            
        	System.out.println(goods+" ���� ����!");
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
    // cafeReservationDB -> menu���̺��� drink��  goods�� �����Ѵ�.
    public int deleteDrink(String goods) {
    	System.out.println(goods);
    	String sql = "delete from menu where idntfNmbr > 300 and idntfNmbr < 400 and goods=?;";
    	PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, goods);

            int result = pstmt.executeUpdate();
            if(result == 1) {
            	System.out.println(goods+" ���� ����!");
            	return 1;
            }           
            else {
            	return 0;
            }
        } catch (SQLException e) {            
        	System.out.println(goods+" ���� ����!");
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
        
    
    
    // cafeReservationDB -> menu�� ���ϴ� �����Ͱ� �ִ��� Ȯ���Ѵ�.
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
    
    // cafeReservationDB -> menu�� �����͸� �����Ѵ�.
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
    
    
    
 // ����� ��������
 	public int getIdntfNmbr(String goods) {
 		String sql = "select idntfNmbr from menu where goods=?;";
 		PreparedStatement pstmt = null;
 		int idntfNmbr=0;
 		try {
 			pstmt = conn.prepareStatement(sql);
 			System.out.println("cafeReservationDB menu�� idntfNmbr �������� �غ�Ϸ�");
 			pstmt.setString(1, goods);
 			ResultSet rs=pstmt.executeQuery();
 			System.out.println("cafeReservationDB menu�� idntfNmbr �������� ������ executeQuery()");
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
 		System.out.println("cafeReservationDB menu�� idntfNmbr �������� ����");
 		return idntfNmbr;
 	}
 	
	// ����� ��������
	public int getCount(int idntfNmbr) {
		String sql = "select count from menu where idntfNmbr=?;";
		PreparedStatement pstmt = null;
		int count=0;
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println("cafeReservationDB menu�� count �������� �غ�Ϸ�");
			pstmt.setInt(1, idntfNmbr);
			ResultSet rs=pstmt.executeQuery();
			System.out.println("cafeReservationDB menu�� count �������� ������ executeQuery()");
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
		System.out.println("cafeReservationDB menu�� count �������� ����");
		return count;
	}
	// ����� ����
 	public String updateCount(int idntfNmbr, int count) {
		String sql = "update menu set count=? where idntfNmbr = ?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setInt(2, idntfNmbr);
			pstmt.executeUpdate();
			System.out.println("CafeReservationDB menu ������ ����");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("CafeReservationDB menu ��������� �� ���ܹ߻� ! ");
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "CafeReservationDB menu ����� �����Ϸ�";
	}
    
    
    
    // cafeReservationDB -> menu�� �����͸� ��� �����´�.
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
    
    
    
    
    // cafeReservationDB -> menu�� coffe �����͸� ��� �����´�.
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
 // cafeReservationDB -> menu�� food �����͸� ��� �����´�.
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
 // cafeReservationDB -> menu�� drink �����͸� ��� �����´�.
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