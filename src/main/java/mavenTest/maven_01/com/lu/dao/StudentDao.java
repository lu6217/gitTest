package mavenTest.maven_01.com.lu.dao;

import java.util.ArrayList;
import java.util.List;

import mavenTest.maven_01.com.lu.entity.Student;
import mavenTest.maven_01.com.lu.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class StudentDao {

	//添加
	public boolean add(Student student){
		boolean falg=false;
		Session  session =HibernateUtil.getSessionFactory().openSession();
		Transaction ts=session.beginTransaction();
		try{
			session.save(student);
			ts.commit();
			falg=true;
			
		}catch(Exception e){
			if(ts!=null){
				ts.rollback();
			}
			e.printStackTrace();
		}
		
		return falg;
	}
	//刪除
	public boolean delete(Student student){
		
		boolean falg=false;
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction ts=session.beginTransaction();
		try {
			session.delete(student);
			ts.commit();
			falg=true;
		} catch (Exception e) {
			// TODO: handle exception
		
			if(ts!=null){
				ts.rollback();
			}
			e.printStackTrace();
		}
		
		return falg;
	}
	//更新
	public boolean update(int id){
		boolean falg=false;
		
		return falg;
	}
	//查詢
	public List<Object> query(int id){
		
		List<Object> lists=new ArrayList<Object>();
	
		
		return lists;
	}
	
	
}
