package mavenTest.maven_01.com.lu.test;

import java.util.EnumSet;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

public class Test_01 {

	public static void main(String[] args) {
	
		ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().configure().build();
		Metadata metadata=new MetadataSources(serviceRegistry).buildMetadata();
		SchemaExport export =new SchemaExport();
		export.create(EnumSet.of(TargetType.STDOUT, TargetType.DATABASE),metadata);
	
	}
}
