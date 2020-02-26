package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	public List<BoardVo> findAll() {
		List<BoardVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
		
			String sql = "select a.no, title, contents, hit, reg_date, g_no, o_no, depth, user_no, b.name from board a, user b where a.user_no = b.no order by g_no desc, o_no asc";
			pst = conn.prepareStatement(sql);

			rs = pst.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				int hit = rs.getInt(4);
				String regDate = rs.getString(5);
				int groupNo = rs.getInt(6);
				int groupOrNo = rs.getInt(7);
				int depth = rs.getInt(8);
				Long userNo = rs.getLong(9);
				String name = rs.getString(10);

				BoardVo vo = new BoardVo();

				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setGroupNo(groupNo);
				vo.setGroupOrNo(groupOrNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setName(name);

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 자원 정리
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public List<BoardVo> search(String kwd, String radioValue) {
		List<BoardVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			// String sql = "select no, name, contents, password,
			// date_format(reg_date,'%Y-%m-%d %h:%i:%s') from guestbook order by reg_date
			// desc";
			// String sql = "select no, title, contents, hit, reg_date, g_no, o_no, depth,
			// user_no from board";
			if ("conORtit".equals(radioValue)) {
				String sql = "select a.no, title, contents, hit, reg_date, g_no, o_no, depth, user_no, b.name from board a, user b where a.user_no = b.no and (title like '%"
						+ kwd + "%' or contents like '%" + kwd + "%') order by g_no desc, o_no asc";
				pst = conn.prepareStatement(sql);

				rs = pst.executeQuery();

				while (rs.next()) {
					Long no = rs.getLong(1);
					String title = rs.getString(2);
					String contents = rs.getString(3);
					int hit = rs.getInt(4);
					String regDate = rs.getString(5);
					int groupNo = rs.getInt(6);
					int groupOrNo = rs.getInt(7);
					int depth = rs.getInt(8);
					Long userNo = rs.getLong(9);
					String name = rs.getString(10);

					BoardVo vo = new BoardVo();

					vo.setNo(no);
					vo.setTitle(title);
					vo.setContents(contents);
					vo.setHit(hit);
					vo.setRegDate(regDate);
					vo.setGroupNo(groupNo);
					vo.setGroupOrNo(groupOrNo);
					vo.setDepth(depth);
					vo.setUserNo(userNo);
					vo.setName(name);

					list.add(vo);
				}
			} else {

				String sql = "select a.no, title, contents, hit, reg_date, g_no, o_no, depth, user_no, b.name from board a, user b where a.user_no = b.no and name like '%"+kwd+"%'order by g_no desc, o_no asc";
				pst = conn.prepareStatement(sql);

				rs = pst.executeQuery();

				while (rs.next()) {
					Long no = rs.getLong(1);
					String title = rs.getString(2);
					String contents = rs.getString(3);
					int hit = rs.getInt(4);
					String regDate = rs.getString(5);
					int groupNo = rs.getInt(6);
					int groupOrNo = rs.getInt(7);
					int depth = rs.getInt(8);
					Long userNo = rs.getLong(9);
					String name = rs.getString(10);

					BoardVo vo = new BoardVo();

					vo.setNo(no);
					vo.setTitle(title);
					vo.setContents(contents);
					vo.setHit(hit);
					vo.setRegDate(regDate);
					vo.setGroupNo(groupNo);
					vo.setGroupOrNo(groupOrNo);
					vo.setDepth(depth);
					vo.setUserNo(userNo);
					vo.setName(name);

					list.add(vo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 자원 정리
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	
	public List<BoardVo> pagingList(int start, int end) {
		List<BoardVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConnection();			
			
				String sql = "select a.no, title, contents, hit, reg_date, g_no, o_no, depth, user_no, b.name from board a, user b where a.user_no = b.no order by g_no desc, o_no asc limit ?, ?";
				pst = conn.prepareStatement(sql);
				
				pst.setInt(1, start);
				pst.setInt(2, end);

				rs = pst.executeQuery();

				while (rs.next()) {
					Long no = rs.getLong(1);
					String title = rs.getString(2);
					String contents = rs.getString(3);
					int hit = rs.getInt(4);
					String regDate = rs.getString(5);
					int groupNo = rs.getInt(6);
					int groupOrNo = rs.getInt(7);
					int depth = rs.getInt(8);
					Long userNo = rs.getLong(9);
					String name = rs.getString(10);

					BoardVo vo = new BoardVo();

					vo.setNo(no);
					vo.setTitle(title);
					vo.setContents(contents);
					vo.setHit(hit);
					vo.setRegDate(regDate);
					vo.setGroupNo(groupNo);
					vo.setGroupOrNo(groupOrNo);
					vo.setDepth(depth);
					vo.setUserNo(userNo);
					vo.setName(name);

					list.add(vo);
				}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 자원 정리
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public Boolean delete(Long no) {
		Connection conn = null;
		PreparedStatement pst = null;
		Boolean result = false;
		try {
			conn = getConnection();

			// 4.sql 문 실행
			String sql = "delete from board where no= ?";

			pst = conn.prepareStatement(sql);

			pst.setLong(1, no);

			int count = pst.executeUpdate();
			result = count == 1;
			// 5. 성공여부
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 6. 자원 정리
			try {
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public BoardVo hit(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pst = null;
		Boolean result = false;
		try {
			conn = getConnection();

			// 4.sql 문 실행
			// String sql = "update user set name = ?, password = ? where no = ?";
			String sql = "update board set hit = hit + 1 where no = ?";
			pst = conn.prepareStatement(sql);

			pst.setLong(1, vo.getNo());

			int count = pst.executeUpdate();

			// 5. 성공여부
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 6. 자원 정리
			try {
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vo;
	}

	public void updateOno(int gNo, int oNo) {
		Connection conn = null;
		PreparedStatement pst = null;
		Boolean result = false;
		try {
			conn = getConnection();

			// 4.sql 문 실행
			// String sql = "update user set name = ?, password = ? where no = ?";
			String sql = "update board set o_no = o_no+1 where g_no = ? and o_no >= ? ";
			pst = conn.prepareStatement(sql);

			pst.setInt(1, gNo);
			pst.setInt(2, oNo);

			int count = pst.executeUpdate();

			// 5. 성공여부
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 6. 자원 정리
			try {
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	public int getTotalCount() {
		BoardVo BoardVo = null;

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select count(*) from board";
			pst = conn.prepareStatement(sql);			

			rs = pst.executeQuery();

			if (rs.next()) {
				int gNo = rs.getInt(1);
				return gNo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 자원 정리
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	

	public int findGNo(int no) {
		BoardVo BoardVo = null;

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select g_no from board where no = ?";
			pst = conn.prepareStatement(sql);

			pst.setLong(1, no);

			rs = pst.executeQuery();

			if (rs.next()) {
				int gNo = rs.getInt(1);
				return gNo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 자원 정리
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public int findMaxGNo() {
		BoardVo BoardVo = null;

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select max(g_no) from board";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			if (rs.next()) {
				int gNo = rs.getInt(1);
				return gNo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 자원 정리
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public int findONo(int no) {
		BoardVo BoardVo = null;

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select o_no from board where no = ?";
			pst = conn.prepareStatement(sql);

			pst.setLong(1, no);

			rs = pst.executeQuery();

			if (rs.next()) {
				int oNo = rs.getInt(1);
				return oNo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 자원 정리
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public BoardVo insert(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pst = null;
		Boolean result = false;

		try {
			conn = getConnection();

			// String sql = "insert into user values(null, ?, ?, ?, ?, sysdate())";
			String sql = "insert into board values(null, ?, ?, ?, sysdate(), ?, ?, ?, ?)";

			pst = conn.prepareStatement(sql);

			pst.setString(1, vo.getTitle());
			pst.setString(2, vo.getContents());
			pst.setInt(3, vo.getHit());
			pst.setInt(4, vo.getGroupNo());
			pst.setInt(5, vo.getGroupOrNo());
			pst.setInt(6, vo.getDepth());
			pst.setLong(7, vo.getUserNo());

			int count = pst.executeUpdate();

			result = count == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {

			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mysql://192.168.1.98:3307/webdb";
			conn = DriverManager.getConnection(url,"webdb","webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		
		return conn;
	}

//	public BoardVo findByEmailAndPassword(BoardVo vo) {
//		BoardVo BoardVo = null;
//		
//		Connection conn = null;
//		PreparedStatement pst = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = getConnection();
//			String sql = "select no, name  from user where email = ? and password = ?";
//			pst = conn.prepareStatement(sql);
//			
//			pst.setString(1, vo.getEmail());
//			pst.setString(2, vo.getPassword());
//			
//			rs = pst.executeQuery();
//			
//			if(rs.next()) {
//				Long no = rs.getLong(1);
//				String name = rs.getString(2);
//				
//				BoardVo = new BoardVo();
//				
//				BoardVo.setNo(no);
//				//BoardVo.setName(name);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			// 6. 자원 정리
//			try {
//				if(rs != null)
//					rs.close();
//				if(pst != null)
//					pst.close();
//				if(conn !=null)
//					conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return BoardVo;
//	}

	public BoardVo findByNo(Long no) {
		BoardVo BoardVo = null;

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select title, contents, g_no, o_no from board where no = ?";
			pst = conn.prepareStatement(sql);

			pst.setLong(1, no);

			rs = pst.executeQuery();

			if (rs.next()) {

				String title = rs.getString(1);
				String contents = rs.getString(2);
				int groupNo = rs.getInt(3);
				int groupOrNo = rs.getInt(4);

				BoardVo = new BoardVo();

				BoardVo.setTitle(title);
				BoardVo.setContents(contents);
				BoardVo.setGroupNo(groupNo);
				BoardVo.setGroupOrNo(groupOrNo);
				BoardVo.setNo(no);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 자원 정리
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return BoardVo;
	}

	public BoardVo update(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pst = null;
		Boolean result = false;
		try {
			conn = getConnection();

			// 4.sql 문 실행
			// String sql = "update user set name = ?, password = ? where no = ?";
			String sql = "update board set title = ?, contents = ? where no = ?";
			pst = conn.prepareStatement(sql);

			pst.setString(1, vo.getTitle());
			pst.setString(2, vo.getContents());
			pst.setLong(3, vo.getNo());

			int count = pst.executeUpdate();

			// 5. 성공여부
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 6. 자원 정리
			try {
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vo;
	}
}
