package sadian.lisset.personaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PersonaApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(PersonaApiApplication.class, args);

    }
    @Bean
    public WebMvcConfigurer corsConfigurer(){ return new
            WebMvcConfigurer() {
                @Override public void addCorsMappings(CorsRegistry registry) {
            //        registry.addMapping("/**").allowedOrigins("https://localhost:8082").allowedMethods("*");
                    registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
                    //Database.createPersona("Archangel", "Emperor");
                   // Database.selectPersonas();

                } }; }

}
