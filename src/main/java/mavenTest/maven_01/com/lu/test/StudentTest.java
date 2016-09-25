package mavenTest.maven_01.com.lu.test;

import mavenTest.maven_01.com.lu.entity.Clazz;
import mavenTest.maven_01.com.lu.entity.Student;
import mavenTest.maven_01.com.lu.util.HibernateUtil;

import org.hibernate.Session;
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

	    @Test
	    public void testSaveClassAndStudent() {
	        Clazz c=new Clazz();
	        c.setName("计本");
	       
	        Student s1=new Student();
	        s1.setName("张三");
	        s1.setClazz(c);
	        
	        Student s2=new Student();
	        s2.setName("李四");
	        s2.setClazz(c);
	       // c.getStudents().add(s1);
	        //c.getStudents().add(s2);
	        session.save(c);
	        session.save(s1);
	        session.save(s2);
	        
	    }
	    
	    @Test
	    public void testLoadClass(){
	        // Class c=(Class)session.load(Class.class, Long.valueOf(2));
	        Clazz c=(Clazz)session.load(Clazz.class, Long.valueOf(1));
	        System.out.println(c.getStudents());
	    }
	    
	    @Test
	    public void testGetClass(){
	        // Class c=(Class)session.get(Class.class, Long.valueOf(2));
	        Clazz c=(Clazz)session.get(Clazz.class, Long.valueOf(1));
	        System.out.println(c.getStudents());
	    }
	    
	    @Test
	    public void testUpdateClass(){
	        Session session1=HibernateUtil.getSession();
	        session1.beginTransaction();
	        Clazz c=(Clazz)session1.get(Clazz.class, Long.valueOf(1));
	        session1.getTransaction().commit(); // 提交事务
	        session1.close();
	        
	        Session session2=HibernateUtil.getSession();
	        session2.beginTransaction();
	        c.setName("计算机本科2");
	        session2.update(c);
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
