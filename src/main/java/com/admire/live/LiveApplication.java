package com.admire.live;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
//@ImportResource({"classpath:spring-dubbo.xml"}) //暂时不启用dubbo，后期放开spring的bean的xml文件
@ComponentScan(basePackages = {"com.admire.live"})
public class LiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiveApplication.class, args);
	}
}
