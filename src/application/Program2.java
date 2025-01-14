package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

public class Program2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		try {
			DepartmentDao departmentDao = DaoFactory.createDeparmentDao();	
			
			System.out.println("=== TEST 1: department findById =====");
			Department department = departmentDao.findByid(1);
			System.out.println(department);
			
			System.out.println("\n=== TEST 2: department findAll =====");
		    List<Department> listDepartmente = departmentDao.findAll();
		    
		    for (Department obj : listDepartmente) {
		    	System.out.println(obj);
		    }
		    
		    System.out.println("\n=== TEST 3: department insert =====");		    
		    Department newDepartment = new Department(null
		    		, "Informatica");
		    
		    departmentDao.insert(newDepartment);
		    System.out.println("Inserted! New id = "+ newDepartment.getId());
		    
		    System.out.println("\n=== TEST 4: department update =====");
		    department = departmentDao.findByid(1);
		    department.setName("Food");
		    departmentDao.update(department);
		    System.out.println("Update completed");
		    
		    System.out.println("\n=== TEST 5: department delete =====");
		    System.out.println("Enter id for delete test: ");
		    int id = sc.nextInt();
		    departmentDao.deleteById(id);
		    System.out.println("Delete completed");
			
		} finally {
			sc.close();			
		}
	}

}
