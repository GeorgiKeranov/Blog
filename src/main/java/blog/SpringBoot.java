package blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SpringBoot extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/res/**")
                .addResourceLocations("file:/home/georgi/Server/");
    }
}
