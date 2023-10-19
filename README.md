package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface CrudInterface extends CrudRepository<SpringBootEmployee,Integer>{

}





package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CrudRunner implements CommandLineRunner {
	@Autowired
	private CrudInterface repo;

	@Override
	public void run(String... args) throws Exception {
		repo.saveAll(Arrays.asList(new SpringBootEmployee(1901,"Bibhu",12300),
				new SpringBootEmployee(1903,"Kamal",12400),
				new SpringBootEmployee(1908,"Syena",18000)
				));
	}

}






package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpringBootEmployee {
	@Id
	private int eid;
	@Column(name="ename",length=200,unique=true)
	private String ename;
	@Column(name="esal",length=200,nullable=false)
	private double esal;
	public SpringBootEmployee(int eid, String ename, double esal) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.esal = esal;
	}

}










spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
spring.datasource.url= jdbc:oracle:thin:@192.168.10.230:1521:orcl
spring.datasource.password= training
spring.datasource.username= training
spring.jpa.database-platform= org.hibernate.dialect.Oracle8iDialect
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql= true







