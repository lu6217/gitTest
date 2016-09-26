package mavenTest.maven_01.com.lu.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//在实体类的类名前  
//标注是实体  
//对应的表名  
//相当于xml的  
//<class name="com.pegasus.domain.User" table="t_user"></class>  
@Entity  
@Table(name="t_user")  
public class User {  
  private int id;  
  private String name;  
  private Group group;  
    
  //在主键的get方法前  
  //标注主键,以及生成主键策略  
  //相当于<id name="id"><generator class="native"></generator></id>  
  @Id  
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  public int getId() {  
      return id;  
  }  
  public void setId(int id) {  
      this.id = id;  
  }  
    
  //在一般属性的get方法前  
  //一般的列名前不需要标注  
  //相当于<property name="name"></property>  
  public String getName() {  
      return name;  
  }  
  public void setName(String name) {  
      this.name = name;  
  }  
  //在一对多"多"的一方关联的属性get方法前  
  //使用@ManyToOne标注  
  //cascade参数标明级联的方式,如果不需要级联可以省略  
  //相当于<many-to-one name="group" column="groupId"></many-to-one>  
  @ManyToOne(cascade={CascadeType.ALL})  
  public Group getGroup() {  
      return group;  
  }  
  public void setGroup(Group group) {  
      this.group = group;  
  }  
}  