package mavenTest.maven_01.com.lu.test;

import mavenTest.maven_01.com.lu.entity.Group;
import mavenTest.maven_01.com.lu.entity.User;
import mavenTest.maven_01.com.lu.util.HibernateUtil;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	/*public static void main(String[] args) {
		UserDao dao=new UserDaoHibernateImpl();  
        User user=new User();  
          
        //创建保存  
        user.setName("xc");  
        user.setBirthday(new Date()) ;  
        dao.saveUser(user);  
          
        //更新  
        user.setName("swxc");  
        dao.updateUser(user);  
          
        //查询  
        User u=dao.findUserByName(user.getName());  
          System.out.println(u.toString()+"----------------");
        //删除  
       // dao.remove(u);  
	}*/
	//private static SessionFactory sessionFactory;  
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
    /**级联保存*/  
    @Test  
    public void testSaveUser() {  
        System.out.print("begin to testSaveUser()");  
        User u = new User();  
        u.setName("u1");  
        Group g = new Group();  
        g.setName("g1");  
        u.setGroup(g);  
       // Session s = sessionFactory.getCurrentSession();  
        session.beginTransaction();  
        //s.save(g);  
        session.save(u);  //级联保存
       // session.getTransaction().commit();  
        //session.close();
    }  
   //级联保存
  @Test  
  public void testSaveGroup() {  
      System.out.print("begin to testSaveGroup()");  
      User u1 = new User();  
      u1.setName("u3");  
      User u2 = new User();  
      u2.setName("u2");  
      Group g = new Group();  
      g.setName("g2");  
      g.getUsers().add(u1);  
      g.getUsers().add(u2);  
      u1.setGroup(g);  
      u2.setGroup(g);  
     // Session s = sessionFactory.getCurrentSession();  
      session.beginTransaction();  
      session.save(g);  //级联保存 
      //session.getTransaction().commit();  
      //session.close();
  }  
    /**级联查询*/  
    @Test  
    public void testGetUser() {  
        System.out.print("begin to testGetUser()");  
//      testSaveGroup();  
          
        //Session s = sessionFactory.getCurrentSession();  
        session.beginTransaction();  
        User u = (User)session.get(User.class, 1);  
          
        System.out.println(u.getGroup().getName());  
        //session.getTransaction().commit();  
       // session.close();
    }  
  //删除user
  @Test  
  public void testDeleteUser() {  
      System.out.print("begin to testDeleteUser()");    
//    testSaveGroup();  
      //Session s = sessionFactory.getCurrentSession();  
      //session.beginTransaction();  
      //User u = (User)session.load(User.class, 1);  
      //u.setGroup(null);  //删除user前把Group置空
      //session.delete(u);  
      session.createQuery("delete from User u where u.id = 1").executeUpdate();  
  }  
  
    /**级联修改*/  
    @Test  
    public void testUpdateUser() {  
        System.out.print("begin to testUpdateUser()");    
//        testSaveGroup();  
          
        Session s = HibernateUtil.getSession();  
        s.beginTransaction();  
        User u = (User)s.get(User.class, 1);  
        s.getTransaction().commit();  
        s.close();
       
        u.setName("user");  
        u.getGroup().setName("group");  
          
        Session s2 = HibernateUtil.getSession();  
        s2.beginTransaction();  
        s2.update(u);  
          
        s2.getTransaction().commit();  
        s2.close();
    }  
      
    /**级联删除*/  
    @Test  
    public void testDeleteGroup() {  
        System.out.print("begin to testDeleteGroup()");   
//        testSaveGroup();  
          
//        Session s = sessionFactory.getCurrentSession();  
        //session.beginTransaction();  
        //User u = (User)s.load(User.class, 1);  
        //u.setGroup(null);  
        //s.delete(u);  
        Group g = (Group)session.load(Group.class, 1);  
        session.delete(g);  
        //s.createQuery("delete from User u where u.id = 1").executeUpdate();  
        //session.getTransaction().commit();  
       // session.close();
    }     
      
      
//  @Test  
//  public void testSchemaExport() {  
//      new SchemaExport(new AnnotationConfiguration().configure()).create(true, true);  
//  }  
      
//  public static void main(String[] args) {  
//      beforeClass();  
//  }  
}  