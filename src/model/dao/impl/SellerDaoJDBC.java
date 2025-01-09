package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findByid(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("Select a.*\r\n"
					+ "     , b.name as DepName\r\n"
					+ "  from seller a\r\n"
					+ "  join department b on b.id = a.DepartmentId\r\n"
					+ " where a.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Department dep = instantiateDepartment(rs);				
				Seller obj = instantiateSeller(rs, dep);
				return obj;
			}
			
			return null;
		}
		catch(SQLException e ) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		return new Seller(rs.getInt("Id")
				, rs.getString("Name")
				, rs.getString("Email")
				, rs.getDate("BirthDate")
				, rs.getDouble("BaseSalary")
				, dep);
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {		
		return new Department(rs.getInt("DepartmentId")
				, rs.getString("DepName"));
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("Select a.*\r\n"
					+ "     , b.name as DepName\r\n"
					+ "  from seller a\r\n"
					+ "  join department b on b.id = a.DepartmentId\r\n"
					+ " order by a.name asc");
			
			rs = st.executeQuery();
			List<Seller> listSeller = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while (rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if (dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller obj = instantiateSeller(rs, dep);
				listSeller.add(obj);
			}
			
			return listSeller;
		}
		catch(SQLException e ) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartmentId(Integer departmentId) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("Select a.*\r\n"
					+ "     , b.name as DepName\r\n"
					+ "  from seller a\r\n"
					+ "  join department b on b.id = a.DepartmentId\r\n"
					+ " where a.DepartmentId = ?"
					+ " order by a.name asc");
			
			st.setInt(1, departmentId);
			rs = st.executeQuery();
			List<Seller> listSeller = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while (rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if (dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller obj = instantiateSeller(rs, dep);
				listSeller.add(obj);
			}
			
			return listSeller;
		}
		catch(SQLException e ) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
