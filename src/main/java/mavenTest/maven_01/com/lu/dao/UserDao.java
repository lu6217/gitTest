package mavenTest.maven_01.com.lu.dao;

import mavenTest.maven_01.com.lu.entity.User;

public interface UserDao {  
    //数据访问接口  
    public void saveUser(User user);//保存对象  
    public User findUserByName(String name);//通过用名字查找  
    public User findUserById(int id);//通过id查找  
    public void updateUser(User user);//更新  
    public void remove(User user);//删除  
}  