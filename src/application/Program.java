package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();	
		
		System.out.println("=== TEST 1: seller findById =====");
		Seller seller = sellerDao.findByid(3);
		System.out.println(seller);
		
		System.out.println("\n=== TEST 2: seller findByDepartmentId =====");
	    List<Seller> listSeller = sellerDao.findAll(2);
	    
	    for (Seller obj : listSeller) {
	    	System.out.println(obj);
	    }
	}

}
