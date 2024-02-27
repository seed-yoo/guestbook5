package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class GuestbookDao {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/guestbook_db";
	private String id = "guestbook";
	private String pw = "guestbook";

	// 생성자
	// 메소드-gs

	// 메소드-일반

	// 연결
	public void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {
			System.out.println("error:" + e);

		}
	}

	// 종료
	public void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 전체가져오기
	public List<PersonVo> personSelect() {

		this.getConnection();

		List<PersonVo> personList = new ArrayList<PersonVo>();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " select no, ";
			query += "	      name, ";
			query += "        password, ";
			query += "	      content, ";
			query += "	      reg_date ";
			query += " from guestbook ";

			// 바인딩
			pstmt = conn.prepareStatement(query);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {// 반복
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String reg_date = rs.getString("reg_date");

				// db에서 가져온 데이터 vo로 묶기
				PersonVo personVo = new PersonVo(no, name, password, content, reg_date);
				// 리스트에 주소 추가
				personList.add(personVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return personList;
	}

	// 등록
	public int personInsert(PersonVo personVo) {
		int count = -1;

		this.getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " insert into guestbook ";
			query += " values(null, ?, ?, ?, now()) ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getPassword());
			pstmt.setString(3, personVo.getContent());

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		this.close();

		return count;
	}

	// 삭제
	public int personDelete(int no, String password) {
		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " delete from guestbook ";
			query += " where no = ? ";
			query += " and password = ? ";

			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			pstmt.setString(2, password);

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 삭제되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;
	}

	// 1개 가져오기
	public PersonVo personSelectOne(int personId) {

		this.getConnection();

		PersonVo personVo = null;

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " select no, ";
			query += "	      name, ";
			query += "        password, ";
			query += "	      content, ";
			query += "	      reg_date ";
			query += " from guestbook ";
			query += " where no=? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, personId);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {// 반복
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String reg_date = rs.getString("reg_date");

				// db에서 가져온 데이터 vo로 묶기
				personVo = new PersonVo(no, name, password, content, reg_date);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return personVo;
	}
	
	// 수정
	public int personModify(PersonVo personVo) {
		int count = -1;

		this.getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " update guestbook ";
			query += " set name=?, ";
			query += " 	   password=?, ";
			query += "     content=?, ";
			query += "     reg_date=? ";
			query += " where no = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getPassword());
			pstmt.setString(3, personVo.getContent());
			pstmt.setString(4, personVo.getReg_date());
			pstmt.setInt(5, personVo.getNo());
			
			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 수정되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		this.close();

		return count;
	}
}
