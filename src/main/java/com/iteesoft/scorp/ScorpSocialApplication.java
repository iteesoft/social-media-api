package com.iteesoft.scorp;

import com.iteesoft.scorp.model.User;
import com.iteesoft.scorp.repository.UserRepo;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.ArrayList;

@SpringBootApplication
public class ScorpSocialApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScorpSocialApplication.class, args);
    }

	@Bean
    CommandLineRunner run(UserRepo userRepo) {
		return args -> {
            userRepo.save(new User("Reece James", "rc24", "reecy@blues.com", "pwd123", "http://img", "cool nigga", false, new ArrayList<>()));
            userRepo.save(new User("Mason Mount", "mm19", "moneymase@blues.com", "pwd124","http://img", "cool nigga", false, new ArrayList<>()));
            userRepo.save(new User("Bernardo Silva", "bs12", "benny@city.com","pwd111", "http://img", "cool nigga", false, new ArrayList<>()));
            userRepo.save(new User( "Thierry Henry", "igwe14", "th14@gunners.com","pwd122", "http://img", "cool nigga", false, new ArrayList<>()));
		};
	}

    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") String appDescription, @Value("${application-version}") String appVersion) {
        return new OpenAPI().info(new Info().title("social media app API")
                .version(appVersion).description(appDescription)
                .termsOfService("https://iteesoft.io/terms")
                .license(new License()
                        .name("Ifeoluwa Stephen Adebayo 'iTeesoft'")
                        .url("https://iteesoft.io")));
    }
}
