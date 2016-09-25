package mavenTest.maven_01.com.lu.dao;

import mavenTest.maven_01.com.lu.entity.User;
import mavenTest.maven_01.com.lu.util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UserDaoHibernateImpl implements UserDao {


    public void saveUser(User user) {  
        // TODO Auto-generated method stub  
        Session s=null;  
        Transaction tx=null;//开启事务  
        try{  
            s=HibernateUtil.getSession();//获得session  
            tx=s.beginTransaction();  
            s.save(user);//删除  
            tx.commit();//提交  
        }finally{  
            if(s!=null){  
                s.close();  
            }  
        }  
    }  
  

    public User findUserByName(String name) {  
        // TODO Auto-generated method stub  
        Session s=null;  
        try{  
            s=HibernateUtil.getSession();//获得session  
            Criteria c=s.createCriteria(User.class);  
            //加入条件查询  
            c.add(Restrictions.eq("name", name));  
            User user=(User)c.uniqueResult();  
            return user;  
        }finally{  
            if(s!=null){  
                s.close();  
            }  
        }  
    }  

    public User findUserById(int id) {  
        // TODO Auto-generated method stub  
        Session s=null;  
        try{  
            s=HibernateUtil.getSession();//获得session  
            User user=(User)s.get(User.class, id);  
            return user;  
        }finally{  
            if(s!=null){  
                s.close();  
            }  
        }  
    }  
    public void updateUser(User user) {  
        // TODO Auto-generated method stub  
        Session s=null;  
        Transaction tx=null;//开启事务  
        try{  
            s=HibernateUtil.getSession();//获得session  
            tx=s.beginTransaction();  
            s.update(user);//删除  
            tx.commit();//提交  
        }finally{  
            if(s!=null){  
                s.close();  
            }  
        }  
    }  

    public void remove(User user) {  
        // TODO Auto-generated method stub  
        Session s=null;  
        Transaction tx=null;//开启事务  
        try{  
            s=HibernateUtil.getSession();//获得session  
            tx=s.beginTransaction();  
            s.delete(user);//删除  
            tx.commit();//提交  
        }finally{  
            if(s!=null){  
                s.close();  
            }  
        }  
    }  

}
