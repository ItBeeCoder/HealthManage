package com.itc.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.entity.Oldman;

import com.itc.util.DBUtil;

public class ConnectionTest {
	public static void main(String[] args) throws SQLException {
		DBUtil util = new DBUtil();
		
		Connection conn = util.openConnection();
		String sql = "select * from oldman";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			
			String username = rs.getString("username");
			Oldman oldMan = new Oldman();
			oldMan.setUsername(username);
			System.out.println(oldMan);
		}
	}

}
