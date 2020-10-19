package net.wicp.tams.duckula.ops;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import net.wicp.tams.common.spring.autoconfig.annotation.EnableTams;

@SpringBootApplication
@ImportResource("classpath:beanRefContext.xml")
@Configuration
@MapperScan("net.wicp.tams.app.duckula.controller.dao")
@EnableTams
@ComponentScan("net.wicp.tams.app.duckula.controller")
public class App extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AppConfiguration.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication application = new SpringApplication(App.class);
		application.setApplicationContextClass(AnnotationConfigWebApplicationContext.class);
		SpringApplication.run(App.class, args);
	}

	public static void contributeIgnoredPathsFilter(org.apache.tapestry5.ioc.Configuration<String> configuration) {
		configuration.add("/connector");
	}

}
