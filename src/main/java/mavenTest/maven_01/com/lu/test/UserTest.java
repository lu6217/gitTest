package mavenTest.maven_01.com.lu.test;

import java.util.Date;

import mavenTest.maven_01.com.lu.dao.UserDao;
import mavenTest.maven_01.com.lu.dao.UserDaoHibernateImpl;
import mavenTest.maven_01.com.lu.entity.User;

public class UserTest {
	
	public static void main(String[] args) {
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
	}
}
