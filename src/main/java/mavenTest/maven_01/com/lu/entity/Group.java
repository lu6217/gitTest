package mavenTest.maven_01.com.lu.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//在实体类的类名前  
//标注是实体  
//对应的表名  
//相当于xml的  
//<class name="com.pegasus.domain.Group" table="t_group"></class>  
@Entity  
@Table(name="t_group")  
public class Group {  
    private int id;  
    private String name;  
    private Set<User> users = new HashSet<User>();  
  
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
  
    //在一对多"一"的一方关联的集合前  
    //使用@OneToMany标注,并标明"mappedBy",即关联的"多"的一方所对应的类的属性
//mapped by 即 inverse=true 由多一方控制 

//mapped用于指定在双向关系中两个实体中被哪个实体是被关联处理的. 

//在你的department和employee的一对多关系中,当你指定department中的mappedBy后,你的关系只能被employee来主动维护.也就是employee级联的处理department.  
    //cascade参数标明级联的方式,如果不需要级联可以省略  
    //相当于<set name="users">  
    //             <key column="groupId"></key>  
    //             <one-to-many class="com.pegasus.domain.User"/>  
        //         </set>  
    @OneToMany(mappedBy="group",  
        cascade={CascadeType.ALL}  
        )  
    public Set<User> getUsers() {  
        return users;  
    }  
    public void setUsers(Set<User> users) {  
        this.users = users;  
    }  
}  