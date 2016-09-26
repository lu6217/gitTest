package mavenTest.maven_01.com.lu.test;

import java.util.Iterator;
import java.util.List;

import mavenTest.maven_01.com.lu.entity.Clazz;
import mavenTest.maven_01.com.lu.entity.Student;
import mavenTest.maven_01.com.lu.util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StudentTest {

	 //private SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
	    private Session session;
	    
	    @Before
	    public void setUp() throws Exception {
	        session=HibernateUtil.getSession(); // 生成一个session
	        session.beginTransaction(); // 开启事务
	    }

	    @After
	    public void tearDown() throws Exception {
	         session.getTransaction().commit(); // 提交事务
	         session.close(); // 关闭session
	    }
	    //级联保存
	    @Test
	    public void testSaveClassAndStudent() {
	        Clazz c=new Clazz();
	        c.setName("土木");
	       
	        Student s1=new Student();
	        s1.setName("gs");
	        s1.setClazz(c);
	        
	        Student s2=new Student();
	        s2.setName("pz");
	        s2.setClazz(c);
	        c.getStudents().add(s1);
	        c.getStudents().add(s2);
	        session.save(c);
//	        session.save(s1);
//	        session.save(s2);
	    }
	    //级联查询
	    @Test
	    public void testLoadClass(){
	        // Class c=(Class)session.load(Class.class, Long.valueOf(2));
	        Clazz c=(Clazz)session.load(Clazz.class, Long.valueOf(1));
	        System.out.println(c.getStudents().iterator().next().getName());
	    }
	    
	    @Test
	    public void testGetClass(){
	        // Class c=(Class)session.get(Class.class, Long.valueOf(2));
	        Clazz c=(Clazz)session.get(Clazz.class, Long.valueOf(1));
	        System.out.println(c.getStudents().iterator().next().getName());
	    }
	    //更新
	    @Test
	    public void testUpdateClass(){
	        Session session1=HibernateUtil.getSession();
	        session1.beginTransaction();
	        Clazz c=(Clazz)session1.get(Clazz.class, Long.valueOf(1));
	        session1.getTransaction().commit(); // 提交事务
	        session1.close();
	        
	        Session session2=HibernateUtil.getSession();
	        session2.beginTransaction();
	        c.setName("计算机本科");
	        session2.update(c);
	        session2.getTransaction().commit(); // 提交事务
	        session2.close();
	    }
	    //级联更新
	    @Test
	    public void testUpdateStudent(){
	        Session session1=HibernateUtil.getSession();
	        session1.beginTransaction();
	        Student student=(Student)session1.get(Student.class, Long.valueOf(1));
	        session1.getTransaction().commit(); // 提交事务
	        session1.close();
	        
	        Session session2=HibernateUtil.getSession();
	        session2.beginTransaction();
	        student.setName("xpz");
	        student.getClazz().setName("软件");
	        session2.update(student);
	        session2.getTransaction().commit(); // 提交事务
	        session2.close();
	    }
	    
	    
	    
	   // <!--更新-->
	    @Test
	    public void testSaveOrUpdateClass(){
	        Session session1=HibernateUtil.getSession();
	        session1.beginTransaction();
	        Clazz c=(Clazz)session1.get(Clazz.class, Long.valueOf(1));
	        session1.getTransaction().commit(); // 提交事务
	        session1.close();
	        
	        Session session2=HibernateUtil.getSession();
	        session2.beginTransaction();
	        c.setName("计算机本科3");
	        
	        Clazz c2=new Clazz();
	        c2.setName("计算机本科5");
	        session2.saveOrUpdate(c);
	        session2.saveOrUpdate(c2);
	        session2.getTransaction().commit(); // 提交事务
	        session2.close();
	    }
	    
	    @Test
	    public void testMergeClass(){
	        Session session1=HibernateUtil.getSession();
	        session1.beginTransaction();
	        Clazz c=(Clazz)session1.get(Clazz.class, Long.valueOf(1));
	        session1.getTransaction().commit(); // 提交事务
	        session1.close();
	        
	        Session session2=HibernateUtil.getSession();
	        session2.beginTransaction();
	        
	    //    Clazz c2=(Clazz)session2.get(Clazz.class, Long.valueOf(1));
	        c.setName("计算机本科4");
	    
	        session2.merge(c);

	        session2.getTransaction().commit(); // 提交事务
	        session2.close();
	    }
	    //<!--删除-->
	    @Test
	    public void testDeleteStudent(){
	        Student student=(Student)session.load(Student.class, Long.valueOf(1));
	        session.delete(student);
	    }
	    
	    
	    @Test
	    public void testStudentCriteria(){
	    	Session session=HibernateUtil.getSession();
	    	List list=session.createCriteria(Student.class)
	    			.add(Restrictions.like("name", "%"))
	    			.addOrder(Order.asc("name"))	//排序//==>.addOrder(Property.forName("name").asc())
	    			.addOrder(Order.desc("id"))
	    			
	    			.setMaxResults(5)		//设置最多显示的条数
	    			.list();
//	    	List list=session.createCriteria(Student.class)
//	    			//.add(Restrictions.like("name", "%"))
//	    			.createCriteria("Clazz")
//	    			.add(Restrictions.like("name", "%"))
//	    			.list();
	    	Iterator iterator=list.iterator();
	    	while(iterator.hasNext()){
	    		Student student=(Student)iterator.next();
	    		System.out.println(student.getId()+"  "+student.getName());
	    	}
	    }
	    
	    
	    /*
	     * 	*Query query = session.createQuery(hql)：利用hql查询语句查询；
			*Criteria critera = session.createCriteria(Class clazz);
			*(3)Transaction tx = session.beginTransaction();     //开始事务；tx.commit()提交事务；
			*session.close();//关闭Session，此后被session管理的持久化对象变为脱管状态；
			*session.save(Object obj);    //添加
			*session.update(Object obj);     //更新
			*session.delete(Object obj);    //删除
			*Object obj = session.get(Class clazz,Serialiazble id);    //根据主键查找记录并返回；
			*Object obj = session.load(Class clazz,Serializable id);    //和get方法效果一样，但是是懒加载，即在不使用他之前他不会返回对象；
	     * */
	
}
