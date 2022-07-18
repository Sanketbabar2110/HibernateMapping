package com.Runner;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.Pojo.Cart;
import com.Pojo.Items;

public class HibernateOneToMany {
	

	public static void main(String[] args) {
		System.out.println("This is Runner Class started....");
		
		System.out.println("program start here....");
		Cart c1 = new Cart();
		c1.setName("MyCart");
		System.out.println("step 2");

		Items item1 = new Items("I10", 10, 1, c1);
		Items item2 = new Items("I20", 20, 2, c1);
		Items item3 = new Items("I30", 20, 2, c1);
		Items item4 = new Items("I40", 20, 2, c1);
		
		Set<Items> itemSet = new HashSet<Items>();
		itemSet.add(item1);
		itemSet.add(item2);
		itemSet.add(item3);
		itemSet.add(item4);
		
		c1.setItems(itemSet);
	    c1.setTotal(10*2  + 10*2);
	    org.hibernate.Transaction tx = null;
		org.hibernate.Session session = null;
		org.hibernate.SessionFactory Factory = null;
	
	try {
		Factory = new Configuration().configure("Hibernate.config.xml").buildSessionFactory();
		session = Factory.openSession();
		 tx = session.beginTransaction();
		 session.save(c1);
		 session.save(item1);
		 session.save(item2);
		 session.save(item3);
		 session.save(item4);
		 tx.commit();
		 
		 System.out.println("cart id :----> "+c1.getId());
		// System.out.println("item1 ID="+item1);
		 
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	
	finally{
		if ( session != null ) {
			
            session.close();
        }
      if ( Factory != null ) {
        	
            Factory.close();
        }
	}
System.out.println("end..........");
	
	}
}