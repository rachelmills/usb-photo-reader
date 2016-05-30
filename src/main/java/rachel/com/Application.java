package rachel.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
//import rachel.com.home.servlet.DynamicImageServlet;

@SpringBootApplication
public class Application {

    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

//    @Bean
//    public ServletRegistrationBean adminServletRegistrationBean() {
//        return new ServletRegistrationBean(new DynamicImageServlet(), "/Volumes/Lexar/Assignment2/*");
//        return new ServletRegistrationBean(new DynamicImageServlet(), "/openfile/*", "/deletefile/*");
//    }



    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
