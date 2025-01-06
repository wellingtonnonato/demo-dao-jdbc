package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			
			int rows1 = st.executeUpdate("Update seller set baseSalary = 2090 where departmentId = 1");
			int rows2 = st.executeUpdate("Update seller set baseSalary = 3090 where departmentId = 2");
			
			System.out.println("rows1: " + rows1);
			System.out.println("rows2: " + rows2);
			conn.commit();
		}
		catch(SQLException e) {
			try {
				conn.rollback();
				throw new DbIntegrityException("Transaction rolled back! Caused by: "+ e.getMessage());
			} catch (SQLException e1) {
				throw new DbIntegrityException("Error trying to rollback! Caused by: "+ e1.getMessage());
			}
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}	

	}

}
