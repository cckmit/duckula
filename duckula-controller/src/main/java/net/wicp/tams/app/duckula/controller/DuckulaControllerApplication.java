package net.wicp.tams.app.duckula.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:beanRefContext.xml")
@Configuration
@MapperScan("net.wicp.tams.app.duckula.controller.dao")
public class DuckulaControllerApplication {

	public static void main(String[] args) {
		System.setProperty("DEPLOY_ENV", "dev");// TODO  上线前请去掉此句，在本机设置好环境变量也可去掉此句
		SpringApplication.run(DuckulaControllerApplication.class, args);
	}

}
