package mavenTest.maven_01.com.lu.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	//hibernate工具类  
    private static SessionFactory sessionFactory;  
      
    private HibernateUtil(){  
        //私有构造方法  
    }  
      
    static{  
        Configuration cfg=new Configuration();  
        cfg.configure();//读取配置文件  
        sessionFactory=cfg.buildSessionFactory();//包含配置文件的所有配置信息  
    }  
      
    public static SessionFactory getSessionFactory(){  
        return sessionFactory;  
    }  
      
    public static Session getSession(){  
        return sessionFactory.openSession();//获取session  
    }  
	
	
	

}

/***
 *    
	private static SessionFactory sessionFactory;
	
	static{
		ServiceRegistry registry=new StandardServiceRegistryBuilder().configure().build();
		sessionFactory=new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
*/