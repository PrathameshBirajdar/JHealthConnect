package com.p.jhealthconnect.jhealthconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class JhealthconnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JhealthconnectApplication.class, args);
		System.out.println("\n" +
				"===============================================\n" +
				"    🏥 JHealthConnect Application Started     \n" +
				"===============================================\n" +
				"    Access your application at:               \n" +
				"    🌐 http://localhost:8080                  \n" +
				"                                             \n" +
				"    H2 Database Console (Development):       \n" +
				"    🗄️  http://localhost:8080/h2-console      \n" +
				"    JDBC URL: jdbc:h2:mem:jhealthconnect     \n" +
				"    Username: sa                             \n" +
				"    Password: (leave empty)                  \n" +
				"===============================================\n");
	}

	/**
	 * CORS Configuration for frontend-backend communication
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:8080", "http://localhost:3000")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}
}