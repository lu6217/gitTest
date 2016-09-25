package mavenTest.maven_01.com.lu.test;

import mavenTest.maven_01.com.lu.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Test_02 {

	
	Session  session =HibernateUtil.getSessionFactory().openSession();
	Transaction ts=session.beginTransaction();
	
	public static void main(String[] args) {
	
	}

	
	
}
