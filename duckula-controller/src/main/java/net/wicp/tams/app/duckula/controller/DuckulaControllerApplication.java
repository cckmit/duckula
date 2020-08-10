package net.wicp.tams.app.duckula.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import net.wicp.tams.common.spring.autoconfig.annotation.EnableTams;

@SpringBootApplication
@ImportResource("classpath:beanRefContext.xml")
@Configuration
@EnableTams
public class DuckulaControllerApplication {

	public static void main(String[] args) {
		System.setProperty("DEPLOY_ENV", "dev");// TODO  上线前请去掉此句，在本机设置好环境变量也可去掉此句
		SpringApplication.run(DuckulaControllerApplication.class, args);
	}

}
