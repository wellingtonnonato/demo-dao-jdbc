package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
	
	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("Insert into department(name) "
					+ "values(?)"
					, Statement.RETURN_GENERATED_KEYS);
			
            st.setString(1, obj.getName());
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				rs = st.getGeneratedKeys();
				
				if (rs.next()) {
					obj.setId(rs.getInt(1));
				}
			}
			else {
				throw new DbException("Unexoercted error! No rows affected!");				
			} 
			
		} catch (SQLException e){
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("update department "					
					+ "   set Name = ? "
					+ " where Id = ?");
			
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			st.executeUpdate(); 
			
		} catch (SQLException e){
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("delete from department a where a.id = ?");
			st.setInt(1, id);
			st.executeUpdate(); 
			
		} catch (SQLException e){
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Department findByid(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("Select * from department a where a.id = ?");			
            st.setInt(1, id);
            rs = st.executeQuery();
            
            if(rs.next()) {
            	Department dep = new Department(rs.getInt("Id")
            			, rs.getString("Name"));
				return dep;
			}
			
			return null;
			
		} catch (SQLException e){
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("Select * from department ");			
            rs = st.executeQuery();
            List <Department> listDepartment = new ArrayList<>();
            
            while(rs.next()) {
            	Department dep = new Department(rs.getInt("Id")
            	 		, rs.getString("Name"));
				listDepartment.add(dep);
			}
			
			return listDepartment;
			
		} catch (SQLException e){
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
