package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();	
		
		System.out.println("=== TEST 1: seller findById =====");
		Seller seller = sellerDao.findByid(3);
		System.out.println(seller);
		
		System.out.println("\n=== TEST 2: seller findByDepartmentId =====");
	    List<Seller> listSeller = sellerDao.findByDepartmentId(2);
	    
	    for (Seller obj : listSeller) {
	    	System.out.println(obj);
	    }
	    
	    System.out.println("\n=== TEST 3: seller findAll =====");
	    listSeller = sellerDao.findAll();
	    
	    for (Seller obj : listSeller) {
	    	System.out.println(obj);
	    }
	    
	    System.out.println("\n=== TEST 4: seller insert =====");
	    Department dep = new Department(2, null);
	    Seller newSeller = new Seller(null
	    		, "Greg"
	    		, "greg@gmail.com"
	    		, new Date()
	    		, 4000.0
	    		, dep);
	    sellerDao.insert(newSeller);
	    System.out.println("Inserted! New id = "+ newSeller.getId());
	    
	    System.out.println("\n=== TEST 5: seller update =====");
	    seller = sellerDao.findByid(1);
	    seller.setName("Marta Waine");
	    sellerDao.update(seller);
	    System.out.println("Update completed");
	}

}
